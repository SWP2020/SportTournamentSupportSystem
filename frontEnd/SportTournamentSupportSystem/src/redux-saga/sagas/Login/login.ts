import { call, takeLatest, put } from 'redux-saga/effects';
import { query, METHOD } from 'utils/socketApi';
import { IRequest, IParams } from 'interfaces/common';
import { LOGIN, COMMON_SHOW_NOTIFICATION } from 'redux-saga/actions';

const login = (data: IParams) => {
  const uri = 'login';
  const params = { ...data };
  return query(uri, METHOD.POST, params);
};

function* doLogin(request: IRequest<IParams>) {
  try {
    const response = yield call(login, request.data);
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
        title: 'Login',
        content: 'Your username or password is incorrect',
        time: new Date(),
      },
    });
  }
}

export default function* watchLogin() {
  yield takeLatest(LOGIN, doLogin);
}
