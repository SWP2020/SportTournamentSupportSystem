import React from 'react';
import { connect } from 'react-redux';
import './styles.css';

interface IHeaderProps extends React.ClassAttributes<Header> {
}

interface IHeaderState {
}

class Header extends React.Component<IHeaderProps, IHeaderState> {
  render() {
    return (
      <div className="Header-container">
        <div className="Logo-container">
          <img src={'./assets/logo.jpg'} alt={'logo'} />
        </div>
        <div className="Option-container">
          <div className="Link"><p className="Link-text">Các giải đấu</p></div>
          <div className="Link"><p className="Link-text">Tin tức</p></div>
          <div className="Link"><p className="Link-text">Sự kiện</p></div>
        </div>
        <div className="SearchBar-container"></div>
        <div className="Right-container">
          <div className="Link-text-no-hover-container"><p className="Link-text-no-hover">Bạn chưa có tài khoản?</p></div>
          <div className="Button-container"><p className="Button">Đăng ký</p></div>
        </div>
      </div>
    )
  }
}

export default connect(
  null,
  null
)(Header);