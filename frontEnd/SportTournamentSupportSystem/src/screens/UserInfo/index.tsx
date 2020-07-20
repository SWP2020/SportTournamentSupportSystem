import React from 'react';
import { connect } from 'react-redux';
import CustomTab from 'components/CustomTab';
import UserInfoOverview from 'components/UserInfoOverview';
import UserInfoTournament from 'components/UserInfoTournament';
import UserInfoTeams from 'components/UserInfoTeams';
import './styles.css';

interface IUserInfoProps extends React.ClassAttributes<UserInfo> {
}

interface IUserInfoState {
}

class UserInfo extends React.Component<IUserInfoProps, IUserInfoState> {
  constructor(props: IUserInfoProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    const tabList = ['Tổng quan', 'Giải đấu', 'Đội đang quản lý', 'Tin tức'];
    const componentList = [<UserInfoOverview />, <UserInfoTournament />, <UserInfoTeams />, <div />];
    return (
      <div className="UserInfo-Container">
        <div className="UserInfo-background-image-container">
        </div>
        <div className="UserInfo-content-container">
          <div className="UserInfo-content-info-container">
            <div className="UserInfo-content-info-basic-info-container">
              <div className="UserInfo-content-info-basic-info-container-singleRow">
                <p className="UserInfo-name-text">Phạm Minh Hiếu</p>
              </div>
              <div className="UserInfo-content-info-basic-info-container-singleRow">
                <p className="UserInfo-otherInfo-text">Nam</p>
                <p className="UserInfo-otherInfo-text">30 tuổi</p>
                <p className="UserInfo-otherInfo-text">Tạo ngày 30/09/2020</p>
              </div>
              <div className="UserInfo-content-info-basic-info-container-singleRow">
                <p>id: user-123456789</p>
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

export default connect(
  null,
  null
)(UserInfo);