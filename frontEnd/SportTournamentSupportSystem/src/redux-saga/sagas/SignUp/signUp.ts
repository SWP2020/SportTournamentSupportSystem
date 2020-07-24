import { call, takeLatest, put } from 'redux-saga/effects';
import { query, METHOD } from 'utils/socketApi';
import { IRequest, IParams } from 'interfaces/common';
import { SIGNUP, COMMON_SHOW_NOTIFICATION } from 'redux-saga/actions';

const signUp = (data: IParams) => {
  const uri = '/users';
  const params = { ...data };
  return query(uri, METHOD.POST, params);
};

function* doSignUp(request: IRequest<IParams>) {
  try {
    const response = yield call(signUp, request.data);
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
        title: 'SignUp',
        content: 'error',
        time: new Date(),
      },
    });
  }
}

export default function* watchSignUp() {
  yield takeLatest(SIGNUP, doSignUp);
}
