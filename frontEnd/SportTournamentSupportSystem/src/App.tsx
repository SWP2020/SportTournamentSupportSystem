import React from 'react';
import { connect } from 'react-redux';
import { Switch, Route, Redirect } from 'react-router-dom';
import Login from 'screens/Login';
import SignUp from 'screens/SignUp';
// import ForgotPassword from 'screens/ForgotPassword';
import Home from 'screens/Home';
// import UserInfo from 'screens/UserInfo';
import TournamentInfo from 'screens/TournamentInfo';
import Header from 'components/Header';
import Content from 'components/Content';
import UserInfo from 'screens/UserInfo';
import AllTournaments from 'components/AllTournaments';
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
        <Switch>
          <Route exact path={["/", "/home"]} render={() => <Header />} />
          <Route path="/tournament/:tournamentId" render={() => <Header />} />
          <Route path="/signUp" render={() => <Header />} />
          <Route path="/user/:userId" render={() => <Header />} />
          <Route path="/tournaments" render={() => <Header />} />
          <Route path="/login" render={() => <Header />} />
        </Switch>
        <Content fullScreen={true} transparent={false}>
          <Switch>
            <Route exact path={["/", "/home"]} render={() => <Home />} />
            <Route path="/tournament/:tournamentId" render={(info) => <TournamentInfo routerInfo={info} />} />
            <Route path="/tournaments" render={() => <AllTournaments />} />
            <Route path="/signUp" render={() => <SignUp />} />
            <Route path="/login" render={() => <Login />} />
            <Route path="/user/:userId" render={(info) => <UserInfo routerInfo={info} />} />
            <Route render={() => <Redirect to="/" />} />
          </Switch>
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