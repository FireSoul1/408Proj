import React from 'react'
import { Button, Jumbotron } from 'react-bootstrap'
import ReactSpinner from 'react-spinjs'


const Alerting = React.createClass({
    getInitialState() {
        return {
            alertVisible: true
        };
    },

    render() {
        if (this.state.alertVisible) {
            return (
                <Alert bsStyle="success" onDismiss={this.handleAlertDismiss}>
                    <h4>Stress Help!</h4>
                    <p>Here is some sdvice</p>
                    <p>
                        <Button bsStyle="danger">Take this action</Button>
                        <span> or </span>
                        <Button onClick={this.handleAlertDismiss}>Hide Alert</Button>
                    </p>
                </Alert>
            );
        }
        return (
            <Button onClick={this.handleAlertShow}>Show Alert</Button>
        );
    },

    handleAlertDismiss() {
        this.setState({alertVisible: false});
    },

    handleAlertShow() {
        this.setState({alertVisible: true});
    }
});

export default Alerting
