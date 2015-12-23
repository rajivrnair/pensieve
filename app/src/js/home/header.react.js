import React from 'react';
import SearchBar from './search-bar.react';

import '../../styles/header.scss';

class Header extends React.Component {
  render() {
    return (
      <div className='header'>
        <h1>Pensieve</h1>
        <SearchBar />
      </div>
    );
  }
}

export default Header;