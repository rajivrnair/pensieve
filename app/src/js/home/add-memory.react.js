import React from 'react';
import TextField from 'material-ui/lib/text-field';
import Card from 'material-ui/lib/card/card';
import CardTitle from 'material-ui/lib/card/card-title';
import CardText from 'material-ui/lib/card/card-text';
import CardHeader from 'material-ui/lib/card/card-header';
import FlatButton from 'material-ui/lib/flat-button';
import CardActions from 'material-ui/lib/card/card-actions';

import '../../styles/add-memory.scss';

class AddMemory extends React.Component {
  componentDidMount() {
    this.refs.content.querySelectorAll('textarea')[1].addEventListener('keydown', function(e) {
      if (e.keyCode === 9) {
        let val = this.value,
            start = this.selectionStart,
            end = this.selectionEnd;

        this.value = val.substring(0, start) + '\t' + val.substring(end);
        this.selectionStart = this.selectionEnd = start + 1;

        e.preventDefault();
        return false;
      }
    });
  }

  render() {
    return (
      <Card className='add-memory-card'>
        <CardHeader title="Add a memory" avatar={<span />} />
        <CardText>
          <div className='add-memory' ref='content'>
            <TextField hintText="Title" floatingLabelText="Title" style={{ width: '100%', color: '#fff' }} />
            <TextField  floatingLabelText="Snippet" multiLine style={{ width: '100%', color: '#fff' }} />
          </div>
        </CardText>
        <CardActions style={{ textAlign: 'right' }}>
            <FlatButton label="Reset"/>
            <FlatButton label="Save"/>
          </CardActions>
      </Card>
    );
  }
}

export default AddMemory;