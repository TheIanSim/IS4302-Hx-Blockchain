import React, { Component } from 'react';
import PrescriptionCard from './PrescriptionCard'


class Prescriptions extends Component {
    state = {
        prescriptions: this.props.prescriptions
    }

    render() {
      return (
        <div>
            <h1>Prescriptions</h1>
            <div className='Prescription-container'>
                {this.state.prescriptions.map(i => <PrescriptionCard data={i} key={i.ID} modal={this.props.modal} spinner={this.props.spinner}/>)}
            </div>
        </div>
      );
    }
  }

export default Prescriptions;
