import React from 'react';

class Modal extends React.Component {
  render() {

    const backdropStyle = {
      position: 'fixed',
      top: 0,
      bottom: 0,
      left: 0,
      right: 0,
      backgroundColor: 'rgba(0,0,0,0.3)',
      padding: 50
    };

    const modalStyle = {
      backgroundColor: '#fff',
      borderRadius: 5,
      maxWidth: 500,
      minHeight: 300,
      margin: '0 auto',
      position: 'relative'
    };


    return (
      <div className="backdrop" style={backdropStyle}>
        <div className="modal" style={modalStyle}>
        <h1>{this.props.msg}</h1>

          <div className="modalButton">
            <button onClick={this.props.close}>
              Close
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default Modal;