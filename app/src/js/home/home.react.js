import React from 'react';

import ColorTheme from '../color-theme';
import ThemeManager from 'material-ui/lib/styles/theme-manager';
import FloatingActionButton from 'material-ui/lib/floating-action-button';
import ToggleStar from 'material-ui/lib/svg-icons/toggle/star';

import Memories from './memories.react';
import Header from './header.react';
import AddMemory from './add-memory.react';

import { addMemory, clearForm, setValues, getMemories, search } from './actions';
import { connect } from 'react-redux'

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
    return (
      <div className='content'>
        <Memories memories={ this.props.memories.collection }/>
        <AddMemory onAddMemory={ this.onAddMemory } values={ this.props.ui.form } onValueChange={ this.onValueChange } />
      </div>
    );
  }

  render() {
    return (
      <div className='home-page'>
        <Header onSearch={ this.onSearch } />
        { this.content() }
        <FloatingActionButton style={{ position: 'fixed', right: '3%', bottom: '5%' }}><ToggleStar /></FloatingActionButton>
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

    if (regex.test(memory.content)) {
      return true;
    }

    if (regex.test(memory.title)) {
      return true;
    }

    if (regex.test(memory.tags.join(' '))) {
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
