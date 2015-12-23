import axios from 'axios';
import uuid from 'uuid-v4';

import ActionTypes from './action-types';

const baseUrl = 'http://188.166.216.161/pensieve';

export function addMemory(memory) {
  const data = {
    id: uuid(),
    title: memory.title,
    content: memory.content
  };

  axios.post(`${baseUrl}/memories`, data);

  return {
    type: ActionTypes.ADD_MEMORY,
    memory: data
  };
};

export function getMemories() {
  axios.post(`${baseUrl}/memories`, {
    id: uuid.v4(),
    title: memory.title,
    content: memory.content
  });
};

export function clearForm() {
  return {
    type: ActionTypes.CLEAR_FORM
  };
};

export function setValues(memory) {
  return {
    type: ActionTypes.SET_VALUES,
    memory: memory
  };
};