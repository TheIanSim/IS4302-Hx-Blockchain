import React, { Component } from 'react';
import MCCard from '../Patient/MCCard';
import NewMC from './NewMC';


class MedicalCerts extends Component {

    state = {
        rawCerts: this.props.certs,
        issuer: this.props.pd.owner
    }

    formatCerts = (cert) => {
        let certData = {
            ...cert
        }
        delete certData.$class;
        return <MCCard data={certData} key={certData.mcId}/>
    }

    render() {
        return (
            <div>
                <h1>Medical Certificates</h1>
                <div className='MedicalCerts-container'>
                    <NewMC issuer={this.state.issuer} modal={this.props.modal} spinner={this.props.spinner}/>
                </div>
            </div>
        );
    }
  }

export default MedicalCerts;
