import { IAction, IParams } from "interfaces/common";

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILED = 'LOGIN_FAILED';

export function LoginToken(state: IParams | null = null, action: IAction<IParams>) {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return action.payload;
    case LOGIN_FAILED:
      return null;
    default:
      return state;
  }
}