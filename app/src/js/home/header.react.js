import React from 'react';
import SearchBar from './search-bar.react';

import '../../styles/header.scss';

import _ from 'lodash';

class Header extends React.Component {
  render() {
    return (
      <div className='header'>
        <h1>Pensieve</h1>
        <SearchBar onSearch={ this.props.onSearch } />
      </div>
    );
  }
}

Header.propTypes = {
  onSearch: React.PropTypes.func
};

Header.defaultProps = {
  onSearch: _.noop
};

export default Header;