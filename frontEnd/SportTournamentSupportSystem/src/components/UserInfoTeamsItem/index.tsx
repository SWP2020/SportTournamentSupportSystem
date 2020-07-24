import React from 'react';
import { connect } from 'react-redux';
import { FaEdit } from 'react-icons/fa';
import { MdDelete } from 'react-icons/md';
import './styles.css';

interface IUserInfoTeamsItemProps extends React.ClassAttributes<UserInfoTeamsItem> {
}

interface IUserInfoTeamsItemState {
  seeMoreInfo: boolean;
}

class UserInfoTeamsItem extends React.Component<IUserInfoTeamsItemProps, IUserInfoTeamsItemState> {
  constructor(props: IUserInfoTeamsItemProps) {
    super(props);
    this.state = {
      seeMoreInfo: false,
    };
  }

  private handleSeeMore = () => {
    this.setState({
      seeMoreInfo: !this.state.seeMoreInfo,
    });
  }

  render() {
    return (
      <div className="UserInfoTeamsItem-info-container">
        <div className="UserInfoTeamsItem-container">
          <div className="UserInfoTeamsItem-container-container" onClick={this.handleSeeMore}>
            <div className="UserInfoTeamsItem-order-number-container">
              <div className="UserInfoTeamsItem-order-number-box">
                <p>1</p>
              </div>
            </div>
            <div className="UserInfoTeamsItem-team-name-container">
              <p>G2</p>
            </div>
            <div className="UserInfoTeamsItem-team-setting-container">
              <div className="UserInfoTeamsItem-team-setting-container-container">
                <FaEdit className="UserInfoTeamsItem-team-setting-icon" />
              </div>
              <div className="UserInfoTeamsItem-team-setting-container-container">
                <MdDelete className="UserInfoTeamsItem-team-setting-icon" />
              </div>
            </div>
          </div>
        </div>
        {this.state.seeMoreInfo === true &&
          <div className="UserInfoTeamsItem-moreInfo-container">
            <div className="UserInfoTeamsItem-moreInfo-normalInfo-container">
              <p>Giải tham gia: ABCXYZ</p>
              <p>Bộ môn tham gia: Bóng đá</p>
              <p>Tên cuộc thi: Bóng đá U20</p>
              <p>Danh sách thành viên</p>
            </div>
            <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-container">
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>Thứ tự</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Tên</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Giới tính</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>Ngày sinh</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>Email</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container1">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>1</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container2">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>2</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container1">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>3</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container2">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>4</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container1">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>5</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
              <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-container UserInfoTeamsItem-moreInfo-listTeamInfo-item-container2">
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-orderNumber">
                  <p>6</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-memberName">
                  <p>Phan Trọng Nhân</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-gender">
                  <p>Nam</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-dob">
                  <p>30/09/1998</p>
                </div>
                <div className="UserInfoTeamsItem-moreInfo-listTeamInfo-item-email">
                  <p>caulamgithelol.lmht@gmail.com</p>
                </div>
              </div>
            </div>
          </div>}
      </div>
    );
  }
}

export default connect(
  null,
  null
)(UserInfoTeamsItem);