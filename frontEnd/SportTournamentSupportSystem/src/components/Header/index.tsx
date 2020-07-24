import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import './styles.css';

interface IHeaderProps extends React.ClassAttributes<Header> {
}

interface IHeaderState {
}

class Header extends React.Component<IHeaderProps, IHeaderState> {
  constructor(props: IHeaderProps) {
    super(props);
    this.state = {
    };
  }

  render() {
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
        <div className="Right-container">
          <div className="Link-text-no-hover-container"><p className="Link-text-no-hover">Bạn chưa có tài khoản?</p></div>
          <Link to="/signUp">
            <div className="Button-container"><p className="Button">Đăng ký</p></div>
          </Link>
        </div>
      </div>
    )
  }
}

export default connect(
  null,
  null
)(Header);