import React from 'react';

import ColorTheme from '../color-theme';
import ThemeManager from 'material-ui/lib/styles/theme-manager';
import FloatingActionButton from 'material-ui/lib/floating-action-button';
import ToggleStar from 'material-ui/lib/svg-icons/toggle/star';

import Memories from './memories.react';
import Header from './header.react';
import AddMemory from './add-memory.react';

import '../../styles/home.scss';

class Home extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      muiTheme: ThemeManager.getMuiTheme(ColorTheme),
    };
  }

  getChildContext () {
    return {
      muiTheme: this.state.muiTheme,
    };
  }

  content() {
    return (
      <div className='content'>
        <Memories />
        <AddMemory />
      </div>
    );
  }

  render() {
    return (
      <div className='home-page'>
        <Header />
        { this.content() }
        <FloatingActionButton style={{ position: 'fixed', right: '3%', bottom: '5%' }}><ToggleStar /></FloatingActionButton>
      </div>
    );
  }
}

Home.contextTypes = {
  muiTheme: React.PropTypes.object
};

Home.childContextTypes = {
  muiTheme: React.PropTypes.object
};

export default Home;