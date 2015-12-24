import axios from 'axios';
import uuid from 'uuid-v4';

import ActionTypes from './action-types';

const baseUrl = 'http://188.166.216.161/pensieve';

export function addMemory(memory) {
  const data = {
    id: uuid(),
    title: memory.title,
    content: memory.content,
    tags: memory.tags
  };

  axios.post(`${baseUrl}/memories`, data);

  return {
    type: ActionTypes.ADD_MEMORY,
    memory: data
  };
};

export function getMemories() {
  return axios.get(`${baseUrl}/memories`).then(response => {
    return {
      collection: response.data,
      type: ActionTypes.GET_MEMORIES
    };
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
    memory
  };
};

export function search(criteria) {
  return {
    type: ActionTypes.SEARCH_MEMORY,
    criteria
  };
};

export function showMemoryDetail(item) {
  return {
    type: ActionTypes.SHOW_MEMORY_DETAIL,
    item
  };
};