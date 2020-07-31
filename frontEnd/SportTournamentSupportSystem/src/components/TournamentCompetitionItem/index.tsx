import React from 'react';
import { connect } from 'react-redux';
import Select, { ValueType, OptionTypeBase } from 'react-select';
import { FaEdit } from 'react-icons/fa';
import { MdDelete } from 'react-icons/md';
import TextInput from 'components/TextInput';
import './styles.css';

interface ITournamentCompetitionItemProps extends React.ClassAttributes<TournamentCompetitionItem> {
}

interface ITournamentCompetitionItemState {
  seeMoreInfo: boolean;
  competitionName: string;
  selectedSport: ValueType<OptionTypeBase>;
  selectedType: ValueType<OptionTypeBase>;
  competitionNameError: boolean;
  competitionNameErrorContent: string;
}

const sportOptions = [
  { value: 'Football', label: 'Đá bóng' },
  { value: 'Chess', label: 'Cờ vua' },
  { value: 'Table Tennis', label: 'Bóng bàn' },
];

const typeOptions = [
  { value: 'RoundRobin', label: 'Đấu vòng tròn' },
  { value: 'RoundRobinScore', label: 'Đấu vòng tròn tính điểm' },
  { value: 'TwoPhase', label: 'Đấu 2 giai đoạn' },
  { value: 'UpperLowerBracket', label: 'Đấu nhánh thắng nhánh thua' },
  { value: 'Elimination', label: 'Đấu loại' },
];

class TournamentCompetitionItem extends React.Component<ITournamentCompetitionItemProps, ITournamentCompetitionItemState> {
  constructor(props: ITournamentCompetitionItemProps) {
    super(props);
    this.state = {
      seeMoreInfo: false,
      competitionNameError: false,
      selectedSport: null,
      selectedType: null,
      competitionName: '',
      competitionNameErrorContent: '',
    };
  }

  private handleSeeMore = () => {
    this.setState({
      seeMoreInfo: !this.state.seeMoreInfo,
    });
  }

  private onChangeSport = (value: ValueType<OptionTypeBase>) => {
    this.setState({
      selectedSport: value,
    });
  }

  private onChangeType = (value: ValueType<OptionTypeBase>) => {
    this.setState({
      selectedType: value,
    });
  }

  private onChangeCompetitionName = (value: string) => {
    this.setState({
      competitionName: value,
    });
  }

  render() {
    return (
      <div className="TournamentCompetitionItem-info-container">
        <div className="TournamentCompetitionItem-container">
          <div className="TournamentCompetitionItem-container-container" onClick={this.handleSeeMore}>
            <div className="TournamentCompetitionItem-order-number-container">
              <div className="TournamentCompetitionItem-order-number-box">
                <p>1</p>
              </div>
            </div>
            <div className="TournamentCompetitionItem-team-name-container">
              <p>G2</p>
            </div>
            <div className="TournamentCompetitionItem-team-setting-container">
              <div className="TournamentCompetitionItem-team-setting-container-container">
                <FaEdit className="TournamentCompetitionItem-team-setting-icon" />
              </div>
              <div className="TournamentCompetitionItem-team-setting-container-container">
                <MdDelete className="TournamentCompetitionItem-team-setting-icon" />
              </div>
            </div>
          </div>
        </div>
        {this.state.seeMoreInfo === true &&
          <div className="TournamentCompetitionItem-moreInfo-container">
            <div className="TournamentCompetitionItem-moreInfo-normalInfo-container">
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
                <p className="TournamentInfo-text">Tên cuộc thi:</p>
                <TextInput
                  label={''}
                  error={this.state.competitionNameError}
                  errorContent={this.state.competitionNameErrorContent}
                  onChangeText={this.onChangeCompetitionName}
                />
              </div>
              <div className="TournamentInfo-info-item">
                <p className="TournamentInfo-text">Thể thức</p>
                <Select
                  options={typeOptions}
                  className="Select"
                  defaultValue={this.state.selectedType}
                  onChange={this.onChangeType}
                  menuPlacement={'top'}
                />
              </div>
              <div className={'CreateNewTournament-checkBox-container'}>
                <label className="Checkbox-label">
                  <input type="checkbox" />
                  {/*defaultValue */}
            Giới hạn số đội tối đa
          </label>
              </div>
              <div className="TournamentInfo-info-item">
                <TextInput
                  label={'Nhập số đội tối đa'}
                  error={this.state.competitionNameError}
                  errorContent={this.state.competitionNameErrorContent}
                  onChangeText={this.onChangeCompetitionName}
                />
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
)(TournamentCompetitionItem);