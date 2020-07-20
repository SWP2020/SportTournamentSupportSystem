import React from 'react';
import { connect } from 'react-redux';
import './styles.css';
import TextInput from 'components/TextInput';

interface IForgotPasswordProps extends React.ClassAttributes<ForgotPassword> {
}

interface IForgotPasswordState {
  email: string;
}

class ForgotPassword extends React.Component<IForgotPasswordProps, IForgotPasswordState> {
  constructor(props: IForgotPasswordProps) {
    super(props);
    this.state = {
      email: '',
    };
  }

  private onChangeEmail = (value: string) => {
    this.setState({ email: value, });
  }

  render() {
    return (
      <div className="Container-login">
        <div className="Container-login-middle">
          <h2>Quên mật khẩu</h2>
          <p className="Long-introduction">Nhập địa chỉ email của bạn và chúng tôi sẽ gửi cho bạn một liên kết để đặt lại mật khẩu của bạn.</p>

          <TextInput label={'Email của bạn'} type={'email'} onChangeText={this.onChangeEmail}/>
          <div className="Button-login-container">
            <div className="Button-login">
              <h4 className="Button-login-text">Gửi</h4>
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
)(ForgotPassword);