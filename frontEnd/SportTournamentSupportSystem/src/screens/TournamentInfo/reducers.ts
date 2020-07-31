import { IAction, IParams } from "interfaces/common";

export const QUERY_TOURNAMENT_INFO_SUCCESS = 'QUERY_TOURNAMENT_INFO_SUCCESS';
export const QUERY_TOURNAMENT_INFO_FAILED = 'QUERY_TOURNAMENT_INFO_FAILED';

export function TournamentInfo(state: IParams | null = null, action: IAction<IParams>) {
  switch (action.type) {
    case QUERY_TOURNAMENT_INFO_SUCCESS:
      return action.payload;
    case QUERY_TOURNAMENT_INFO_FAILED:
      return null;
    default:
      return state;
  }
}