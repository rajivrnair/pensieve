import React from 'react';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';

import '../../styles/search-bar.scss';

class SearchBar extends React.Component {
  searchBar() {
    return (
      <div className='wrapper'>
        <TextField
          style={{ width: '100%', color: '#fff' }}
          hintStyle={{ width: '100%', color: '#c5cae9', transform: 'scale(1) rotate(0deg) translate(0px, 0px) skew(-8deg, 0deg)' }}
          inputStyle={{ width: '100%', color: '#c5cae9' }}
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

export default SearchBar;