import React from 'react';
import { connect } from 'react-redux';
import TournamentOverview from 'components/TournamentOverview';
import './styles.css';

interface IAllTournamentsProps extends React.ClassAttributes<AllTournaments> {
}

interface IAllTournamentsState {
}

class AllTournaments extends React.Component<IAllTournamentsProps, IAllTournamentsState> {
  constructor(props: IAllTournamentsProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="AllTournaments-container-container">
        <p className="AllTournaments-header-text">Tất cả các giải đấu</p>
        {/* {this.props.searchText !== '' && <p className="AllTournaments-search-text">Kết quả tìm kiếm cho: {this.props.searchText}</p>} */}
        <div className="AllTournaments-container">
          <TournamentOverview />
          <TournamentOverview />
          <TournamentOverview />
          <TournamentOverview />
          <TournamentOverview />
          <TournamentOverview />
          <TournamentOverview />
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(AllTournaments);