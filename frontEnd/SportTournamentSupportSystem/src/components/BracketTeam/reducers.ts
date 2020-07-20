import { IAction } from "interfaces/common";
import { SET_HOVERING_TEAM } from "redux-saga/actions";

export function HoveringTeam(state: string | null = null, action: IAction<string | null>) {
  switch (action.type) {
    case SET_HOVERING_TEAM:
      return action.payload;
    default:
      return state;
  }
}