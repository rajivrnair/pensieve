import React from 'react';
import List from 'material-ui/lib/lists/list';
import ListItem from 'material-ui/lib/lists/list-item';

class Memories extends React.Component {
  memoriesList(items) {
    return items.map(item => {
      return (
        <ListItem primaryText={ item.name } secondaryText={ item.snippet } />
      );
    });
  }

  render() {
    const items = [
      { name: 'blah', snippet: 'India is my country. All Indians are my brothers and sisters' },
      { name: 'puck', snippet: 'India is my country. All Indians are my brothers and sisters' }
    ];

    return (
      <List>
        { this.memoriesList(items) }
      </List>
    );
  }
}

export default Memories;