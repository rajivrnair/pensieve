import React from 'react';
import { IndexRoute, Route, Redirect } from 'react-router';

import Home from './pages/home.react';
// import Login from './pages/Login.react';

export default (
    <Route component={ Home } path='/'>
    </Route>
);
