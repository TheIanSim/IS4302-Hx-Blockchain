import React, { Component } from 'react';
import BackendURL from '../BackendURL';

class InputComponent extends Component {

    state = {
        inp: null,
        url: this.props.url,
        id: this.props.pd,
        role: null
    }

    changeHandler = (event) => {
        this.setState({
            inp: event.target.value
        })
    }

    formatReq = (input) => {

        return {
            patient: this.state.id.owner,
            accessor: input,
            a: 'GRANT',
            role: this.state.role
        }
    }

    submitHandler = () => {
        let payload = this.formatReq(this.state.inp);
        
        if (this.state.inp && this.state.role) {
            this.props.spinner(true);
            fetch(BackendURL + this.state.url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
                credentials: 'include'}) 
                .then((response) => {
                    this.props.spinner(false);
                    if (response.status === 200){
                        this.props.modal("Access to " + this.state.inp + " granted!")
                    }else{
                        this.props.modal("Error")
                    }
                }).catch((e) => {
                        this.props.modal("Network Error")
                    });
        }else{
            this.props.modal("ID and role cannot be empty!")
        }
    }

    selectRoleHandler = (role) => {
        if(role === 'doc'){
            this.setState({
                ...this.state,
                role: 'doctor'
            })
        }else if(role === 'emp'){
            this.setState({
                ...this.state,
                role: 'employer'
            })
        }else if(role === 'pharm'){
            this.setState({
                ...this.state,
                role: 'pharmacy'
            })
        }
    }

    render() {
      return (
        <div className='inputComponent'>
           <h2>Enter Role and ID</h2>
            <button className={(this.state.role === 'doctor')? 'inputComponentSelected' : null} onClick={() => this.selectRoleHandler('doc')} >Doctor</button>
            <button className={(this.state.role === 'employer')? 'inputComponentSelected' : null} onClick={() => this.selectRoleHandler('emp')} >Employer</button>
            <button className={(this.state.role === 'pharmacy')? 'inputComponentSelected' : null} onClick={() => this.selectRoleHandler('pharm')} >Pharmacy</button>
            <input type="text" placeholder="Enter ID" onChange={this.changeHandler}/>
            <button onClick={this.submitHandler}>SUBMIT</button>
        </div>
      );
    }
  }

export default InputComponent;
