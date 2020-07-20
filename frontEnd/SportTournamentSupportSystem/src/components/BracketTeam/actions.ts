import { SET_HOVERING_TEAM } from "redux-saga/actions";

export const setHoveringTeam = (data: string | null) => ({
  type: SET_HOVERING_TEAM,
  payload: data,
});