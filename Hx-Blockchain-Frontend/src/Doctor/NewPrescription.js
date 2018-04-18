import React, { Component } from 'react';
import DrugInput from './DrugInput';
import BackendURL from '../BackendURL';

class NewPrescriptions extends Component {
    state = { 
                preId: '',
                issuee: '',
                issuer: this.props.issuer,
                date: '',
                drugs: [],
                fulfilled: false,
            }

    changeHandler = (event) => {
        let newState = {...this.state} 
        let val = event.target.value;
        let nam = event.target.name;
        newState[nam] = val;
        this.setState(newState);   
    }

    drugInputHandler = (drug) => {
        let newState = {...this.state};
        let curDrugs = [...newState.drugs];
        curDrugs.push({
            ...drug,
            drugQty: parseInt(drug.drugQty)
        });
        newState.drugs = curDrugs;
        this.setState(newState);
    }

    confirmHandler = () => {
        if (!this.state.issuee || !this.state.date || !this.state.drugs){
            this.props.modal("Patient ID, Start Date & Drugs cannot be empty!")
        }
        let payload = this.state;
        console.log(payload)
        this.props.spinner(true);
        fetch(BackendURL + "/createPre", {
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
                                this.props.modal("Prescription Created Successfully!")
                                console.log("[Prescription Created] success");
                            }else{
                                this.props.modal("Prescription Failed!")
                                console.log("[Prescription Created] failure");
                            }           
                        })
                        .catch((e) => {
                            this.props.spinner(false);  
                            this.props.modal("Network Error")
                            //this.wrongCredHandler();
                            console.log(e)
                            console.log("[Prescription Created] failure" , e);
                        });
    }

    render() {

      return (
        <div>
            <div className='InputContainer'>
                <h1>Create Prescription:</h1>
                <div className='InputItem'>
                    <input placeholder={'Patient ID'} onChange={(e) => this.changeHandler(e)} name={'issuee'}/>
                </div>

                <div className='InputItem'>
                    <input placeholder={'Start Date'} onChange={(e) => this.changeHandler(e)} name={'date'}/>
                </div>

                <div className='InputItem Drug'>
                    <h3>Drugs: </h3>
                    <ul>
                        {this.state.drugs.map( d => {
                            return  d.drugName ? <li key={d.drugName+d.drugQty}>{d.drugName} ({d.drugQty})</li>: null
                        } )}
                    </ul>
                </div>      

                <div className='InputItem'>
                    <DrugInput click={this.drugInputHandler} modal={this.props.modal}/>   
                </div>      
            </div>
            <div className='EditInfo-btn' onClick={this.confirmHandler} >SUBMIT</div>
        </div>
      );
    }
  }

export default NewPrescriptions;
