import React from 'react';
import logo from "../images/health.png";

const navigation = (props) => {

    const routes = props.routes;

    return (
    <div>
        <img src={logo} alt="http://sweetclipart.com/multisite/sweetclipart/files/health_legal_caduceus_logo_lineart.png" className='nav-logo' />
        <h1>Navigation</h1>
        <ul>
            {routes.map(i => {
                return <li key={i[0]} onClick={() => props.click(i[1], i[0])} className={props.cur === i[0] ? 'active' : null}>{i[0]}</li>
                })}
            <li onClick={props.out}>Log-Out</li>
        </ul>
    </div>
    );
}
  


export default navigation;
