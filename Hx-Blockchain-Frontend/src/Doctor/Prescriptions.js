import React, { Component } from 'react';
import PrescriptionCard from '../Patient/PrescriptionCard';
import NewPrescription from './NewPrescription';

class Prescriptions extends Component {
    state = {
        prescriptions: this.props.prescriptions,
        issuer: this.props.pd.owner
    }

    render() {
      return (
        <div>
            <h1>Prescriptions</h1>
            <div className='Prescription-container'>
                <NewPrescription issuer={this.state.issuer} modal={this.props.modal} spinner={this.props.spinner} issuer={this.state.issuer}/>
                {this.state.prescriptions.map(i => <PrescriptionCard data={i} key={i.ID}/>)}
            </div>
        </div>
      );
    }
  }

export default Prescriptions;
