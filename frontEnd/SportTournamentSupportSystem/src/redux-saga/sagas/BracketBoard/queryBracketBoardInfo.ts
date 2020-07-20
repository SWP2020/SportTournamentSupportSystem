import { call, takeLatest, put } from 'redux-saga/effects';
import { query, METHOD } from 'utils/socketApi';
import { IRequest, IParams } from 'interfaces/common';
import { GET_BRACKET_BOARD_INFO } from 'redux-saga/actions';
import { GET_BRACKET_BOARD_INFO_SUCCESS } from 'components/BracketBoard/reducers';

const queryBracketBoardInfo = (data: IParams) => {
  const uri = 'user';
  const params = { ...data };
  return query(uri, METHOD.GET, params);
};

function* doQueryBracketBoardInfo(request: IRequest<IParams>) {
  try {
    yield call(queryBracketBoardInfo, request.data);
    yield put({
      type: GET_BRACKET_BOARD_INFO_SUCCESS,
    });
  } catch (error) {
    yield put({
      type: request.response.failed,
    });
  }
}

export default function* watchQueryBracketBoardInfo() {
  yield takeLatest(GET_BRACKET_BOARD_INFO, doQueryBracketBoardInfo);
}
