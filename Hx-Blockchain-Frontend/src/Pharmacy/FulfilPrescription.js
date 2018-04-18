import React, { Component } from 'react';
import BackendURL from '../BackendURL';

class FulfilPrescription extends Component {

    constructor(props) {
        super(props);
        this.state = {
            pres: props.data
        }
    }

    fulfilHandler = () => {
        let newState = {...this.state};
        newState.pres.fulfilled = true;
        this.setState(newState);
        
        let payload = newState;
        this.props.spinner(true);
        fetch(BackendURL + "/fulfilPre", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },                    
            body: JSON.stringify(payload),
            credentials: 'include'}) 
            .then((response) => {    
                this.props.spinner(false);          
                return response;
            })
                .then((resp) => {
                    if (resp.status === 200){
                        this.props.modal("Prescription fulfilled Successfully!")
                        console.log("[Prescription fulfilled] success");
                    }else{
                        this.props.modal("Prescription fulfilled Failed!")
                        this.failHandler();
                        //console.log(payload);
                        console.log("[Prescription fulfilled] failure");
                    }           
                })
                .catch((e) => {
                    this.props.spinner(false);
                    this.props.modal("Network Error")
                    this.failHandler();
                    //this.wrongCredHandler();
                    console.log(e)
                    console.log("[Prescription fulfilled] failure" , e);
                });
    }

    failHandler = () => {
        let newState = {...this.state};
        newState.pres.fulfilled = false;
        this.setState(newState);
    }

    render() {
        return (
            <div className='PrescriptionCard'>
                <div>
                    <h2><b>Prescription ID:</b> {this.state.pres.preId}</h2>
                    <p><b>Issuer:</b> {this.state.pres.issuer}</p>
                    <p><b>Issued to:</b> {this.state.pres.issuee}</p>
                    <p><b>Issue date: </b> {this.state.pres.date}</p>
                    {this.state.pres.drugs.map(
                        (i) => {
                            return <p key={i.drugName+i.drugQty}>{i.drugName} ({i.drugQty})</p>
                        }
                    )}
                </div>
                { this.state.pres.fulfilled? null : <div className='EditInfo-btn' onClick={this.fulfilHandler}><h1>FULFIL</h1></div>}
                {(this.state.pres.fulfilled? <div className='fulfilled'><h1>FULFILLED</h1></div> : null)}
            </div>
            );
    }
}


export default FulfilPrescription;
