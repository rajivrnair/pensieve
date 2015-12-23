import React from 'react';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';

class Memories extends React.Component {
  memoriesList(items) {
    return items.map(item => {
      return (
        <ListItem primaryText={ item.title } secondaryText={ item.content } />
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

export default Memories;