import React from 'react';
import avatar from '../images/img_avatar.png'

const personalDetails = (props) => {
    const pd = props.pd;
    let info = [];
    Object.keys(pd).forEach( (k) => {
        if(k !== 'prePermissions' && k !== '$class' && k !== 'detailsId' && k !== 'owner'){
            info.push(<li key={k}>{k}: <b>{pd[k]}</b></li>);
        }
    })
    return (
    <div>
        <img src={avatar} alt="https://www.w3schools.com/howto/img_avatar.png" className='avatar' />
        <h1>{pd.firstname} <b>{pd.lastname}</b></h1>
        <h2>{props.role}</h2>
        <ul>
            {info}
        </ul>
    </div>
    );
}

export default personalDetails;
