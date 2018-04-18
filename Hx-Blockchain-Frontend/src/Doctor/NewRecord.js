import React, { Component } from 'react';
import BackendURL from '../BackendURL';

class NewRecord extends Component {

    constructor(props) {

        super(props);
        this.state = {
            rec: {
                issuer: props.issuer,
                issuee: '',
                date: '',
                diagnosis: '',
                testImages: '',
                labReports: '',
                drugAllergies: ''
            }
        }
    }

    changeHandler = (event) => {
        let val = event.target.value;
        let nam = event.target.name;
        let newRec = {...this.state.rec} 
        newRec[nam] = val;
        this.setState({
            rec: newRec
        });   
    }

    confirmHandler = () => {
        let payload = {...this.state.rec};
        payload.testImages = this.toList(this.state.rec.testImages);
        payload.labReports = this.toList(this.state.rec.labReports);
        payload.drugAllergies = this.toList(this.state.rec.drugAllergies);
        if (!payload.issuee || !payload.date ){
            this.props.modal("Patient ID and date cannot be empty!")
        }else{
            this.props.spinner(true);
            fetch(BackendURL + '/createMedInfo', {
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
                            this.props.modal("Record Submitted Successfully!")
                            console.log("[Record Submitted] success");
                        }else{
                            this.props.modal("Record Submission Failed!")
                            console.log("[Record Submission] Failed");
                        }
                        
                    })
                    .catch((e) => {
                        this.props.modal("Network error!")
                        this.props.spinner(false);    
                        console.log(e)
                        console.log("[Record Submission] failure" , e);
                    });
        }
    }

    toList = (str) => {
        try {
            return str.split(',');
        }catch(e) {
            return [str];
        }

    }


    render() {
        return (
            <div>
                <div className='InputContainer'>
                    <h1>Create record:</h1>
                    <div className='InputItem'>
                        <input placeholder={'Patient ID'} onChange={(e) => this.changeHandler(e)} name={'issuee'}/>
                    </div>
                    
            
                    <div className='InputItem'>
                        <input placeholder={'Date'} onChange={(e) => this.changeHandler(e)} name={'date'}/>
                    </div>

                    <div className='InputItem'>
                        <input placeholder={'Diagnosis'} onChange={(e) => this.changeHandler(e)} name={'diagnosis'}/>
                    </div>

                    <div className='InputItem'>
                        <input placeholder={'Test Images (seperate values by comma)'} onChange={(e) => this.changeHandler(e)} name={'testImages'}/>
                    </div>

                    <div className='InputItem'>
                        <input placeholder={'Lab Reports (seperate values by comma)'} onChange={(e) => this.changeHandler(e)} name={'labReports'}/>
                    </div>

                    <div className='InputItem'>
                        <input placeholder={'Drug Allergies (seperate values by comma)'} onChange={(e) => this.changeHandler(e)} name={'drugAllergies'}/>
                    </div>
                    <div className='EditInfo-btn' onClick={this.confirmHandler} >SUBMIT</div>
                </div>
                <br/>
            </div>
        );
    }
}

export default NewRecord;