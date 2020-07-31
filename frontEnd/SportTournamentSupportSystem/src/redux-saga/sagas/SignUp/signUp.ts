import { call, takeLatest, put } from 'redux-saga/effects';
import { query, METHOD } from 'utils/socketApi';
import { IRequest, IParams, IBigRequest } from 'interfaces/common';
import { SIGNUP, COMMON_SHOW_NOTIFICATION } from 'redux-saga/actions';

const signUp = (data: IParams, path: string | number, param: IParams) => {
  const uri = 'users';
  const datas = { ...data };
  const paths = path;
  const params = { ...param };
  return query(uri, METHOD.POST, datas, params, paths);
};

function* doSignUp(request: IRequest<IBigRequest>) {
  try {
    const response = yield call(signUp, request.data.data, request.data.path, request.data.param);
    const data = response.data;
    yield put({
      type: request.response.success,
      payload: data,
    });
  } catch (error) {
    yield put({
      type: COMMON_SHOW_NOTIFICATION,
      data: {
        type: 'error',
        title: 'Sign Up',
        content: 'error',
        time: new Date(),
      },
    });
  }
}

export default function* watchSignUp() {
  yield takeLatest(SIGNUP, doSignUp);
}
