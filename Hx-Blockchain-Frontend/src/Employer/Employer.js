import React, { Component } from 'react';
import PersonalDetails from '../Common/PersonalDetails';
import Navigation from '../Common/Navigation';
//import Permissions from './Permissions';
import MedicalCert from '../Patient/MedicalCert';


class Employer extends Component {

    

    state = {
        currentDash: <div><h1>Welcome to HX Patient</h1><h1>Select an option to begin</h1></div>,
        currentName: null,
        medicalCerts: JSON.parse(this.props.data['medicalCerts']),
        pd: JSON.parse(this.props.data.personalDetails)
    }

    changeDashHandler = (page,pageName) => {
        this.setState({
            currentDash: page,
            currentName: pageName
        })
    }

    routes = [
        //['permissions', <Permissions pd={this.state.pd} modal={this.props.modal} spinner={this.props.spinner}/>],
        ['medical certificates', <MedicalCert certs={this.state.medicalCerts}/>],
 
        ]
    
  render() {
      console.log(this.state)
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

export default Employer;
