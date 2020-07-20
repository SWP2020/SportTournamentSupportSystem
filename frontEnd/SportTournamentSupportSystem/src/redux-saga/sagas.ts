import { all } from 'redux-saga/effects';
import { login } from './sagas/Login';
import { showNotification } from './sagas/Common';
import { queryBracketBoardInfo } from './sagas/BracketBoard';

export default function* () {
  yield all([
    login(),
    showNotification(),
    queryBracketBoardInfo(),
  ]);
}