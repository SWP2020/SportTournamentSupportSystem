import React from 'react';
import { connect } from 'react-redux';
import BracketBoard from 'components/BracketBoard';
import './styles.css';

interface IHomeProps extends React.ClassAttributes<Home> {
}

interface IHomeState {
}

class Home extends React.Component<IHomeProps, IHomeState> {
  constructor(props: IHomeProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="Container-login">
        <div className="Container-login-middle">
          <h1>Quản lý giải đấu đơn giản</h1>
          <BracketBoard />
          {/* <MultiGrid
            width={400}
            height={250}
            rowCount={5}
            colCount={5}
            rowHeight={50}
            columnCount={5}
            columnWidth={80}
            cellRenderer={() => { return <div><p>A</p></div>; }}
          /> */}
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(Home);