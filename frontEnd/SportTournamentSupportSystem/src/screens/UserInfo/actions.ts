import { IBigRequest } from "interfaces/common";
import { QUERY_USER_INFO } from "redux-saga/actions";
import { QUERY_USER_INFO_SUCCESS, QUERY_USER_INFO_FAILED } from "./reducers";

export const queryUserInfo = (data: IBigRequest) => ({
  type: QUERY_USER_INFO,
  response: {
    success: QUERY_USER_INFO_SUCCESS,
    failed: QUERY_USER_INFO_FAILED,
  },
  data: {
    path: data.path,
    param: data.param,
    data: data.data,
  },
});