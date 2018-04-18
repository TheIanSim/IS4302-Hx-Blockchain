import React, { Component } from 'react';
import RecordCard from '../Patient/RecordCard';
import NewRecord from './NewRecord';


class MedicalRec extends Component {

    state = {
        rawCerts: this.props.recs,
        issuer: this.props.pd.owner
    }

    formatRec = (rec) => {
        let recData = {
            ...rec
        }
        delete recData.$class;
        return <RecordCard data={recData} key={recData.recId}/>
    }

    render() {
        console.log(this.props.certs)
        return (
            <div>
                <h1>Medical Records</h1>
                <NewRecord issuer={this.state.issuer} modal={this.props.modal} spinner={this.props.spinner}/>
                <div className='MedicalCerts-container'>
                    {this.state.rawCerts.map(this.formatRec)}
                </div>
            </div>
        );
    }
  }

export default MedicalRec;
