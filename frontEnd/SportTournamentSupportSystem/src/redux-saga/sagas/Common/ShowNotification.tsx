import { takeLatest } from 'redux-saga/effects';
import { toast } from 'react-toastify';
import { IRequest, INotification } from 'interfaces/common';
import { COMMON_SHOW_NOTIFICATION } from 'redux-saga/actions';

function doShowNotification(request: IRequest<INotification>) {
  try {
    toast.error('🦄 Wow so easy!', {
      position: "bottom-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
  } catch (err) { }
}

export default function* watchShowNotification() {
  yield takeLatest(COMMON_SHOW_NOTIFICATION, doShowNotification);
}
