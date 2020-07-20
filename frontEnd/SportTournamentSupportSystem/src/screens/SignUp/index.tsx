import React from 'react';
import { connect } from 'react-redux';
import './styles.css';
import TextInput from 'components/TextInput';

interface ISignUpProps extends React.ClassAttributes<SignUp> {
}

interface ISignUpState {
  username: string;
  password: string;
  email: string;
  reConfirmPassword: string;
}

class SignUp extends React.Component<ISignUpProps, ISignUpState> {
  constructor(props: ISignUpProps) {
    super(props);
    this.state = {
      username: '',
      password: '',
      email: '',
      reConfirmPassword: '',
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


          <TextInput label={'Tên đăng nhập'} onChangeText={this.onChangeUserName}/>
          <TextInput label={'Email'} type={'email'} onChangeText={this.onChangeEmail}/>
          <TextInput label={'Mật khẩu'} type={'password'} onChangeText={this.onChangePassword}/>
          <TextInput label={'Xác nhận mật khẩu'} type={'password'} onChangeText={this.onChangeReconfirmPassword}/>

          <div className="Login-option-container">
            <div className="Login-option-container-item">
              <label className="Checkbox-label">
                <input type="checkbox" />
            Tôi đồng ý với điều khoản dịch vụ
          </label>
            </div>
          </div>
          <div className="Button-login-container">
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
  null
)(SignUp);