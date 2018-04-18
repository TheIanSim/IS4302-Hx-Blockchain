import React, { Component } from 'react';
import FulfilPrescription from './FulfilPrescription'

class Prescriptions extends Component {
    state = {
        prescriptions: this.props.prescriptions,
        issuer: this.props.pd //TODO
    }

    render() {
      return (
        <div>
            <h1>Prescriptions</h1>
            <div className='Prescription-container'>
                {this.state.prescriptions.map(i => <FulfilPrescription data={i} key={i.ID} modal={this.props.modal} spinner={this.spinner}/>)}
            </div>
        </div>
      );
    }
  }

export default Prescriptions;
