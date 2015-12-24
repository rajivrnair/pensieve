import React from 'react';

import Dialog from 'material-ui/lib/dialog';
import FlatButton from 'material-ui/lib/flat-button';

import { showMemoryDetail } from './actions';

import marked from 'marked';

import highlight from 'highlight.js';
import '../../styles/detail-view.scss';

class DetailView extends React.Component {
  tags(itemForDisplay) {
    return itemForDisplay.tags.split(',').map(tag => {
      return (
        <li className='tag fa fa-hashtag'>
          <span>{ tag }</span>
        </li>
      );
    });
  }

  dialog() {
    const { dispatch, item } = this.props;

    let itemForDisplay;

    if (_.isEmpty(item)) {
      itemForDisplay = { title: '', content: '', tags: '' };
    } else {
      itemForDisplay = item;
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

    marked.setOptions({
      highlight: function (code) {
        return require('highlight.js').highlightAuto(code).value;
      }
    });


    return (
      <Dialog
        title={itemForDisplay.title}
        actions={customActions}
        actionFocus="submit"
        open={!_.isEmpty(item)}
        onRequestClose={handleOK}>
        <div>
          <div className='detail-view' dangerouslySetInnerHTML={{__html: marked(itemForDisplay.content)}}></div>
          <ul className='tags'>
            { this.tags(itemForDisplay) }
          </ul>
        </div>
      </Dialog>
    );
  }

  render() {
    return (
      <div>
        { this.dialog() }
      </div>
    );
  }
}

export default DetailView;