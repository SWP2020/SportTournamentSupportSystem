import React from 'react';
import { connect } from 'react-redux';
import UserInfoTeamsItem from 'components/UserInfoTeamsItem';
import './styles.css';

interface IUserInfoTeamsProps extends React.ClassAttributes<UserInfoTeams> {
}

interface IUserInfoTeamsState {
}

class UserInfoTeams extends React.Component<IUserInfoTeamsProps, IUserInfoTeamsState> {
  constructor(props: IUserInfoTeamsProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="UserInfoTeams-container">
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
        <UserInfoTeamsItem />
      </div>
    );
  }
}

export default connect(
  null,
  null
)(UserInfoTeams);