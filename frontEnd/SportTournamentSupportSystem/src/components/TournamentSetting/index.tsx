import React from 'react';
import { connect } from 'react-redux';
import './styles.css';

interface ITournamentSettingProps extends React.ClassAttributes<TournamentSetting> {
}

interface ITournamentSettingState {
}

class TournamentSetting extends React.Component<ITournamentSettingProps, ITournamentSettingState> {
  constructor(props: ITournamentSettingProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="TournamentSetting-container">
        <div className="TournamentSetting-tournament-container">
          <p className="TournamentSetting-header-text">Thông tin giải đấu</p>
          <p>Danh sách quản trị viên: </p>
        </div>
        <div className="TournamentSetting-competition-container">

        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(TournamentSetting);