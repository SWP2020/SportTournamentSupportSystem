import React from 'react';
import { connect } from 'react-redux';
import './styles.css';

interface IBracketScheduleProps extends React.ClassAttributes<BracketSchedule> {
}

interface IBracketScheduleState {
}

class BracketSchedule extends React.Component<IBracketScheduleProps, IBracketScheduleState> {
  constructor(props: IBracketScheduleProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="BracketSchedule-container">
        <div className="BracketSchedule-round-container">
          <div className="BracketSchedule-roundName-container">
            <p>Vòng Tứ kết</p>
          </div>
          <div className="BracketSchedule-roundMatch-container">
            <div className="BracketSchedule-roundMatch-orderNumber-container">
              <p>1</p>
            </div>
            <div className="BracketSchedule-roundMatch-time-container">
              <p>01/01/2020 10:00 AM</p>
              <p>Sân Mỹ Đình</p>
            </div>
            <div className="BracketSchedule-roundMatch-name-container">
              <p>Đội A</p>
            </div>
            <div className="BracketSchedule-roundMatch-consequent-container BracketSchedule-roundMatch-consequent1-container">
              <p className="BracketSchedule-roundMatch-result-text BracketSchedule-roundMatch-result1-text">1</p>
            </div>
            <div className="BracketSchedule-roundMatch-consequentMiddle-container">
              <p>-</p>
            </div>
            <div className="BracketSchedule-roundMatch-consequent-container BracketSchedule-roundMatch-consequent2-container">
              <p className="BracketSchedule-roundMatch-result-text BracketSchedule-roundMatch-result2-text">0</p>
            </div>
            <div className="BracketSchedule-roundMatch-name-container">
              <p>Đội B</p>
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
)(BracketSchedule);