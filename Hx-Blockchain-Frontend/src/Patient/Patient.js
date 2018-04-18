import React, { Component } from 'react';
import PersonalDetails from '../Common/PersonalDetails';
import Navigation from '../Common/Navigation';
import Permissions from './Permissions';
import Prescriptions from './Prescriptions';
import MedicalCert from './MedicalCert';
import MedicalRec from './MedicalRec'
import EditInfo from './EditInfo';

class Patient extends Component {

    personalDet = JSON.parse(this.props.data['personalDetails'])[0];
    medicalCerts = JSON.parse(this.props.data['medicalCerts']);
    medicalInfo = JSON.parse(this.props.data['medicalInfo']);
    prescriptions = JSON.parse(this.props.data['prescriptions']);
    

    state = {
        currentDash: <div><h1>Welcome to HX Patient</h1><h1>Select an option to begin</h1></div>,
        currentName: null,
        pd: this.personalDet,
        permissions: JSON.parse(this.props.data['patientInfo']),
    }

    changeDashHandler = (page,pageName) => {
        this.setState({
            currentDash: page,
            currentName: pageName
        })
    }

    updateInfoHandler = (newPd) => {
        this.setState( {
            ...this.state,
            pd: newPd
        });
    }

    toggleInfoHandler = () => {
        this.setState({
            currentDash: <EditInfo pd={this.state.pd} confirm={this.updateInfoHandler} modal={this.props.modal} spinner={this.props.spinner}/>,
            currentName: 'edit info'
        })
    }

    
    routes = [
        ['permissions', <Permissions pd={this.state.pd} modal={this.props.modal} spinner={this.props.spinner} perm={this.state.permissions}/>],
        ['prescriptions', <Prescriptions prescriptions={this.prescriptions}/>],
        ['medical certificates', <MedicalCert certs={this.medicalCerts}/>],
        ['medical records', <MedicalRec recs={this.medicalInfo}/>],
        ]
    
  render() {
    return (
        <div className='Patient-grid'>

            <div className='Navigation'>
                <Navigation routes={this.routes} click={this.changeDashHandler} cur={this.state.currentName} out={this.props.out}/>
            </div>
            
            <div className='Dashboard'>
                {this.state.currentDash}
            </div>

            <div className='PersonalDetails'>
                <PersonalDetails pd={this.state.pd} editInfo={this.toggleInfoHandler} role={this.props.data.role}/>
            </div>

        </div>
    );
  }
}

export default Patient;
