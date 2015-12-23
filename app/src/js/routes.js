import React from 'react';
import { IndexRoute, Route, Redirect } from 'react-router';

import Home from './home/home.react';

export default (
    <Route component={ Home } path='/'>
    </Route>
);
