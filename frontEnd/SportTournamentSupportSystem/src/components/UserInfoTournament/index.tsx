import React from 'react';
import { connect } from 'react-redux';
import TournamentOverview from 'components/TournamentOverview';
import { IBigRequest, IParams } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { queryListTournamentsOfUser } from './actions';
import './styles.css';

interface IUserInfoTournamentProps extends React.ClassAttributes<UserInfoTournament> {
  userId: number;
  listTournamentOfUser: IParams | null;

  queryListTournamentsOfUser(param: IBigRequest): void;
}

interface IUserInfoTournamentState {
  currentPage: number;
}

class UserInfoTournament extends React.Component<IUserInfoTournamentProps, IUserInfoTournamentState> {
  constructor(props: IUserInfoTournamentProps) {
    super(props);
    this.state = {
      currentPage: 1,
    };
  }

  componentDidMount() {
    this.requestData();
  }

  private requestData = () => {
    const params = {
      path: '',
      param: {
        id: this.props.userId,
        page: this.state.currentPage,
      },
      data: {},
    };
    this.props.queryListTournamentsOfUser(params);
  }

  render() {
    return (
      <div className="UserInfoTournament-container">
        <TournamentOverview />
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    listTournamentOfUser: state.listTournamentOfUser,
  };
};

export default connect(
  mapStateToProps,
  { queryListTournamentsOfUser, }
)(UserInfoTournament);