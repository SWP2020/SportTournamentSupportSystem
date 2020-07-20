import React, { ReactNode } from 'react';
import { connect } from 'react-redux';
import 'react-tabs/style/react-tabs.css';
import './styles.css';
import { Tabs, TabList, Tab, TabPanel } from 'react-tabs';

interface ICustomTabProps extends React.ClassAttributes<CustomTab> {
  tabList: string[];
  componentList: ReactNode[];
  selectedIndex: number;
}

interface IModalState {
}

class CustomTab extends React.Component<ICustomTabProps, IModalState> {
  constructor(props: ICustomTabProps) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <Tabs defaultIndex={this.props.selectedIndex}>
        <TabList>
          {this.props.tabList.map((item, index) => {
            return (<Tab key={index}>{item}</Tab>);
          })}
        </TabList>
        {this.props.componentList.map((item, index) => {
          return (<TabPanel key={index}>{item}</TabPanel>);
        })}
      </Tabs>
    );
  }
}

export default connect(
  null,
  null
)(CustomTab);