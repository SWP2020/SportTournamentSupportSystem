import { combineReducers } from 'redux';
import { CurrentPage } from './global-reducers/CurrentPage';
import { BracketBoardInfo, BracketStartedStatus } from 'components/BracketBoard/reducers';
import { HoveringTeam } from 'components/BracketTeam/reducers';

export const appReducer = combineReducers({
  currentPage: CurrentPage,
  bracketBoardInfo: BracketBoardInfo,
  hoveringTeam: HoveringTeam,
  bracketStartedStatus: BracketStartedStatus,
});

export type IState = ReturnType<typeof appReducer>;