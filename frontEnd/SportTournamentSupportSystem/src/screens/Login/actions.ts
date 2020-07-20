import { IParams } from "interfaces/common";
import { LOGIN } from "redux-saga/actions";
import { LOGIN_SUCCESS, LOGIN_FAILED } from "./reducers";

export const login = (data: IParams) => ({
  type: LOGIN,
  response: {
    success: LOGIN_SUCCESS,
    failed: LOGIN_FAILED,
  },
  data,
});