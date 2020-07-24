import { IParams } from "interfaces/common";
import { SIGNUP } from "redux-saga/actions";
import { SIGNUP_SUCCESS, SIGNUP_FAILED } from "./reducers";

export const signUp = (data: IParams) => ({
  type: SIGNUP,
  response: {
    success: SIGNUP_SUCCESS,
    failed: SIGNUP_FAILED,
  },
  data,
});