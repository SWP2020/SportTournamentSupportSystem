import React from 'react';
import { connect } from 'react-redux';
import ReduxBlockUi from 'react-block-ui/redux';
import 'react-block-ui/style.css';
import { AiOutlineClose } from "react-icons/ai";
import TextInput from 'components/TextInput';
import { IBigRequest } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { checkUsernameExisted, setUsernameExistedDefault } from './actions';
import { CHECK_USERNAME_EXISTED } from 'redux-saga/actions';
import { CHECK_USERNAME_EXISTED_SUCCESS, CHECK_USERNAME_EXISTED_FAILED } from './reducers';
import './styles.css';

interface ITournamentSettingProps extends React.ClassAttributes<TournamentSetting> {
  isUsernameExisted: boolean | null | {};

  checkUsernameExisted(param: IBigRequest): void;
  setUsernameExistedDefault(): void;
}

interface ITournamentSettingState {
  listManager: string[];
  username: string;
  usernameError: boolean;
  usernameErrorContent: string;
}

class TournamentSetting extends React.Component<ITournamentSettingProps, ITournamentSettingState> {
  constructor(props: ITournamentSettingProps) {
    super(props);
    this.state = {
      listManager: ['Phạm Minh Hiếu', 'Phan Trọng Nhân', 'Đỗ Văn Công', '4', '5', '6', '7'],
      username: '',
      usernameError: false,
      usernameErrorContent: '',
    };
  }

  shouldComponentUpdate(nextProps: ITournamentSettingProps, nextState: ITournamentSettingState) {
    if (this.props.isUsernameExisted !== nextProps.isUsernameExisted && nextProps.isUsernameExisted === true) {
      this.addManager(this.state.username);
    }
    if (this.props.isUsernameExisted !== nextProps.isUsernameExisted && nextProps.isUsernameExisted === false) {
      this.setState({
        usernameError: true,
        usernameErrorContent: 'Tài khoản không hợp lệ',
      });
    }
    if (this.props.isUsernameExisted !== nextProps.isUsernameExisted && nextProps.isUsernameExisted === null) {
      this.setState({
        usernameError: true,
        usernameErrorContent: 'Mất kết nối',
      });
    }
    if (this.props.isUsernameExisted !== nextProps.isUsernameExisted && nextProps.isUsernameExisted === {}) {
      this.setState({
        usernameError: false,
        usernameErrorContent: '',
      });
    }
    return true;
  }

  private addManager = (username: string) => {
    this.setState({
      listManager: this.state.listManager.concat(username),
    });
  }

  private onDeleteManager = (itemm: string) => {
    this.setState({
      listManager: this.state.listManager.filter((item) => item !== itemm),
    });
  }

  private onChangeUsername = (value: string) => {
    this.setState({ username: value, });
  }

  private validate = () => {
    let usernameError = false;
    let usernameErrorContent = '';
    if (this.state.username.trim() === '') {
      usernameError = true;
      usernameErrorContent = 'Tên đăng nhập không được trống';
    } else if (this.state.listManager.includes(this.state.username.trim())) {
      usernameError = true;
      usernameErrorContent = 'Người dùng này đã là quản lý';
    } else {
      const params = {
        path: '',
        param: {
          username: this.state.username,
        },
        data: {},
      };
      this.props.checkUsernameExisted(params);
    }

    return { usernameError, usernameErrorContent };
  }

  private handleAddManager = () => {
    const { usernameError, usernameErrorContent } = this.validate();
    this.setState({
      usernameError,
      usernameErrorContent,
    });
    this.props.setUsernameExistedDefault();
  }

  render() {
    return (
      <div className="TournamentSetting-container">
        <ReduxBlockUi
          tag="div"
          block={CHECK_USERNAME_EXISTED}
          unblock={[CHECK_USERNAME_EXISTED_SUCCESS, CHECK_USERNAME_EXISTED_FAILED]}
        >
          <div className="TournamentSetting-tournament-container">
            <p className="TournamentSetting-header-text">Thông tin giải đấu</p>
            <div className={'TournamentSetting-listManager-container'}>
              <p>Danh sách quản trị viên: </p>
              <div className={'TournamentSetting-listManager-container-container'}>
                {this.state.listManager.map((item, index) => <div className={'TournamentSetting-manager-container'} key={index}>
                  <p className={'TournamentSetting-manager-text'}>{item}</p>
                  <div className={'TournamentSetting-icon-container'} onClick={() => { this.onDeleteManager(item) }}>
                    <AiOutlineClose />
                  </div>
                </div>)}
                <TextInput label='nhập username để thêm quản trị viên' error={this.state.usernameError} errorContent={this.state.usernameErrorContent} onChangeText={this.onChangeUsername} onHandleSubmit={this.handleAddManager} />
                {/*defaultValue */}
              </div>
            </div>
            <div className={'TournamentSetting-listManager-container'}>
              <p>Tên giải:</p>
              <div className={'TournamentSetting-tounamentName-container-container'}>
                <TextInput label='Nhập tên của giải' error={this.state.usernameError} errorContent={this.state.usernameErrorContent} onChangeText={this.onChangeUsername} onHandleSubmit={this.handleAddManager} />
                {/*defaultValue */}
              </div>
            </div>
            <div className={'TournamentSetting-listManager-container'}>
              <p>Tên ngắn:</p>
              <div className={'TournamentSetting-tounamentName-container-container'}>
                <TextInput label='Nhập tên ngắn của giải' error={this.state.usernameError} errorContent={this.state.usernameErrorContent} onChangeText={this.onChangeUsername} onHandleSubmit={this.handleAddManager} />
                {/*defaultValue */}
              </div>
            </div>
            <div className={'TournamentSetting-checkBox-container'}>
              <label className="Checkbox-label">
                <input type="checkbox" />
                {/*defaultValue */}
            Đăng kí qua form
          </label>
            </div>
            <div className={'TournamentSetting-checkBox-container'}>
              <label className="Checkbox-label">
                <input type="checkbox" />
                {/*defaultValue */}
            Xắp xếp lịch cho 2 giải so le
          </label>
            </div>
            <div className={'TournamentSetting-listManager-container'}>
              <p>Mô tả:</p>
              <div className={'TournamentSetting-tounamentName-container-container'}>
                <TextInput label='Nhập mô tả' error={this.state.usernameError} errorContent={this.state.usernameErrorContent} onChangeText={this.onChangeUsername} onHandleSubmit={this.handleAddManager} />
                {/*defaultValue */}
              </div>
            </div>
          </div>
          <div className="TournamentSetting-competition-container">

          </div>
        </ReduxBlockUi>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    isUsernameExisted: state.isUsernameExisted,
  };
};

export default connect(
  mapStateToProps,
  { checkUsernameExisted, setUsernameExistedDefault }
)(TournamentSetting);