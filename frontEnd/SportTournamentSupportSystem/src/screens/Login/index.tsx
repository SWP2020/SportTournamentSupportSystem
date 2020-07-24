import React from 'react';
import { connect } from 'react-redux';
import TextInput from 'components/TextInput';
import { IParams } from 'interfaces/common';
import { login } from './actions';
import './styles.css';

interface ILoginProps extends React.ClassAttributes<Login> {
  login(params: IParams): void;
}

interface ILoginState {
  username: string;
  password: string;
  usernameError: boolean;
  passwordError: boolean;
  usernameErrorContent: string;
  passwordErrorContent: string;
}

class Login extends React.Component<ILoginProps, ILoginState> {
  constructor(props: ILoginProps) {
    super(props);
    this.state = {
      username: '',
      password: '',
      usernameError: false,
      usernameErrorContent: '',
      passwordError: false,
      passwordErrorContent: '',
    };
  }

  private onChangeUserName = (value: string) => {
    this.setState({ username: value, });
  }

  private onChangePassword = (value: string) => {
    this.setState({ password: value, });
  }

  private validate = () => {
    let passwordError = false;
    let passwordErrorContent = '';
    let usernameErrorContent = '';
    let usernameError = false;
    if (this.state.password.includes(' ')) {
      passwordError = true;
      passwordErrorContent = 'Mật khẩu không được trống, và không chứa dấu cách';
    }
    if (this.state.username.trim() === '') {
      usernameError = true;
      usernameErrorContent = 'Tên đăng nhập không được trống';
    }

    return { passwordError, passwordErrorContent, usernameErrorContent, usernameError };
  }

  private handleLogin = () => {
    const { passwordError, passwordErrorContent, usernameErrorContent, usernameError } = this.validate();
    this.setState({
      passwordError,
      passwordErrorContent,
      usernameErrorContent,
      usernameError,
    });
    if (passwordError === true || usernameError === true) {
      return;
    }
    const params = {
      username: this.state.username,
      password: this.state.password,
    };

    this.props.login(params);
  }

  render() {
    return (
      <div className="Container-login">
        <div className="Container-login-middle">
          <h2>Đăng nhập</h2>
          <p className="Long-introduction">Chào mừng! Vui lòng nhập thông tin đăng nhập của bạn hoặc đăng nhập bằng tài khoản mạng xã hội</p>
          <p>Đăng nhập với</p>
          <div className="Alternate-login-container">
            <div className="Alternate-login-option1 Alternate-login-option"><p className="Alternate-login-option-text">FACEBOOK</p></div>
            <div className="Alternate-login-option2 Alternate-login-option"><p className="Alternate-login-option-text">GOOGLE</p></div>
          </div>
          <p>Hoặc</p>


          <TextInput label={'Tên đăng nhập'} onChangeText={this.onChangeUserName} error={this.state.usernameError} errorContent={this.state.usernameErrorContent} />
          <TextInput label={'Mật khẩu'} type={'password'} onChangeText={this.onChangePassword} error={this.state.passwordError} errorContent={this.state.passwordErrorContent} />
          <div className="Login-option-container">
            <div className="Login-option-container-item">
              <label className="Checkbox-label">
                <input type="checkbox" />
            Nhớ tài khoản trên thiết bị này
          </label>
            </div>
            <div className="Login-option-container-item Login-option-container-item1">
              <p className="Forgot-password">Quên mật khẩu</p>
            </div>
          </div>
          <div className="Button-login-container">
            <div className="Button-login" onClick={this.handleLogin}>
              <h4 className="Button-login-text">Đăng nhập</h4>
            </div>
          </div>

        </div>
      </div>
    );
  }
}

export default connect(
  null,
  {
    login,
  }
)(Login);