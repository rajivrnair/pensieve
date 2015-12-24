import React from 'react';
import TextField from 'material-ui/lib/text-field';
import Card from 'material-ui/lib/card/card';
import CardTitle from 'material-ui/lib/card/card-title';
import CardText from 'material-ui/lib/card/card-text';
import CardHeader from 'material-ui/lib/card/card-header';
import FlatButton from 'material-ui/lib/flat-button';
import CardActions from 'material-ui/lib/card/card-actions';

import _ from 'lodash';

import '../../styles/add-memory.scss';

class AddMemory extends React.Component {
  constructor(props) {
    super(props);

    this.onSave = this.onSave.bind(this);
    this.onValueChange = this.onValueChange.bind(this);
  }

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

  onSave() {
    this.props.onAddMemory(this.props.values);
  }

  onValueChange(key, e) {
    let values = this.props.values;

    const memory = Object.assign({}, values, { [key]: e.target.value });
    this.props.onValueChange(memory);
  }

  render() {
    return (
      <Card className='add-memory-card'>
        <CardHeader title="Add a memory" avatar={<span />} />
        <CardText>
          <div className='add-memory' ref='content'>
            <TextField hintText="Title" floatingLabelText="Title" style={{ width: '100%', color: '#fff' }} onChange={ this.onValueChange.bind(null, 'title') } value={ this.props.values.title } />
            <TextField floatingLabelText="Snippet" multiLine style={{ width: '100%', color: '#fff' }} onChange={ this.onValueChange.bind(null, 'content') } value={ this.props.values.content } />
            <TextField hintText="Tags" floatingLabelText="Tags" style={{ width: '100%', color: '#fff' }} onChange={ this.onValueChange.bind(null, 'tags') } value={ this.props.values.tags } />
          </div>
        </CardText>
        <CardActions style={{ textAlign: 'right' }}>
          <FlatButton label="Reset" onTouchTap={this.onReset} />
          <FlatButton label="Save" onClick={this.onSave} />
        </CardActions>
      </Card>
    );
  }
}

AddMemory.propTypes = {
  onAddMemory: React.PropTypes.func,
  values: React.PropTypes.shape({
    title: React.PropTypes.string,
    content: React.PropTypes.string
  })
};

AddMemory.defaultProps = {
  onAddMemory: _.noop,
  onValueChange: _.noop
};

export default AddMemory;