import React, {Component} from 'react';
import RecordCard from './RecordCard';

class MedicalRec extends Component {

    state = {
        rawRec: this.props.recs
    }

    formatCerts = (rec) => {
        let recData = {
            ...rec
        }
        delete recData.$class;
        return <RecordCard data={recData} key={recData.infoId}/>
    }

    render() {
        return (
            <div className='MedicalRec'>
                <h1>Medical Records</h1>
                <div className='MedicalRec-container'>
                    {this.state.rawRec.map(this.formatCerts)}
                </div>
            </div>
        );
    }
  }

export default MedicalRec;
