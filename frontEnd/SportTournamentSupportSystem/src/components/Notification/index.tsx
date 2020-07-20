import * as React from 'react';
import './styles.css';

interface INotificationProps extends React.ClassAttributes<Notification> {
  title: string;
  content: string | React.ReactNode;
  time?: string;
}

class Notification extends React.Component<INotificationProps> {
  render() {
    return (
      <div className={'Notification'}>
        <h6>{this.props.title}</h6>
        {typeof this.props.content === 'string' ? <p>{this.props.content}</p> : this.props.content}
        <p>{this.props.time}</p>
      </div>
    );
  }
}

export default Notification;
