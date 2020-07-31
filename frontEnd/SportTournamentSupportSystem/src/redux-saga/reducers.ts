import { combineReducers } from 'redux';
import { CurrentPage } from './global-reducers/CurrentPage';
import { BracketBoardInfo, BracketStartedStatus } from 'components/BracketBoard/reducers';
import { HoveringTeam } from 'components/BracketTeam/reducers';
import { CurrentUserInfo } from 'screens/Login/reducers';
import { SignUp } from 'screens/SignUp/reducers';
import { ForgotPassword } from 'screens/ForgotPassword/reducers';
import { UserInfo } from 'screens/UserInfo/reducers';
import { TournamentInfo } from 'screens/TournamentInfo/reducers';
import { IsUsernameExisted } from 'components/TournamentSetting/reducers';
import { ListTournamentOfUser } from 'components/UserInfoTournament/reducers';
import { ListTeamOfUser } from 'components/UserInfoTeams/reducers';

export const appReducer = combineReducers({
  currentPage: CurrentPage,
  bracketBoardInfo: BracketBoardInfo,
  hoveringTeam: HoveringTeam,
  bracketStartedStatus: BracketStartedStatus,
  currentUserInfo: CurrentUserInfo,
  userInfo: UserInfo,
  signUp: SignUp,
  forgotPassword: ForgotPassword,
  tournamentInfo: TournamentInfo,
  isUsernameExisted: IsUsernameExisted,
  listTournamentOfUser: ListTournamentOfUser,
  listTeamOfUser: ListTeamOfUser,
});

export type IState = ReturnType<typeof appReducer>;