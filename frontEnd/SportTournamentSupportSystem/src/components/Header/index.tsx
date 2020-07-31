import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { FaChevronDown, FaChevronUp } from 'react-icons/fa';
import { COOKIES_TYPE } from 'global';
import { cookies } from 'utils/cookies';
import { IState } from 'redux-saga/reducers';
import { logOut } from './actions';
import './styles.css';
import store from 'redux-saga/store';

interface IHeaderProps extends React.ClassAttributes<Header> {
  currentPage: 'login' | 'signUp' | 'tournaments' | 'tournamentInfo' | 'users' | 'userInfo' | 'home' | 'forgotPassword' | 'newTournament';

  logOut(): void;
}

interface IHeaderState {
  showUserOption: boolean;
}

class Header extends React.Component<IHeaderProps, IHeaderState> {
  constructor(props: IHeaderProps) {
    super(props);
    this.state = {
      showUserOption: false,
    };
  }

  private handleShowUserOption = () => {
    this.setState({
      showUserOption: !this.state.showUserOption,
    });
  }


  private hideUserOption = () => {
    this.setState({
      showUserOption: false,
    });
  }

  private logOut = () => {
    this.hideUserOption();
    this.props.logOut();
  }

  render() {
    const currentUserInfo = cookies.get(COOKIES_TYPE.AUTH_TOKEN);
    return (
      <div className="Header-container">
        <div className="Logo-container">
          <Link to="/">
            <img src={'./assets/logo.jpg'} alt={'logo'} />
          </Link>
        </div>
        <div className="Option-container">
          <Link to="/tournaments">
            <div className="Link"><p className="Link-text">Các giải đấu</p></div>
          </Link>
          <Link to="/news">
            <div className="Link"><p className="Link-text">Tin tức</p></div>
          </Link>
          <Link to="/events">
            <div className="Link"><p className="Link-text">Sự kiện</p></div>
          </Link>
        </div>
        <div className="SearchBar-container"></div>
        {this.props.currentPage !== 'newTournament' && <Link to="/newTournament">
          <div className="Button-container"><p className="Button">Tạo giải ngay</p></div>
        </Link>}
        {cookies.get(COOKIES_TYPE.AUTH_TOKEN) != null ?
          <div className="Right-container Right-container-hover">
            <div className={'UserOption-container'} onClick={this.handleShowUserOption}>
              <div className={'UserOption-avatar-container'}></div>
              <p className={'UserOption-name-text'}>{`${currentUserInfo.User.firstname} ${currentUserInfo.User.lastname}`}</p>
              {this.state.showUserOption === true ? <FaChevronDown color={'white'} /> : <FaChevronUp color={'white'} />}
            </div>
            {this.state.showUserOption === true &&
              <div
                className={'UserOption-dropdown-container'}
              >
              <Link to={`/user/${currentUserInfo.User.userID}`}>
                  <div className={'UserOption-dropdown-item-container UserOption-dropdown-item-container1'}>
                    <p>Thông tin</p>
                  </div>
                </Link>
                <div className={'UserOption-dropdown-item-container UserOption-dropdown-item-container2'}>
                  <p>Giải đấu đã tạo</p>
                </div>
                <div className={'UserOption-dropdown-item-container UserOption-dropdown-item-container1'}>
                  <p>Đội đã tham gia</p>
                </div>
                <div className={'UserOption-dropdown-item-container UserOption-dropdown-item-container2'}>
                  <p>Cài đặt tài khoản</p>
                </div>
                <div className={'UserOption-dropdown-item-container UserOption-dropdown-item-container1'} onClick={this.logOut}>
                  <p>Đăng xuất</p>
                </div>
              </div>
            }
          </div>
          : <div className="Right-container">
            {this.props.currentPage === 'login' && <div className="Link-text-no-hover-container"><p className="Link-text-no-hover">Bạn chưa có tài khoản?</p></div>}
            {this.props.currentPage === 'login' ?
              <Link to="/signUp">
                <div className="Button-container"><p className="Button">Đăng ký</p></div>
              </Link> :
              <Link to="/login">
                <div className="Button-container"><p className="Button">Đăng nhập</p></div>
              </Link>}
          </div>}
      </div>
    )
  }
}

const mapStateToProps = (state: IState) => {
  return {
  };
};

export default connect(
  mapStateToProps,
  { logOut, }
)(Header);