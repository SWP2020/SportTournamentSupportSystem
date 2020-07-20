import React from 'react';
import { connect } from 'react-redux';
import BracketRound from 'components/BracketRound';
import { IState } from 'redux-saga/reducers';
import { IBracketBoardInfo, IParams } from 'interfaces/common';
import { queryBracketBoardInfo, setBracketStartedStatus } from './actions';
import './styles.css';

interface IBracketBoardProps extends React.ClassAttributes<BracketBoard> {
  bracketBoardInfo: IBracketBoardInfo | null;

  queryBracketBoardInfo(params: IParams): void;
  setBracketStartedStatus(params: boolean): void;
}

interface IBracketBoardState {
}

class BracketBoard extends React.Component<IBracketBoardProps, IBracketBoardState> {
  constructor(props: IBracketBoardProps) {
    super(props);
    this.state = {
    };
  }

  shouldComponentUpdate(nextProps: IBracketBoardProps, nextState: IBracketBoardState) {
    if (nextProps.bracketBoardInfo == null) {
      this.props.setBracketStartedStatus(false);
    } else {
      if (this.props.bracketBoardInfo !== nextProps.bracketBoardInfo) {
        this.props.setBracketStartedStatus(nextProps.bracketBoardInfo.started);
      }
    }
    return true;
  }

  componentDidMount() {
    this.requestData();
  }

  private requestData = () => {
    const params = {
    };
    this.props.queryBracketBoardInfo(params);
  }

  render() {
    return (
      <div className="BracketBoard-container">
        {this.props.bracketBoardInfo == null
          ? <p>Chưa có thông tin</p>
          : this.props.bracketBoardInfo.listRound.map((item, index) => {
            return (<BracketRound info={item} key={index} />);
          })
        }
      </div>
    );
  }

}

const mapStateToProps = (state: IState) => {
  return {
    bracketBoardInfo: state.bracketBoardInfo,
  };
};

export default connect(
  mapStateToProps,
  {
    queryBracketBoardInfo,
    setBracketStartedStatus,
  }
)(BracketBoard);