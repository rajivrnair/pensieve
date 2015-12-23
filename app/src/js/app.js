import React from 'react';
import ReactDOM from 'react-dom';
import Router from 'react-router';
import routes from './routes';
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import reducer from './reducer';
import Home from './home/home.react';

let store = createStore(reducer);

const app = document.getElementById('app');

ReactDOM.render(
  <Provider store={ store }>
    <Home />
  </Provider>
, app);
