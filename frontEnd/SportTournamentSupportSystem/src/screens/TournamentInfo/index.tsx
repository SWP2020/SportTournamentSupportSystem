import React from 'react';
import { connect } from 'react-redux';
import Select, { ValueType, OptionTypeBase } from 'react-select';
import { RouteComponentProps } from 'react-router-dom';
import * as H from 'history';
import { StaticContext } from 'react-router';
import { FaRunning } from 'react-icons/fa';
import { IoIosPeople } from 'react-icons/io';
import { BsCalendarFill } from 'react-icons/bs';
import BracketBoard from 'components/BracketBoard';
import CustomTab from 'components/CustomTab';
import BracketSchedule from 'components/BracketSchedule';
import BracketRank from 'components/BracketRank';
import TournamentListTeam from 'components/TournamentListTeam';
import TournamentSetting from 'components/TournamentSetting';
import './styles.css';

interface ITournamentInfoProps extends React.ClassAttributes<TournamentInfo> {
  routerInfo: RouteComponentProps<any, StaticContext, H.LocationState>;
}

interface ITournamentInfoState {
  selectedSport: ValueType<OptionTypeBase>;
  selectedCompetition: ValueType<OptionTypeBase>;
}

const sportOptions = [
  { value: 'Football', label: 'Đá bóng' },
  { value: 'Chess', label: 'Cờ vua' },
  { value: 'Table Tennis', label: 'Bóng bàn' }
];

const competitionOptions = [
  { value: 'Football', label: 'Đá bóng' },
  { value: 'Chess', label: 'Cờ vua' },
  { value: 'Table Tennis', label: 'Bóng bàn' }
];

class TournamentInfo extends React.Component<ITournamentInfoProps, ITournamentInfoState> {
  constructor(props: ITournamentInfoProps) {
    super(props);
    this.state = {
      selectedSport: null,
      selectedCompetition: null,
    };
  }

  private onChangeSport = (value: ValueType<OptionTypeBase>) => {
    this.setState({
      selectedSport: value,
    });
  }

  private onChangeCompetition = (value: ValueType<OptionTypeBase>) => {
    this.setState({
      selectedCompetition: value,
    });
  }

  render() {
    const tabList = ['Nhánh thi đấu', 'Lịch thi đấu', 'Bảng xếp hạng', 'Thông tin', 'Danh sách các đội', 'Cài đặt'];
    const componentList = [<BracketBoard />, <BracketSchedule />, <BracketRank />, <div />, <TournamentListTeam />, <TournamentSetting />];
    return (
      <div className="TournamentInfo-Container">
        <div className="TournamentInfo-background-image-container">
        </div>
        <div className="TournamentInfo-content-container">
          <div className="TournamentInfo-content-info-container">
            <div className="TournamentInfo-content-info-basic-info-container">
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <p className="TournamentInfo-name-text">Giải hội khỏe phù đổng</p>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <FaRunning size={25} />
                  <p className="TournamentInfo-text">Bóng bàn, cờ vua, ...</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <IoIosPeople size={25} />
                  <p className="TournamentInfo-text">10 người tham gia</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <BsCalendarFill size={25} />
                  <p className="TournamentInfo-text">01/01/2020</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <p>Được tạo ngày: 01/01/2020</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>id: tournament-123456789</p>
                </div>
              </div>
            </div>
            <div className="TournamentInfo-content-info-basic-info-container TournamentInfo-oddBackground">
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <p className="TournamentInfo-name-text">Các cuộc thi trong giải</p>
                <div className="TournamentInfo-info-item">
                  <p className="TournamentInfo-text">Bộ môn</p>
                  <Select
                    options={sportOptions}
                    className="Select"
                    defaultValue={this.state.selectedSport}
                    onChange={this.onChangeSport}
                    menuPlacement={'top'}
                  />
                </div>
                <div className="TournamentInfo-info-item">
                  <p className="TournamentInfo-text">Cuộc thi thuộc bộ môn</p>
                  <Select
                    options={competitionOptions}
                    className={`Select ${this.state.selectedSport == null && `Select-disabled`}`}
                    defaultValue={this.state.selectedSport}
                    onChange={this.onChangeCompetition}
                    isDisabled={this.state.selectedSport == null}
                    menuPlacement={'top'}
                  />
                </div>
              </div>
              {this.state.selectedCompetition != null && this.state.selectedSport != null && <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <FaRunning size={25} />
                  <p className="TournamentInfo-text">Bóng bàn, cờ vua, ...</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <IoIosPeople size={25} />
                  <p className="TournamentInfo-text">10 người tham gia</p>
                </div>
              </div>}
              {this.state.selectedCompetition != null && this.state.selectedSport != null && <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <BsCalendarFill size={25} />
                  <p className="TournamentInfo-text">01/01/2020</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <p>Được tạo ngày: 01/01/2020</p>
                </div>
              </div>}
              {this.state.selectedCompetition != null && this.state.selectedSport != null && <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>id: tournament-123456789</p>
                </div>
              </div>}
            </div>
            {this.state.selectedCompetition != null && this.state.selectedSport != null && <div className="TournamentInfo-content-info-advanced-info-container">
              <CustomTab tabList={tabList} componentList={componentList} selectedIndex={0}></CustomTab>
            </div>}
          </div>
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(TournamentInfo);