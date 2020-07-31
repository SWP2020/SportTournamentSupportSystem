import { all } from 'redux-saga/effects';
import { login } from './sagas/Login';
import { signUp } from './sagas/SignUp';
import { showNotification } from './sagas/Common';
import { queryBracketBoardInfo } from './sagas/BracketBoard';
import { queryUserInfo } from './sagas/QueryUserInfo';
import { logOut } from './sagas/LogOut';
import { queryTournamentInfo } from './sagas/QueryTournamentInfo';
import { isUsernameExisted } from './sagas/IsUsernameExisted';
import { queryListTournamentsOfUser } from './sagas/QueryListTournamentsOfUser';

export default function* () {
  yield all([
    login(),
    signUp(),
    showNotification(),
    queryBracketBoardInfo(),
    queryUserInfo(),
    logOut(),
    queryTournamentInfo(),
    isUsernameExisted(),
    queryListTournamentsOfUser(),
  ]);
}