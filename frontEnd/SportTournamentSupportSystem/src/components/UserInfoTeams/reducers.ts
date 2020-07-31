import { IAction, IParams } from "interfaces/common";

export const QUERY_LIST_TEAM_OF_USER_SUCCESS = 'QUERY_LIST_TEAM_OF_USER_SUCCESS';
export const QUERY_LIST_TEAM_OF_USER_FAILED = 'QUERY_LIST_TEAM_OF_USER_FAILED';

export function ListTeamOfUser(state: IParams | null = null, action: IAction<IParams>) {
  switch (action.type) {
    case QUERY_LIST_TEAM_OF_USER_SUCCESS:
      return action.payload;
    case QUERY_LIST_TEAM_OF_USER_FAILED:
      return null;
    default:
      return state;
  }
}