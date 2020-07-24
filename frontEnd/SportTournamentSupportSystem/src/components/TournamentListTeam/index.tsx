import React from 'react';
import { connect } from 'react-redux';
import TournamentListTeamItems from 'components/TournamentListTeamItems';
import './styles.css';

interface ITournamentListTeamProps extends React.ClassAttributes<TournamentListTeam> {
}

interface ITournamentListTeamState {
}

class TournamentListTeam extends React.Component<ITournamentListTeamProps, ITournamentListTeamState> {
  constructor(props: ITournamentListTeamProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="TournamentListTeam-container">
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
        <TournamentListTeamItems />
      </div>
    );
  }
}

export default connect(
  null,
  null
)(TournamentListTeam);