import React from 'react';
import { connect } from 'react-redux';
import './styles.css';

interface IUserInfoTeamsItemProps extends React.ClassAttributes<UserInfoTeamsItem> {
}

interface IUserInfoTeamsItemState {
}

class UserInfoTeamsItem extends React.Component<IUserInfoTeamsItemProps, IUserInfoTeamsItemState> {
  constructor(props: IUserInfoTeamsItemProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="UserInfoTeamsItem-container">
        <div className="UserInfoTeamsItem-order-number-container">
          <div className="UserInfoTeamsItem-order-number-box">
            <p>1</p>
          </div>
        </div>
        <div className="UserInfoTeamsItem-team-name-container">
          <p>G2</p>
        </div>
        <div className="UserInfoTeamsItem-team-setting-container">
          <p>G2</p>
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(UserInfoTeamsItem);