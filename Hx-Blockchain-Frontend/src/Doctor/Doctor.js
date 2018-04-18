import React, { Component } from 'react';
import PersonalDetails from '../Common/PersonalDetails';
import Navigation from '../Common/Navigation';
import Permissions from './Permissions';
import Prescriptions from './Prescriptions';
import MedicalCert from './MedicalCert';
import MedicalRec from './MedicalRec';
import EditInfo from '../Patient/EditInfo';

class Doctor extends Component {

    state = {
        currentDash: <div><h1>Welcome to HX Practitioner</h1><h1>Select an option to begin</h1></div>,
        currentName: null,
        pd: JSON.parse(this.props.data['personalDetails'])[0],
        medicalInfo: JSON.parse(this.props.data['medicalInfo']),
        prescriptions: JSON.parse(this.props.data['prescriptions']),
        permissions: JSON.parse(this.props.data['permissions']),
       // medicalCerts: JSON.parse(this.props.data['medicalCerts']),

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
            currentDash: <EditInfo spinner={this.props.spinner} pd={this.state.pd} confirm={this.updateInfoHandler} modal={this.props.modal}/>,
            currentName: 'edit info'
        })
    }

    
    routes = [
        ['permissions', <Permissions pd={this.state.pd} modal={this.props.modal} perm={this.state.permissions}/>],
        ['prescriptions', <Prescriptions pd={this.state.pd} prescriptions={this.state.prescriptions} modal={this.props.modal} spinner={this.props.spinner}/>],
        ['medical records', <MedicalRec recs={this.state.medicalInfo} pd={this.state.pd} modal={this.props.modal} spinner={this.props.spinner}/>],
        ['medical certificates', <MedicalCert pd={this.state.pd} certs={this.medicalCerts} modal={this.props.modal} spinner={this.props.spinner}/>],
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

export default Doctor;
