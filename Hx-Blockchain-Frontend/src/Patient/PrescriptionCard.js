import React from 'react';

const prescriptionCard = (props) => {
    const class_n = (props.data.fulfilled? <div className='fulfilled'><h1>FULFILLED</h1></div> : null)
    return (
    <div className='PrescriptionCard'>
        <div>
            <h2><b>Prescription ID:</b> {props.data.preId}</h2>
            <p><b>Issuer:</b> {props.data.issuer}</p>
            <p><b>Issued to:</b> {props.data.issuee}</p>
            <p><b>Issue date: </b> {props.data.date}</p>
            {props.data.drugs.map(
                (i) => {
                    return <p key={i.drugName+i.drugQty}>{i.drugName} ({i.drugQty})</p>
                }
            )}
        </div>
        {class_n}
    </div>
    );
}

export default prescriptionCard;
