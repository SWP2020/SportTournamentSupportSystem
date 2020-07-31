import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import * as H from 'history';
import { StaticContext } from 'react-router';
import Skeleton from 'react-loading-skeleton';
import CustomTab from 'components/CustomTab';
import UserInfoOverview from 'components/UserInfoOverview';
import UserInfoTournament from 'components/UserInfoTournament';
import UserInfoTeams from 'components/UserInfoTeams';
import { IParams, IBigRequest } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { formatGender } from 'utils/common';
import { queryUserInfo } from './actions';
import './styles.css';

interface IUserInfoProps extends React.ClassAttributes<UserInfo> {
  routerInfo: RouteComponentProps<any, StaticContext, H.LocationState>;
  userInfo: IParams | null;

  queryUserInfo(param: IBigRequest): void;
}

interface IUserInfoState {
}

class UserInfo extends React.Component<IUserInfoProps, IUserInfoState> {
  constructor(props: IUserInfoProps) {
    super(props);
    this.state = {
    };
  }

  shouldComponentUpdate(nextProps: IUserInfoProps, nextState: IUserInfoState) {
    return true;
  }

  componentDidMount() {
    this.requestData();
  }

  private requestData = () => {
    const params = {
      path: Number(this.props.routerInfo.match.params.userId),
      param: {},
      data: {},
    };
    this.props.queryUserInfo(params);
  }

  render() {
    const tabList = ['Tổng quan', 'Giải đấu', 'Đội đang quản lý', 'Tin tức'];
    const componentList = [<UserInfoOverview />, <UserInfoTournament userId={Number(this.props.routerInfo.match.params.userId)} />, <UserInfoTeams userId={Number(this.props.routerInfo.match.params.userId)} />, <div />];
    return (
      <div className="UserInfo-Container">
        <div className="UserInfo-background-image-container">
        </div>
        <div className="UserInfo-content-container">
          <div className="UserInfo-content-info-container">
            <div className="UserInfo-content-info-basic-info-container">
              <div className="UserInfo-content-info-basic-info-container-singleRow">
                <p className="UserInfo-name-text">{this.props.userInfo != null && this.props.userInfo.firstname != null && this.props.userInfo.lastname != null ? `${this.props.userInfo.firstname} ${this.props.userInfo.lastname}` : <Skeleton width={250} height={30} />}</p>
              </div>
              <div className="UserInfo-content-info-basic-info-container-singleRow">
                <p className="UserInfo-otherInfo-text">{this.props.userInfo != null ? formatGender(this.props.userInfo.gender as boolean | null) : <Skeleton width={50} height={20} />}</p>
                <p className="UserInfo-otherInfo-text">{this.props.userInfo != null ? this.props.userInfo.age : <Skeleton width={70} height={20} />}</p>
                <p className="UserInfo-otherInfo-text">{this.props.userInfo != null ? this.props.userInfo.createdate : <Skeleton width={200} height={20} />}</p>
              </div>
            </div>
            <div className="UserInfo-content-info-advanced-info-container">
              <CustomTab tabList={tabList} componentList={componentList} selectedIndex={0}></CustomTab>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    userInfo: state.userInfo,
  };
};

export default connect(
  mapStateToProps,
  { queryUserInfo, }
)(UserInfo);