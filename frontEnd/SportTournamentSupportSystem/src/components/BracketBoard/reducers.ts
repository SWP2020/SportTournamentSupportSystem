import { SET_BRACKET_STATUS } from "redux-saga/actions";
import { IBracketBoardInfo, IAction } from "interfaces/common";
import { MATCH_TYPE } from "global";

export const GET_BRACKET_BOARD_INFO_SUCCESS = 'GET_BRACKET_BOARD_INFO_SUCCESS';
export const GET_BRACKET_BOARD_INFO_FAILED = 'GET_BRACKET_BOARD_INFO_FAILED';

export function BracketBoardInfo(state: IBracketBoardInfo | null = null, action: IAction<IBracketBoardInfo>) {
  switch (action.type) {
    case GET_BRACKET_BOARD_INFO_SUCCESS:
      // return action.payload;
      return {
        started: true,
        id: 'competition-1',
        listRound: [
          {
            roundName: 'Vòng Tứ kết',
            roundNumber: 1,
            listMatch: [
              {
                id: 'match-1',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 1,
                listTeam: [
                  {
                    teamInfo: {
                      id: 'team-1',
                      name: 'Green Team',
                    },
                    top: '1',
                    score: '1',
                  },
                  {
                    teamInfo: {
                      id: 'team-2',
                      name: 'Blue Team',
                    },
                    top: '2',
                    score: '0',
                  },
                ],
                time: '01/02/2020 07:30 AM',
                location: 'Sân vận động Mỹ Đình',
              },
              {
                id: 'match-2',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 2,
                listTeam: [
                  {},
                  {},
                ],
                time: '02/02/2020 07:30 AM',
              },
              {
                id: 'match-3',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 3,
                listTeam: [
                  {},
                  {},
                ],
                time: '03/02/2020 07:30 AM',
              },
              {
                id: 'match-4',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 4,
                listTeam: [
                  {},
                  {},
                ],
                location: 'Sân Hàng Đẫy',
              },
            ],
          },
          {
            roundName: 'Vòng Bán kết',
            roundNumber: 2,
            listMatch: [
              {
                id: 'match-5',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 5,
                listTeam: [
                  {
                    teamInfo: {
                      id: 'team-1',
                      name: 'Green Team',
                    },
                  },
                  {},
                ],
                location: 'Sân Hàng Đẫy',
              },
              {
                id: 'match-6',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 6,
                listTeam: [
                  {},
                  {},
                ],
                location: 'Sân Hàng Đẫy',
              },
            ],
          },
          {
            roundName: 'Vòng Chung kết, tranh giải 3',
            roundNumber: 3,
            listMatch: [
              {
                id: 'match-8',
                type: MATCH_TYPE.NORMAL_MATCH,
                numericalOrderMatch: 8,
                listTeam: [
                  {},
                  {},
                ],
              },
              {
                id: 'match-7',
                type: MATCH_TYPE.BRONZE_MATCH,
                numericalOrderMatch: 7,
                listTeam: [
                  {},
                  {},
                ],
              },
            ],
          },
        ],
      };
    case GET_BRACKET_BOARD_INFO_FAILED:
      return null;
    default:
      return state;
  }
}

export function BracketStartedStatus(state = false, action: IAction<boolean>) {
  switch (action.type) {
    case SET_BRACKET_STATUS:
      return action.payload;
    default:
      return state;
  }
}