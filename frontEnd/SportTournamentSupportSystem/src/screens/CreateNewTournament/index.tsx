import React from 'react';
import { connect } from 'react-redux';
import 'react-block-ui/style.css';
import TextInput from 'components/TextInput';
import TournamentCompetitionItem from 'components/TournamentCompetitionItem';
import { IState } from 'redux-saga/reducers';
import './styles.css';

interface ICreateNewTournamentProps extends React.ClassAttributes<CreateNewTournament> {
}

interface ICreateNewTournamentState {
  tournamentName: string;
  tournamentNameError: boolean;
  tournamentNameErrorContent: string;
  tournamentShortName: string;
  tournamentShortNameError: boolean;
  tournamentShortNameErrorContent: string;
  tournamentDescription: string;
  tournamentDescriptionError: boolean;
  tournamentDescriptionErrorContent: string;
  tournamentStartLocation: string;
  tournamentStartLocationError: boolean;
  tournamentStartLocationErrorContent: string;
  tournamentEndLocation: string;
  tournamentEndLocationError: boolean;
  tournamentEndLocationErrorContent: string;
}

class CreateNewTournament extends React.Component<ICreateNewTournamentProps, ICreateNewTournamentState> {
  constructor(props: ICreateNewTournamentProps) {
    super(props);
    this.state = {
      tournamentName: '',
      tournamentNameError: false,
      tournamentNameErrorContent: '',
      tournamentShortName: '',
      tournamentShortNameError: false,
      tournamentShortNameErrorContent: '',
      tournamentDescription: '',
      tournamentDescriptionError: false,
      tournamentDescriptionErrorContent: '',
      tournamentStartLocation: '',
      tournamentStartLocationError: false,
      tournamentStartLocationErrorContent: '',
      tournamentEndLocation: '',
      tournamentEndLocationError: false,
      tournamentEndLocationErrorContent: '',
    };
  }

  private onChangeTournamentname = (value: string) => {
    this.setState({ tournamentName: value, });
  }

  private onChangeTournamentShortName = (value: string) => {
    this.setState({ tournamentShortName: value, });
  }

  private onChangeTournamentDescription = (value: string) => {
    this.setState({ tournamentDescription: value, });
  }

  private onChangeTournamentStartLocation = (value: string) => {
    this.setState({ tournamentStartLocation: value, });
  }

  private onChangeTournamentEndLocation = (value: string) => {
    this.setState({ tournamentEndLocation: value, });
  }

  render() {
    return (
      <div className="CreateNewTournament-container">
        <div className="CreateNewTournament-tournament-container">
          <p className="CreateNewTournament-header-text">Thông tin giải đấu</p>
          <div className={'CreateNewTournament-listManager-container'}>
            <p>Tên giải:</p>
            <div className={'CreateNewTournament-tounamentName-container-container'}>
              <TextInput label='Nhập tên của giải' error={this.state.tournamentNameError} errorContent={this.state.tournamentNameErrorContent} onChangeText={this.onChangeTournamentname} />
              {/*defaultValue */}
            </div>
          </div>
          <div className={'CreateNewTournament-listManager-container'}>
            <p>Tên ngắn:</p>
            <div className={'CreateNewTournament-tounamentName-container-container'}>
              <TextInput label='Nhập tên ngắn của giải' error={this.state.tournamentShortNameError} errorContent={this.state.tournamentShortNameErrorContent} onChangeText={this.onChangeTournamentShortName} />
              {/*defaultValue */}
            </div>
          </div>
          <div className={'CreateNewTournament-checkBox-container'}>
            <label className="Checkbox-label">
              <input type="checkbox" />
              {/*defaultValue */}
            Đăng kí qua form
          </label>
          </div>
          <div className={'CreateNewTournament-checkBox-container'}>
            <label className="Checkbox-label">
              <input type="checkbox" />
              {/*defaultValue */}
            Xắp xếp lịch cho 2 giải so le
          </label>
          </div>
          <div className={'CreateNewTournament-listManager-container'}>
            <p>Mô tả:</p>
            <div className={'CreateNewTournament-tounamentName-container-container'}>
              <TextInput label='Nhập mô tả' error={this.state.tournamentDescriptionError} errorContent={this.state.tournamentDescriptionErrorContent} onChangeText={this.onChangeTournamentDescription} />
              {/*defaultValue */}
            </div>
          </div>
          <div className={'CreateNewTournament-listManager-container'}>
            <p>Địa điểm khai mạc:</p>
            <div className={'CreateNewTournament-tounamentName-container-container'}>
              <TextInput label='' error={this.state.tournamentStartLocationError} errorContent={this.state.tournamentStartLocationErrorContent} onChangeText={this.onChangeTournamentStartLocation} />
              {/*defaultValue */}
            </div>
          </div>
          <div className={'CreateNewTournament-listManager-container'}>
            <p>Địa điểm bế mạc:</p>
            <div className={'CreateNewTournament-tounamentName-container-container'}>
              <TextInput label='' error={this.state.tournamentEndLocationError} errorContent={this.state.tournamentEndLocationErrorContent} onChangeText={this.onChangeTournamentEndLocation} />
              {/*defaultValue */}
            </div>
          </div>
        </div>
        <div className="CreateNewTournament-competition-container">
          <p className="TournamentSetting-header-text">Thông tin các cuộc thi</p>
          <TournamentCompetitionItem />
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
  };
};

export default connect(
  mapStateToProps,
  null
)(CreateNewTournament);