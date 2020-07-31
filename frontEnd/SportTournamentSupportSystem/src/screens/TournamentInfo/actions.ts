import { IBigRequest } from "interfaces/common";
import { QUERY_TOURNAMENT_INFO } from "redux-saga/actions";
import { QUERY_TOURNAMENT_INFO_SUCCESS, QUERY_TOURNAMENT_INFO_FAILED } from "./reducers";

export const queryTournamentInfo = (data: IBigRequest) => ({
  type: QUERY_TOURNAMENT_INFO,
  response: {
    success: QUERY_TOURNAMENT_INFO_SUCCESS,
    failed: QUERY_TOURNAMENT_INFO_FAILED,
  },
  data: {
    path: data.path,
    param: data.param,
    data: data.data,
  },
});