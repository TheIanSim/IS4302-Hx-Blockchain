import React, { Component } from 'react';
import MCCard from './MCCard'


class MedicalCerts extends Component {

    state = {
        rawCerts: this.props.certs
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
                    {this.state.rawCerts.map(this.formatCerts)}
                </div>
            </div>
        );
    }
  }

export default MedicalCerts;
