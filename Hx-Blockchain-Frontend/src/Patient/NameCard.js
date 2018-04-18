import React from 'react';
import BackendURL from '../BackendURL';



const nameCard = (props) => {


    const clicked = () => {
        try {
            let payload= null;
            let roleList = ['doctor','pharmacy','employer']
            for (let role in roleList){
                payload = {
                    patient: props.pd.owner,
                    accessor: props.inf,
                    a: 'REVOKE',
                    role: roleList[role]
                };
                
                console.log(payload)
                console.log(BackendURL + './' + props.type + 'Access')

                props.spinner(true);
                fetch(BackendURL + '/' + props.type + 'Access', {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(payload),
                    credentials: 'include'}) 
                    .then((response) => {
                        if (response.status === 200){
                            props.spinner(false);
                            props.modal("Access to " + props.inf + " revoked!")
                            return;
                        }
                    }).catch((e) => {
                            props.spinner(false);
                            props.modal("Network Error")
                        }); 
                }
        }catch (e){
            props.spinner(false);
            props.modal("Error")
        }
    }

    return (
    <div className='NameCard'>
        <div>
            <h2>{props.inf}</h2>
        </div>
        <button className='NameCard-btn' onClick={clicked}>Remove</button>
    </div>
    );
}

export default nameCard;
