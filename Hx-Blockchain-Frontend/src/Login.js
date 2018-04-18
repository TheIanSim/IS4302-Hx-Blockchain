import React, { Component } from 'react';
import './App.css';
import logo from "./images/health.png";

class Login extends Component {

    state = {
        username: '',
        password: ''
    }

    usernameChangeHandler = (event) => {
        this.setState({
            ...this.state,
            username: event.target.value
        });
    }

    passwordChangeHandler = (event) => {
        this.setState({
            ...this.state,
            password: event.target.value
        });
    }

    
  render() {
    return (
        <div className='LoginContainer'>

            <div className='LoginBox'>
            <img src={logo} alt="http://sweetclipart.com/multisite/sweetclipart/files/health_legal_caduceus_logo_lineart.png" className='nav-logo' />
            <h1><b>HX</b> Login</h1>
            <input className='LoginInput' type="text" placeholder="Username" name="uname" required onChange={this.usernameChangeHandler} />
            <br/>
            <input className='LoginInput' type="password" placeholder="Password" name="psw" required onChange={this.passwordChangeHandler}/>
            <br/>
            <button className='LoginBtn' type="submit" onClick={() => this.props.click(this.state)}>Enter</button>
            </div>

        </div>
    );
  }
}

export default Login;
