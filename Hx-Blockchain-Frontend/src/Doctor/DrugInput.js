import React, { Component } from 'react';

class DrugInput extends Component {
    state = {
                drugName: '',
                drugQty: 0
            }

    changeHandler = (event) => {
        let newState = {...this.state} 
        let val = event.target.value;
        let nam = event.target.name;
        newState[nam] = val;
        this.setState(newState);   
    }

    confirmHandler = () => {
        if (this.state.drugName && this.state.drugQty){
            this.props.click(this.state);
        }else{
            this.props.modal("Drug name and quantity cannot be empty!")
        }
        
    }

    render() {
      return (
        <div>
            <div className='drugInput-container'>
                <h3>Add Drug: </h3>
                <input placeholder={'Drug Name'} onChange={(e) => this.changeHandler(e)} name={'drugName'}/>
                <input placeholder={'Drug Quantity'} onChange={(e) => this.changeHandler(e)} name={'drugQty'}/>
            </div>
            <div className='EditInfo-btn' onClick={this.confirmHandler} >ADD</div>
        </div>
      );
    }
  }

export default DrugInput;

