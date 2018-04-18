import React, { Component } from 'react';
import BackendURL from '../BackendURL';

class EditInfo extends Component {

    constructor(props) {
        super(props);
        if(this.props.pd){
            let initState = {...this.props.pd};

            delete initState.$class;
            delete initState.detailsId;
            delete initState.owner;
            delete initState.address;

            let NewState = {
                ...initState,
                ...props.pd.address
            };

            delete NewState.$class;
            this.state = NewState;
        }
        
    }

    changeHandler = (event) => {
        const newState = {...this.state};
        newState[event.target.name] = event.target.value;
        this.setState(
            newState
        );
    }

    formatter = () => {
        let outState = {...this.props.pd,
                        ...this.state};
        
        outState.address.country = this.state.country;
        outState.address.postcode = this.state.postcode;
        outState.address.street = this.state.street;

        delete outState.country;
        delete outState.postcode;
        delete outState.street;

        return outState
    }


    confirmHandler = () => {
        let payload = this.formatter();
        this.props.spinner(true);
        fetch(BackendURL + "/updatePersonalInfo2", {
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
                                this.props.confirm(payload);
                                this.props.modal("Info Updated Successfully!")
                                console.log("[Update Info] success");
                            }else{
                                this.props.modal("Info Update Failed!")
                                console.log("[Update Info] failure");
                            }           
                        })
                        .catch((e) => {
                            this.props.spinner(false);  
                            this.props.modal("Network Error")
                            //this.wrongCredHandler();
                            console.log(e)
                            console.log("[Update Info] failure" , e);
                        });
      }

    render() {

        let fields = [];
        Object.keys(this.state).forEach( (k) => {
            
            fields.push(
                    <div className='EditInfoItem' key={k}>
                        <h3>{k}</h3>
                        <input placeholder={this.state[k]} onChange={(e) => this.changeHandler(e)} name={k}/>
                    </div>
            );
        })


        return (
            <div>
                <h1>Edit Personal Details</h1>
                <div className='EditInfo'>
                   {fields}
                </div>
                <div className='EditInfo-btn' onClick={this.confirmHandler} >CONFIRM</div>
            </div>
        );
      }

}

export default EditInfo;
