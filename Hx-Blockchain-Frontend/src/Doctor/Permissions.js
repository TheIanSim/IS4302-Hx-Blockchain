import React, { Component } from 'react';
import NameCard from './NameCard';


class Permissions extends Component {
    state = {
        mc: this.props.perm[0].mcPermissions,
        pres: this.props.perm[0].prePermissions,
        info: this.props.perm[0].infoPermissions
    }

    render() {
        console.log(this.props.perm)
      return (
        <div>
        <h1>Permissions</h1>
        <div className='Permissions-grid'>
            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0}}>Medical Certificates</h1>
                {this.state.mc.map(i => <NameCard id={i} key={i}/>)}
            </div>

            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0}}>Prescriptions</h1>
                {this.state.pres.map(i => <NameCard id={i} key={i}/>)}
            </div>

            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0}}>Medical Information</h1>
                {this.state.info.map(i => <NameCard id={i} key={i}/>)}
            </div>

        </div>
    </div>
      );
    }
  }

export default Permissions;
