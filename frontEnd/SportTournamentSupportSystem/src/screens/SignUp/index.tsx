import React from 'react';
import { connect } from 'react-redux';
import TextInput from 'components/TextInput';
import { IParams } from 'interfaces/common';
import config from 'config';
import { signUp } from './actions';
import './styles.css';

interface ISignUpProps extends React.ClassAttributes<SignUp> {
  signUp(param: IParams): void;
}

interface ISignUpState {
  username: string;
  password: string;
  email: string;
  reConfirmPassword: string;
  usernameError: boolean;
  passwordError: boolean;
  usernameErrorContent: string;
  passwordErrorContent: string;
  emailError: boolean;
  reconfirmPasswordError: boolean;
  emailErrorContent: string;
  reconfirmPasswordErrorContent: string;
}

class SignUp extends React.Component<ISignUpProps, ISignUpState> {
  constructor(props: ISignUpProps) {
    super(props);
    this.state = {
      username: '',
      password: '',
      email: '',
      reConfirmPassword: '',
      usernameError: false,
      passwordError: false,
      usernameErrorContent: '',
      passwordErrorContent: '',
      emailError: false,
      reconfirmPasswordError: false,
      emailErrorContent: '',
      reconfirmPasswordErrorContent: '',
    };
  }

  private onChangeUserName = (value: string) => {
    this.setState({ username: value, });
  }

  private onChangePassword = (value: string) => {
    this.setState({ password: value, });
  }

  private onChangeEmail = (value: string) => {
    this.setState({ email: value, });
  }

  private onChangeReconfirmPassword = (value: string) => {
    this.setState({ reConfirmPassword: value, });
  }

  private validate = () => {
    let passwordError = false;
    let passwordErrorContent = '';
    let usernameErrorContent = '';
    let usernameError = false;
    let emailErrorContent = '';
    let emailError = false;
    let reconfirmPasswordErrorContent = '';
    let reconfirmPasswordError = false;
    if (this.state.password.includes(' ') || !config.regex.password.test(this.state.password)) {
      passwordError = true;
      passwordErrorContent = 'Mật khẩu không được trống, không chứa dấu cách, và phải chứa từ 8 đến 32 kí tự';
    }
    if (this.state.username.trim() === '' || !config.regex.username.test(this.state.username)) {
      usernameError = true;
      usernameErrorContent = 'Tên đăng nhập không được trống, và phải chứa từ 8 đến 32 kí tự';
    }
    if (this.state.email.trim() === '' || !config.regex.email.test(this.state.email)) {
      emailError = true;
      emailErrorContent = 'Email không hợp lệ';
    }
    if (this.state.reConfirmPassword !== this.state.password) {
      reconfirmPasswordError = true;
      reconfirmPasswordErrorContent = 'Nhập lại mật khẩu phải giống mật khẩu';
    }

    return { passwordError, passwordErrorContent, usernameErrorContent, usernameError, emailErrorContent, emailError, reconfirmPasswordErrorContent, reconfirmPasswordError };
  }

  private handleSignUp = () => {
    const { passwordError, passwordErrorContent, usernameErrorContent, usernameError, emailErrorContent, emailError, reconfirmPasswordErrorContent, reconfirmPasswordError } = this.validate();
    this.setState({
      passwordError,
      passwordErrorContent,
      usernameErrorContent,
      usernameError,
      emailErrorContent,
      emailError,
      reconfirmPasswordErrorContent,
      reconfirmPasswordError,
    });
    if (passwordError === true || usernameError === true || reconfirmPasswordError === true || emailError === true) {
      return;
    }
    const params = {
      username: this.state.username,
      password: this.state.password,
      email: this.state.email,
    };

    this.props.signUp(params);
  }

  render() {
    return (
      <div className="Container-login">
        <div className="Container-login-middle">
          <h2>Đăng ký</h2>
          <p className="Long-introduction">Bắt đầu dễ dàng bằng cách đăng ký để quản lý các giải đấu và sự kiện</p>
          <p>Đăng nhập với</p>
          <div className="Alternate-login-container">
            <div className="Alternate-login-option1 Alternate-login-option"><p className="Alternate-login-option-text">FACEBOOK</p></div>
            <div className="Alternate-login-option2 Alternate-login-option"><p className="Alternate-login-option-text">GOOGLE</p></div>
          </div>
          <p>Hoặc</p>


          <TextInput label={'Tên đăng nhập'} onChangeText={this.onChangeUserName} error={this.state.usernameError} errorContent={this.state.usernameErrorContent} />
          <TextInput label={'Email'} onChangeText={this.onChangeEmail} error={this.state.emailError} errorContent={this.state.emailErrorContent} />
          <TextInput label={'Mật khẩu'} type={'password'} onChangeText={this.onChangePassword} error={this.state.passwordError} errorContent={this.state.passwordErrorContent} />
          <TextInput label={'Xác nhận mật khẩu'} type={'password'} onChangeText={this.onChangeReconfirmPassword} error={this.state.reconfirmPasswordError} errorContent={this.state.reconfirmPasswordErrorContent} />

          <div className="Login-option-container">
            <div className="Login-option-container-item">
              <label className="Checkbox-label">
                <input type="checkbox" />
            Tôi đồng ý với điều khoản dịch vụ
          </label>
            </div>
          </div>
          <div className="Button-login-container" onClick={this.handleSignUp}>
            <div className="Button-login">
              <h4 className="Button-login-text">Đăng ký</h4>
            </div>
          </div>

        </div>
      </div>
    );
  }
}

export default connect(
  null,
  { signUp, }
)(SignUp);