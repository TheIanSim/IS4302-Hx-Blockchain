import React, { Component } from 'react';
import BackendURL from '../BackendURL';

class NewMC extends Component {

    constructor(props) {

        super(props);
        this.state = {
            mc: {
                mcId: '',
                issuer: props.issuer,
                issuee: '',
                startDate: '',
                duration: 0,
                remark: ''
            }
        }
    }

    changeHandler = (event) => {
        let newMC = {...this.state.mc} 
        let val = event.target.value;
        let nam = event.target.name;
        newMC[nam] = val;
        this.setState({mc: newMC});   
    }

    confirmHandler = () => {
        let payload = this.state.mc;
        if (!payload.issuee || !payload.startDate ){
            this.props.modal("Patient ID and start date cannot be empty!")
        }else{
            this.props.spinner(true);
            fetch(BackendURL + '/createMC', {
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
                            this.props.modal("MC Submitted Successfully!")
                            console.log("[MC Submitted] success");
                        }else{
                            this.props.modal("MC Submission Failed!")
                            console.log("[MC Submission] Failed");
                        }
                        
                    })
                    .catch((e) => {
                        this.props.modal("Network error! " + e)
                        this.props.spinner(false);    
                        console.log(e)
                        console.log("[MC Submission] failure" , e);
                    });
        }
    }


    render() {
        return (
            <div className='InputContainer'>   
                <h1>Create MC:</h1>
                <div className='InputItem'>    
                    <input placeholder={'Patient ID'} onChange={(e) => this.changeHandler(e)} name={'issuee'}/>
                </div>    
                <div className='InputItem'>    
                    <input placeholder={'Start Date'} onChange={(e) => this.changeHandler(e)} name={'startDate'}/>
                </div>    
                <div className='InputItem'>    
                    <input placeholder={'Duration'} onChange={(e) => this.changeHandler(e)} name={'duration'}/>
                </div>    
                <div className='InputItem'>    
                    <input placeholder={'Remark'} onChange={(e) => this.changeHandler(e)} name={'remark'}/>
                </div>
                <div className='EditInfo-btn' onClick={this.confirmHandler} >SUBMIT</div>
            </div>
        );
    }
}

export default NewMC;