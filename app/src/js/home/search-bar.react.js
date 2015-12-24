import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';

import '../../styles/search-bar.scss';

import ActionTypes from './action-types';

import _ from 'lodash';

class SearchBar extends React.Component {
  searchBar() {
    const onSearch = (ev) => {
      this.props.onSearch(ev.target.value);
    };

    return (
      <div className='wrapper'>
        <TextField
          style={{ width: '100%', color: '#fff' }}
          hintStyle={{ width: '100%', color: '#c5cae9', transform: 'scale(1) rotate(0deg) translate(0px, 0px) skew(-8deg, 0deg)' }}
          inputStyle={{ width: '100%', color: '#c5cae9' }}
          onChange={ onSearch }
          hintText='Search...' />
      </div>
    );
  }

  render() {
    return (
      <div className='search-bar'>
        { this.searchBar() }
      </div>
    );
  }
}

SearchBar.propTypes = {
  onSearch: React.PropTypes.func
};

SearchBar.defaultProps = {
  onSearch: _.noop
};

export default SearchBar;