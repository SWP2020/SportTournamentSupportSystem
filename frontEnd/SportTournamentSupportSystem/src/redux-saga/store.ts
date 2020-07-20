import {
  applyMiddleware,
  createStore
} from 'redux';
import sagaMiddlewareFactory from 'redux-saga';
import { appReducer } from './reducers';
import sagas from './sagas';

const sagaMiddleware = sagaMiddlewareFactory();

const store = createStore(
  appReducer,
  applyMiddleware(sagaMiddleware)
);

sagaMiddleware.run(sagas);

export default store;