import React from 'react';
import { connect } from 'react-redux';
import { Styles } from 'react-modal';
import 'react-tabs/style/react-tabs.css';
import { FaSearch, FaEdit } from 'react-icons/fa';
import { MdSettings } from 'react-icons/md';
import BracketTeam from 'components/BracketTeam';
import CustomModal from 'components/CustomModal';
import CustomTab from 'components/CustomTab';
import MatchDetail from 'components/MatchDetail';
import MatchSetting from 'components/MatchSetting';
import MatchResult from 'components/MatchResult';
import { IState } from 'redux-saga/reducers';
import { IBracketMatchInfo } from 'interfaces/common';
import { MATCH_CONTAINER_HEIGHT, MATCH_TYPE } from 'global';
import './styles.css';

interface IBracketMatchProps extends React.ClassAttributes<BracketMatch> {
  bracketStartedStatus?: boolean;
  info: IBracketMatchInfo;
  round: number;
}

interface IBracketMatchState {
  iconVisible: boolean;
  selectedIndexInTab: number
  showModal: boolean;
}

const customStyles: Styles = {
  content: {
    top: '15%',
    left: '15%',
    right: '15%',
    bottom: '15%',
    backgroundColor: '#2b303d',
    display: 'flex',
    flexDirection: 'column',
  },
  overlay: {
    zIndex: 100001,
  },
};

class BracketMatch extends React.Component<IBracketMatchProps, IBracketMatchState> {
  constructor(props: IBracketMatchProps) {
    super(props);
    this.state = {
      iconVisible: false,
      showModal: false,
      selectedIndexInTab: 0,
    };
  }

  private handleCloseModal = () => {
    this.setState({
      showModal: false,
      selectedIndexInTab: 0,
    });
  }

  private handleOpenModal = (index: number) => {
    this.setState({
      showModal: true,
      selectedIndexInTab: index,
    });
  }

  private handleConfirmModal = () => {
  }

  render() {
    let amountOfListTeamDisplayed = 0;
    for (let i = 0; i < this.props.info.listTeam.length; i++) {
      if (this.props.info.listTeam[i].teamInfo != null && this.props.info.listTeam[i].teamInfo!.id) {
        amountOfListTeamDisplayed++;
      }
    }
    const tabList = ['Match Detail', 'Match Setting'];
    const tabComponentList = [<MatchDetail />, <MatchSetting />];
    if (this.props.info.listTeam.length === amountOfListTeamDisplayed && this.props.bracketStartedStatus === true) {
      tabComponentList.push(<MatchResult teamsInfo={this.props.info.listTeam}/>);
      tabList.push('Match Result');
    }
    return (
      <div
        className="BracketMatch-container"
        style={{ height: `${(MATCH_CONTAINER_HEIGHT / 2) * (2 ** this.props.round)}px` }}
      >
        {this.props.round > 1 && this.props.info.type !== MATCH_TYPE.BRONZE_MATCH &&
          <div
            className="BracketMatch-preMatch-connector"
            style={{ height: `${(MATCH_CONTAINER_HEIGHT / 4) * (2 ** this.props.round)}px` }}
          >
            {/* height=số đội trong 1 match * 25px / 2 + 2 */}
            <div className="BracketMatch-preMatch-connector-border1"></div>
            <div className={`${this.props.info.type !== MATCH_TYPE.PRIORITY_MATCH && 'BracketMatch-preMatch-connector-border2-border'} BracketMatch-preMatch-connector-border2`}></div>
          </div>}
        <div className="BracketMatch-numericalOrder-container">
          <p className="BracketMatch-numericalOrder-text">{this.props.info.numericalOrderMatch}</p>
        </div>
        <div className="BracketMatch-info-container">
          <p className={'BracketMatch-info-text No-margin-bottom'}>{this.props.info.time}</p>
          <div className="BracketMatch-teams-container" onMouseOver={() => { this.setState({ iconVisible: true, }); }} onMouseOut={() => { this.setState({ iconVisible: false, }); }}>
            <div className="BracketMatch-team-container">
              {
                this.props.info.listTeam.map((item, index) => {
                  return (<BracketTeam info={item} key={index} {...index < this.props.info.listTeam.length - 1 && { borderBottom: true, }} />);
                })
              }
            </div>
            <div className="BracketMatch-matchSetting-container">
              <div className={`BracketMatch-afterMatch-icon-container ${this.state.iconVisible === true && 'BracketMatch-afterMatch-icon-container-background'}`} onClick={() => this.handleOpenModal(0)}>
                <FaSearch className={`BracketMatch-afterMatch-icon-search ${this.state.iconVisible === true ? 'BracketMatch-afterMatch-icon-visible' : 'BracketMatch-afterMatch-icon-invisible'}`} />
              </div>
              <div className={`BracketMatch-afterMatch-icon-container ${this.state.iconVisible === true && 'BracketMatch-afterMatch-icon-container-background'}`} onClick={() => this.handleOpenModal(1)}>
                <MdSettings className={`BracketMatch-afterMatch-icon-setting ${this.state.iconVisible === true ? 'BracketMatch-afterMatch-icon-visible' : 'BracketMatch-afterMatch-icon-invisible'}`} />
              </div>
              {this.props.info.listTeam.length === amountOfListTeamDisplayed && this.props.bracketStartedStatus === true && <div className={`BracketMatch-afterMatch-icon-container ${this.state.iconVisible === true && 'BracketMatch-afterMatch-icon-container-background'}`} onClick={() => this.handleOpenModal(2)}>
                <FaEdit className={`BracketMatch-afterMatch-icon-setting ${this.state.iconVisible === true ? 'BracketMatch-afterMatch-icon-visible' : 'BracketMatch-afterMatch-icon-invisible'}`} />
              </div>}
            </div>
          </div>
          <p className={'BracketMatch-info-text No-margin-top'}>{this.props.info.location}</p>
        </div>
        {
          this.props.round < 3 && this.props.info.type !== MATCH_TYPE.BRONZE_MATCH && <div
            // this.props.round nhỏ hơn tổng số round
            className="BracketMatch-preMatch-connector"
            style={{ height: `${MATCH_CONTAINER_HEIGHT}px` }}
          >
            {/* height=số đội trong 1 match * 25px / 2 + 2 */}
            <div className="BracketMatch-afterMatch-connector-border1"></div>
            <div className="BracketMatch-afterMatch-connector-border2"></div>
          </div>
        }
        <CustomModal
          customStyles={customStyles}
          handleCloseModal={this.handleCloseModal}
          showModal={this.state.showModal}
          handleConfirmModal={this.handleConfirmModal}>
          <CustomTab tabList={tabList} componentList={tabComponentList} selectedIndex={this.state.selectedIndexInTab} />
        </CustomModal>
      </div >
    );
  }
}

const mapStateToProps = (state: IState) => {
  return {
    bracketStartedStatus: state.bracketStartedStatus,
  };
};

export default connect(
  mapStateToProps,
  null
)(BracketMatch);