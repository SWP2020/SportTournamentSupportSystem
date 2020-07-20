import React from 'react';
import { connect } from 'react-redux';
import { FaRunning } from 'react-icons/fa';
import { IoIosPeople } from 'react-icons/io';
import { BsCalendarFill } from 'react-icons/bs';
import './styles.css';

interface ITournamentOverviewProps extends React.ClassAttributes<TournamentOverview> {
}

interface ITournamentOverviewState {
}

class TournamentOverview extends React.Component<ITournamentOverviewProps, ITournamentOverviewState> {
  constructor(props: ITournamentOverviewProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="TournamentOverview-container">
        <div className="TournamentOverview-background-image-container">
        </div>
        <div className="TournamentOverview-avatar-image-container">
          <div className="TournamentOverview-avatar-container">
          </div>
          <div className="TournamentOverview-name-container">
            <p className="TournamentOverview-name-text">Giải bóng đá nữ U20</p>
          </div>
        </div>
        <div className="TournamentOverview-info-container">
          <div className="TournamentOverview-info-item">
            <FaRunning size={25}/>
            <p className="TournamentOverview-text">Bóng bàn, cờ vua, ...</p>
          </div>
          <div className="TournamentOverview-info-item">
            <IoIosPeople size={25}/>
            <p className="TournamentOverview-text">10 người tham gia</p>
          </div>
          <div className="TournamentOverview-info-item">
            <BsCalendarFill size={25}/>
            <p className="TournamentOverview-text">01/01/2020</p>
          </div>
          <div className="TournamentOverview-info-item">
            <p>Được tạo ngày: 01/01/2020</p>
          </div>
          <div className="TournamentOverview-info-item">
            <p>Tiến trình giải: 15%</p>
          </div>
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(TournamentOverview);