import React from 'react';
import ReactDOM from 'react-dom';
import Router from 'react-router';
import routes from './routes';

const app = document.getElementById('app');

ReactDOM.render(
  <Router>{ routes }</Router>, app
);
