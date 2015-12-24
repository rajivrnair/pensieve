import React from 'react';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';

import _ from 'lodash';

class Memories extends React.Component {
  memoriesList(items) {
    return items.map(item => {
      return (
        <ListItem primaryText={ item.title } secondaryText={ item.content } onClick={ this.props.onItemClick.bind(null, item) } />
      );
    });
  }

  render() {
    return (
      <List>
        { this.memoriesList(this.props.memories) }
      </List>
    );
  }
}

Memories.propTypes = {
  onItemClick: React.PropTypes.func
};

Memories.defaultProps = {
  onItemClick: _.noop
};

export default Memories;