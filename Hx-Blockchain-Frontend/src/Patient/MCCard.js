import React from 'react';

const MCCard = (props) => {

    const nameFormatter = (name) => {
        return name.split("#")[1];
    }

    return (
    <div className='PrescriptionCard'>
        <div>
            <h2><b>Issuer:  </b>{nameFormatter(props.data.issuer)}</h2>
            <h2><b>Issuee:  </b>{nameFormatter(props.data.issuee)}</h2>
            <p><b>Issued:  </b>{props.data.startDate}</p>
            <p><b>Duration:  </b>{props.data.duration}</p>
            <p><b>Remarks:  </b>{props.data.remark}</p>
        </div>
    </div>
    );
}

export default MCCard;
