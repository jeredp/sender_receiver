import React, { Component } from 'react';
import logo from '../images/logo.svg';
import '../css/Header.css';

export class Header extends Component {
  render() {
    return (
      <div className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2>JPHILI</h2>
      </div>
    );
  }
}