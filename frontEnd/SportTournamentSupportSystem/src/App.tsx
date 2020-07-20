import React from 'react';
import { connect } from 'react-redux';
// import Login from 'screens/Login';
// import SignUp from 'screens/SignUp';
// import ForgotPassword from 'screens/ForgotPassword';
// import Home from 'screens/Home';
import UserInfo from 'screens/UserInfo';
import Header from 'components/Header';
import Content from 'components/Content';
import { IState } from 'redux-saga/reducers';
import './App.css';

interface IAppProps extends React.ClassAttributes<App> {
}

interface IAppState {
}

class App extends React.Component<IAppProps, IAppState> {
  constructor(props: IAppProps) {
    super(props);
    this.state = {
    };
  }


  render() {
    return (
      <div className="Container">
        <Header />
        <Content fullScreen={true} transparent={false}>
          <UserInfo />
        </Content>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    currentPage: state.currentPage,
  };
};

export default connect(
  mapStateToProps,
  null
)(App);