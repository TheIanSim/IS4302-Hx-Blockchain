import React, { Component } from 'react';
import PersonalDetails from './PersonalDetailsPharm';
import Navigation from '../Common/Navigation';
import Prescriptions from './Prescriptions';
//import Permissions from './Permissions';

class Pharmacy extends Component {

    state = {
        currentDash: <div><h1>Welcome to HX Pharmacy</h1><h1>Select an option to begin</h1></div>,
        currentName: null,
        prescriptions: JSON.parse(this.props.data['prescriptions']),
        pd: JSON.parse(this.props.data.personalDetails)[0]
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

    
    routes = [
        //['permissions', <Permissions pd={this.state.pd} modal={this.props.modal} spinner={this.props.spinner}/>],
        ['prescriptions', <Prescriptions  prescriptions={this.state.prescriptions} modal={this.props.modal} spinner={this.props.spinner}/>],
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
                <PersonalDetails pd={this.state.pd} role={this.props.data.role}/>
            </div>

        </div>
    );
  }
}

export default Pharmacy;
