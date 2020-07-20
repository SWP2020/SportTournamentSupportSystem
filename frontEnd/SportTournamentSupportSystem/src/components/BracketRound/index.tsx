import React from 'react';
import { connect } from 'react-redux';
import BracketMatch from 'components/BracketMatch';
import { IBracketRoundInfo } from 'interfaces/common';
import './styles.css';

interface IBracketRoundProps extends React.ClassAttributes<BracketRound> {
  info: IBracketRoundInfo;
}

interface IBracketRoundState {
  roundHover: boolean;
}

class BracketRound extends React.Component<IBracketRoundProps, IBracketRoundState> {
  constructor(props: IBracketRoundProps) {
    super(props);
    this.state = {
      roundHover: false,
    };
  }

  render() {
    return (
      <div className={`BracketRound-eachRound ${this.state.roundHover === true ? 'BracketRound-reachRound-bolder' : 'BracketRound-eachRound-noBold'}`}>
        <div className={`BracketRound-title-round-container ${this.props.info.roundNumber > 1 && 'BracketRound-title-round-container-border'}`} onMouseOver={() => { this.setState({ roundHover: true, }); }} onMouseOut={() => { this.setState({ roundHover: false, }); }}>
          <p className="BracketRound-title-round-text">{this.props.info.roundName}</p>
        </div>
        {
          this.props.info.listMatch.map((item, index) => {
            return (<BracketMatch info={item} key={index} round={this.props.info.roundNumber} />);
          })
        }
      </div>
    );
  }
}

export default connect(
  null,
  null
)(BracketRound);