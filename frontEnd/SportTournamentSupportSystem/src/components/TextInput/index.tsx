import React from 'react';
import { connect } from 'react-redux';
import './styles.css';

interface ITextInputProps extends React.ClassAttributes<TextInput> {
  label: string;
  type?: string;
  errorContent: string;
  error: boolean;
  defaultValue?: string;

  onChangeText(value: string): void;
  onHandleSubmit?(): void;
}

interface ITextInputState {
}

class TextInput extends React.Component<ITextInputProps, ITextInputState> {
  constructor(props: ITextInputProps) {
    super(props);
    this.state = {
    };
  }

  private onChangeValue = (value: React.ChangeEvent<HTMLInputElement>) => {
    this.props.onChangeText(value.target.value);
  }

  private keyPressed = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter" && this.props.onHandleSubmit) {
      this.props.onHandleSubmit();
    }
  }

  render() {
    return (
      <div className="omrs-input-group">
        {this.props.error === true && <div className="TextInput-error-text-container"><p className="TextInput-error-text">{this.props.errorContent}</p></div>}
        <label className="omrs-input-underlined">
          <input required {...this.props.defaultValue && { defaultValue: this.props.defaultValue }} type={this.props.type} onChange={this.onChangeValue} onKeyPress={this.keyPressed} />
          <span className="omrs-input-label">{this.props.label}</span>
        </label>
      </div>
    );
  }
}

export default connect(
  null,
  null
)(TextInput);