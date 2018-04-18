import React from 'react';

const nameCard = (props) => {
    console.log(props)
    return (
    <div className='NameCard'>
        <div>
            <h2>{props.id}</h2>
        </div>
    </div>
    );
}

export default nameCard;
