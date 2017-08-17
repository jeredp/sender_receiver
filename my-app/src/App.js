import React, { Component } from 'react';
import { Router, Route } from 'react-router'
import './App.css';
import { Header } from './global/components/Header.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header/>
        <Router>
          <Route path="/" component={App} />
        </Router>
      </div>
    );
  }
}

export default App;
