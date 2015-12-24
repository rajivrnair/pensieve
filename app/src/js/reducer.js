import MemoryActions from './home/actions';
import MemoryActionTypes from './home/action-types';

const memoriesInitialState = {
  collection: []
};

const uiInitialState = {
  search: {
    criteria: ''
  },
  form: { title: '', content: '' },
  detailView: null
};

function uiState(state = uiInitialState, action) {
  switch (action.type) {
    case MemoryActionTypes.SEARCH_MEMORY:
      return Object.assign({}, state, { search: { criteria: action.criteria } });
      break;
    case MemoryActionTypes.CLEAR_FORM:
      return Object.assign({}, state, { form: { title: '', content: '' } });
      break;
    case MemoryActionTypes.SET_VALUES:
      return Object.assign({}, state, { form: action.memory });
      break;
    case MemoryActionTypes.SHOW_MEMORY_DETAIL:
      return Object.assign({}, state, { detailView: action.item });
      break;
    default:
      return state;
  }
}

function memories(state = memoriesInitialState, action) {
  switch (action.type) {
    case MemoryActionTypes.ADD_MEMORY:
      return {
        collection: [...state.collection, action.memory]
      };
    case MemoryActionTypes.GET_MEMORIES:
      return {
        collection: action.collection
      };
    default:
      return state;
  }
}

function PensieveApp(state = { ui: uiInitialState, memories: memoriesInitialState }, action) {
  return {
    ui: uiState(state.uiState, action),
    memories: memories(state.memories, action)
  };
}

export default PensieveApp;