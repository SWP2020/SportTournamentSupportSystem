import React from 'react';
import { connect } from 'react-redux';
import TournamentOverview from 'components/TournamentOverview';
import './styles.css';

interface IUserInfoTournamentProps extends React.ClassAttributes<UserInfoTournament> {
}

interface IUserInfoTournamentState {
}

class UserInfoTournament extends React.Component<IUserInfoTournamentProps, IUserInfoTournamentState> {
  constructor(props: IUserInfoTournamentProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="UserInfoTournament-container">
        <TournamentOverview />
      </div>
    );
  }
}

export default connect(
  null,
  null
)(UserInfoTournament);