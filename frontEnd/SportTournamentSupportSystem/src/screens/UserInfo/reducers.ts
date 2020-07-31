import { IAction, IParams } from "interfaces/common";

export const QUERY_USER_INFO_SUCCESS = 'QUERY_USER_INFO_SUCCESS';
export const QUERY_USER_INFO_FAILED = 'QUERY_USER_INFO_FAILED';

export function UserInfo(state: IParams | null = null, action: IAction<IParams>) {
  switch (action.type) {
    case QUERY_USER_INFO_SUCCESS:
      return action.payload;
    case QUERY_USER_INFO_FAILED:
      return null;
    default:
      return state;
  }
}