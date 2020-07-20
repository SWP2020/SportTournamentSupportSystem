import { IParams } from "interfaces/common";
import { GET_BRACKET_BOARD_INFO, SET_BRACKET_STATUS } from "redux-saga/actions";
import { GET_BRACKET_BOARD_INFO_SUCCESS } from "./reducers";

export const queryBracketBoardInfo = (data: IParams) => ({
  type: GET_BRACKET_BOARD_INFO,
  response: {
    success: GET_BRACKET_BOARD_INFO_SUCCESS,
    failed: GET_BRACKET_BOARD_INFO_SUCCESS,
  },
  data,
});

export const setBracketStartedStatus = (data: boolean) => ({
  type: SET_BRACKET_STATUS,
  payload: data,
});