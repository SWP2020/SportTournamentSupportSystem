import React from 'react';
import { connect } from 'react-redux';
import { IBracketTeamInfo } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { setHoveringTeam } from './actions';
import './styles.css';

interface IBracketTeamProps extends React.ClassAttributes<BracketTeam> {
  info: IBracketTeamInfo;
  borderBottom?: boolean;
  hoveringTeam: string | null;
  setHoveringTeam(params: string | null): void;
}

interface IBracketTeamState {
}

class BracketTeam extends React.Component<IBracketTeamProps, IBracketTeamState> {
  constructor(props: IBracketTeamProps) {
    super(props);
    this.state = {
    };
  }

  private setHoveringTeam = (data: string | null) => {
    this.props.setHoveringTeam(data);
  }

  render() {
    return (
      <div
        className={`BracketTeam-container ${this.props.borderBottom === true && 'BracketTeam-border-bottom'}`}
        onMouseOver={() => { this.setHoveringTeam(this.props.info.teamInfo && this.props.info.teamInfo.id ? this.props.info.teamInfo.id : null); }}
        onMouseOut={() => { this.setHoveringTeam(null); }}>
        <div
          className={`BracketTeam-name-container ${this.props.info.teamInfo && this.props.hoveringTeam != null && this.props.info.teamInfo.id === this.props.hoveringTeam ? 'BracketTeam-beingHovered' : 'BracketTeam-notBeingHovered'}`}>
          {this.props.info.teamInfo && <p className={"BracketTeam-name-text"}>{this.props.info.teamInfo.name}</p>}
        </div>
        <div className={`BracketTeam-score-container BracketTeam-score-top${this.props.info.top ? this.props.info.top : '2'}-container`}>
          {this.props.info.score && <p className={`BracketTeam-score-top${this.props.info.top ? this.props.info.top : '2'}-text`}>{this.props.info.score}</p>}
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    hoveringTeam: state.hoveringTeam,
  };
};

export default connect(
  mapStateToProps,
  {
    setHoveringTeam,
  }
)(BracketTeam);