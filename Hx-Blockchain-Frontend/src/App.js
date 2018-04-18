import React, { Component } from 'react';
import './App.css';
import Patient from './Patient/Patient';
import Doctor from './Doctor/Doctor';
import Pharmacy from './Pharmacy/Pharmacy';
import Employer from './Employer/Employer';
import Login from './Login';
import BackendURL from './BackendURL';
import Modal from './Common/Modal';
import Spinner from './Common/Spinner';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      userInfo: null,
      display: <Login click={this.loginHandler}/>,
      modal: null,
      spinner: null
    }
  }

  loginHandler = (cred) => {

    const username = cred.username;
    const password = cred.password;
    this.showSpinner(true);
    fetch(BackendURL + "/login?username=" + username + "&password=" + password + "&submit=Login", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                credentials: 'include'}) 

                .then((response) => {
                    if(response.status === 500){
                      this.wrongCredHandler();
                    }
                    return response.json();
                })
                    .then((myJson) => {     
                      this.showSpinner(false);               
                      let role = myJson['role']; //get role
                        if(role === 'PATIENT'){ 
                          this.setState({
                            ...this.state,
                            display: <Patient spinner={this.showSpinner} out={this.logoutHandler} data={myJson} modal={this.showModal}/>
                          })};

                        if(role === 'DOCTOR'){ 
                          this.setState({
                            ...this.state,
                            display: <Doctor spinner={this.showSpinner} out={this.logoutHandler} data={myJson} modal={this.showModal}/>
                          })};

                        if(role === 'PHARMACY'){ 
                          this.setState({
                            ...this.state,
                            display: <Pharmacy spinner={this.showSpinner} out={this.logoutHandler} data={myJson} modal={this.showModal}/>
                          })};

                        if(role === 'EMPLOYER'){ 
                          this.setState({
                            ...this.state,
                            display: <Employer spinner={this.showSpinner} out={this.logoutHandler} data={myJson}/>
                          })};

                    })
                    .catch((e) => {
                        this.showSpinner(false); 
                        this.showModal('Network Error');
                        console.log(e);
                    });
  }

  logoutHandler = () => {
    this.setState({
      userInfo: null,
      display: <Login click={this.loginHandler}/>
    })
  }

  wrongCredHandler = () => {
    this.showModal('invalid username or password');
  }

  showModal = (message) => {
    this.setState({
      ...this.state,
      modal: <Modal msg={message} close={this.closeModal}/>
    })
  }

  showSpinner = (show) => {
    if (show){
      this.setState({
        ...this.state,
        spinner: <Spinner />
      })
    }else{
      this.setState({
        ...this.state,
        spinner: null
      }) 
    }
  }

  closeModal = () => {
    this.setState({
      ...this.state,
      modal: null
    })
  }

  render() {
    return (
      <div className="App">
        {this.state.modal}
        {this.state.spinner}
        {this.state.display}
      </div>
    );
  }
}

export default App;
