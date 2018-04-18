import React, { Component } from 'react';
import NameCard from './NameCard';
import InputComponent from './InputComponent';
import BackendURL from '../BackendURL';


class Permissions extends Component {
    state = {
        mc: this.props.perm[0].mcPermissions,
        pre: this.props.perm[0].prePermissions,
        info: this.props.perm[0].infoPermissions
    }

    authNew = (ID,field) => {
        let curList = this.state[field];
        let newList = [ ...curList ];
        newList.push(ID);
        let newState = {...this.state};
        newState[field] = newList;
        this.setState(newState);
    }

    remove = (inf,type) => {
        
        
    }

    render() {
      return (
        <div>
        <h1>Permissions</h1>
        <div className='Permissions-grid'>
            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0, fontWeight:400 , textTransform:'uppercase'}}>Medical Certificates</h1>
                <InputComponent update={this.authNew} url={"/mcAccess"} pd={this.props.pd} modal={this.props.modal} spinner={this.props.spinner}/>
                {this.state.mc.map(i => <NameCard modal={this.props.modal} spinner={this.props.spinner} type={'mc'} inf={i} key={i} click={() => this.remove} pd={this.props.pd}/>)}
            </div>

            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0, fontWeight:400 , textTransform:'uppercase'}}>Prescriptions</h1>
                <InputComponent update={this.authNew} url={"/preAccess"} pd={this.props.pd} modal={this.props.modal} spinner={this.props.spinner}/>
                {this.state.pre.map(i => <NameCard modal={this.props.modal} spinner={this.props.spinner} type={'pre'} inf={i} key={i} click={() => this.remove} pd={this.props.pd}/>)}
            </div>

            <div className='Perm-grid-item'>
                <h1 style={{marginBottom:0, fontWeight:400 , textTransform:'uppercase'}}>Medical Information</h1>
                <InputComponent update={this.authNew} url={"/infoAccess"} pd={this.props.pd} modal={this.props.modal} spinner={this.props.spinner}/>
                {this.state.info.map(i => <NameCard modal={this.props.modal} spinner={this.props.spinner} type={'info'} inf={i} key={i} click={() => this.remove} pd={this.props.pd}/>)}
            </div>

        </div>
    </div>
      );
    }
  }

export default Permissions;
