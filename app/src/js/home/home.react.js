import React from 'react';

import ColorTheme from '../color-theme';
import ThemeManager from 'material-ui/lib/styles/theme-manager';
import ToggleStar from 'material-ui/lib/svg-icons/toggle/star';
import Dialog from 'material-ui/lib/dialog';
import FlatButton from 'material-ui/lib/flat-button';

import _ from 'lodash';
import { connect } from 'react-redux'

import Memories from './memories.react';
import Header from './header.react';
import AddMemory from './add-memory.react';

import { addMemory, clearForm, setValues, getMemories, search, showMemoryDetail } from './actions';

import '../../styles/home.scss';

class Home extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      muiTheme: ThemeManager.getMuiTheme(ColorTheme),
    };

    this.onAddMemory = this.onAddMemory.bind(this);
    this.onValueChange = this.onValueChange.bind(this);
    this.onSearch = this.onSearch.bind(this);
  }

  componentDidMount() {
    const { dispatch } = this.props;

    getMemories().then(memories => dispatch(memories));
  }

  getChildContext () {
    return {
      muiTheme: this.state.muiTheme,
    };
  }

  onSearch(keyword) {
    const { dispatch } = this.props;

    dispatch(search(keyword));
  }

  onAddMemory(memory) {
    const { dispatch } = this.props;

    dispatch(addMemory(memory));
    dispatch(clearForm());
  }

  onValueChange(memory) {
    const { dispatch } = this.props;

    dispatch(setValues(memory));
  }

  content() {
    const { dispatch } = this.props;

    const onItemClick = item => {
      dispatch(showMemoryDetail(item));
    };

    return (
      <div className='content'>
        <Memories memories={ this.props.memories.collection } onItemClick={ onItemClick } />
        <AddMemory onAddMemory={ this.onAddMemory } values={ this.props.ui.form } onValueChange={ this.onValueChange } />
      </div>
    );
  }

  detailView() {
    const item = this.props.ui.detailView;
    const { dispatch } = this.props;

    if (_.isEmpty(item)) {
      return null;
    }

    const handleOK = () => {
      dispatch(showMemoryDetail(null));
    };

    let customActions = [
      <FlatButton
        label="Got it!"
        primary={true}
        onClick={handleOK} />
    ];

    return (
      <Dialog
        title={item.title}
        actions={customActions}
        actionFocus="submit"
        open={!_.isEmpty(item)}
        onRequestClose={handleOK}>
      </Dialog>
    );
  }

  render() {
    return (
      <div className='home-page'>
        <Header onSearch={ this.onSearch } />
        { this.content() }
        { this.detailView() }
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

function searchMemories(memories, criteria) {
  return memories.filter(memory => {
    const regex = new RegExp(criteria, 'i');

    if (regex.test(memory.content) || regex.test(memory.title) || regex.test(memory.tags)) {
      return true;
    }

    return false;
  });
}

function select(state) {
  return {
    memories: {
      collection: searchMemories(state.memories.collection, state.ui.search.criteria),
    },
    ui: state.ui
  };
}

// Wrap the component to inject dispatch and state into it
export default connect(select)(Home);
