import React, { ReactNode } from 'react';
import { connect } from 'react-redux';
import Select, { ValueType, OptionTypeBase } from 'react-select';
import { RouteComponentProps } from 'react-router-dom';
import * as H from 'history';
import { StaticContext } from 'react-router';
import Skeleton from 'react-loading-skeleton';
import { FaRunning } from 'react-icons/fa';
import { IoIosPeople } from 'react-icons/io';
import { BsCalendarFill } from 'react-icons/bs';
import BracketBoard from 'components/BracketBoard';
import CustomTab from 'components/CustomTab';
import BracketSchedule from 'components/BracketSchedule';
import BracketRank from 'components/BracketRank';
import TournamentListTeam from 'components/TournamentListTeam';
import TournamentSetting from 'components/TournamentSetting';
import { IBigRequest, IParams } from 'interfaces/common';
import { IState } from 'redux-saga/reducers';
import { formatDateToDisplay } from 'utils/datetime';
import { queryTournamentInfo } from './actions';
import './styles.css';

interface ITournamentInfoProps extends React.ClassAttributes<TournamentInfo> {
  routerInfo: RouteComponentProps<any, StaticContext, H.LocationState>;
  tournamentInfo: IParams | null;

  queryTournamentInfo(param: IBigRequest): void;
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

  componentDidMount() {
    this.requestData();
  }

  private requestData = () => {
    const params = {
      path: '',
      param: {
        id: Number(this.props.routerInfo.match.params.tournamentId),
      },
      data: {},
    };
    this.props.queryTournamentInfo(params);
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
    let tabList: string[] = [];
    let componentList: ReactNode[] = [];
    if (this.state.selectedCompetition != null) {
      tabList = ['Nhánh thi đấu', 'Lịch thi đấu', 'Bảng xếp hạng', 'Thông tin', 'Danh sách các đội', 'Cài đặt'];
      componentList = [<BracketBoard />, <BracketSchedule />, <BracketRank />, <div />, <TournamentListTeam />, <TournamentSetting />];
    } else {
      tabList = ['Cài đặt'];
      componentList = [<TournamentSetting />];
    }

    return (
      <div className="TournamentInfo-Container">
        <div className="TournamentInfo-background-image-container">
        </div>
        <div className="TournamentInfo-content-container">
          <div className="TournamentInfo-content-info-container">
            <div className="TournamentInfo-content-info-basic-info-container">
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <p className="TournamentInfo-name-text">{this.props.tournamentInfo != null ? this.props.tournamentInfo.fullName : <Skeleton width={400} height={30} />}</p>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>Tên ngắn: {this.props.tournamentInfo != null ? `(${this.props.tournamentInfo.shortName})` : <Skeleton width={150} height={20} />}</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>Nhà tài trợ: {this.props.tournamentInfo != null ? this.props.tournamentInfo.donor : <Skeleton width={150} height={20} />}</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <p>Trạng thái: {this.props.tournamentInfo != null ? (this.props.tournamentInfo.status === 'processing' ? 'Đang diễn ra' : (this.props.tournamentInfo.status == null ? 'Chưa diễn ra' : 'Đã kết thúc')) : <Skeleton width={125} height={20} />}</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>Ngày bắt đầu: {this.props.tournamentInfo != null ? formatDateToDisplay(this.props.tournamentInfo.openingTime as string | undefined, 'dd/MM/yyyy', 'yyyy-MM-dd') : <Skeleton width={150} height={20} />}</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <p>Địa điểm khai mạc: {this.props.tournamentInfo != null ? this.props.tournamentInfo.openingLocation : <Skeleton width={150} height={20} />}</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>Ngày kết thúc: {this.props.tournamentInfo != null ? formatDateToDisplay(this.props.tournamentInfo.closingTime as string | undefined, 'dd/MM/yyyy', 'yyyy-MM-dd') : <Skeleton width={150} height={20} />}</p>
                </div>
                <div className="TournamentInfo-info-item">
                  <p>Địa điểm bế mạc: {this.props.tournamentInfo != null ? this.props.tournamentInfo.closingLocation : <Skeleton width={150} height={20} />}</p>
                </div>
              </div>
              <div className="TournamentInfo-content-info-basic-info-container-singleRow">
                <div className="TournamentInfo-info-item">
                  <p>Được tạo ngày: 01/01/2020</p>
                </div>
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
                  <p>Mô tả: {this.props.tournamentInfo != null ? this.props.tournamentInfo.description : <Skeleton width={300} height={20} />}</p>
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
            <div className="TournamentInfo-content-info-advanced-info-container">
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
    tournamentInfo: state.tournamentInfo,
  };
};

export default connect(
  mapStateToProps,
  { queryTournamentInfo, }
)(TournamentInfo);