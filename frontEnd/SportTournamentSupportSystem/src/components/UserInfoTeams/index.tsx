import React from 'react';
import { connect } from 'react-redux';
import UserInfoTeamsItem from 'components/UserInfoTeamsItem';
import { IBigRequest, IParams } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { queryListTeamsOfUser } from './actions';
import './styles.css';

interface IUserInfoTeamsProps extends React.ClassAttributes<UserInfoTeams> {
  userId: number;
  listTeamOfUser: IParams | null;

  queryListTeamsOfUser(param: IBigRequest): void;
}

interface IUserInfoTeamsState {
  currentPage: number;
}

class UserInfoTeams extends React.Component<IUserInfoTeamsProps, IUserInfoTeamsState> {
  constructor(props: IUserInfoTeamsProps) {
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
    this.props.queryListTeamsOfUser(params);
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

const mapStateToProps = (state: IState) => {
  return {
    listTeamOfUser: state.listTeamOfUser,
  };
};

export default connect(
  mapStateToProps,
  { queryListTeamsOfUser, }
)(UserInfoTeams);