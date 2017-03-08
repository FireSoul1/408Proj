import React from 'react'
import {
  MenuItem,
  Nav,
  Navbar,
  NavDropdown
} from 'react-bootstrap'

import ImportPage from './ImportPage'
import StressFormPage from './StressFormPage'
import SweetAlert from 'react-bootstrap-sweetalert';

class Navigation extends React.Component {

    handleAlertDismiss() {
        this.setState({alert: false});
    }
    handleAlertShow() {
        this.setState({alert: true});
    }

  renderDropdown() {
    const { authorized, getCalendars, getLogout, setActiveView, getAdvice} = this.props

    if (authorized) {
      return (
        <Nav pullRight>
          <NavDropdown title='Tools' id='basic-nav-dropdown'>
            <MenuItem onClick={() => getCalendars()}>
              Import Calendar
            </MenuItem>
            <MenuItem onClick={() => setActiveView(StressFormPage)}>
              Rate Events
            </MenuItem>
            <Alerting2 />
            <MenuItem divider/>
            <MenuItem onClick={() => getLogout()}>
              Logout
            </MenuItem>
          </NavDropdown>
        </Nav>
      )
    }
  }

  render() {
    return (
      <Navbar fixedTop>
        <Navbar.Header>
          <Navbar.Brand>
            <a href="/">Stress Manager</a>
          </Navbar.Brand>
        </Navbar.Header>
        {this.renderDropdown()}
      </Navbar>
    )
  }
}
const Alerting2 = React.createClass({
    getInitialState() {
        return {
            alertVisible: false
        };
    },
    render() {
        if (this.state.alertVisible) {
            return (
                <SweetAlert
                    bsStyle="success"
                    title="Advice"
                    onConfirm={this.handleAlertDismiss}>
                    <p>Here is some advice</p>
                </SweetAlert>
            );
        }
        return (
            <MenuItem onClick={this.handleAlertShow}>
                Advice
            </MenuItem>
        );
    },

    handleAlertDismiss() {
        this.setState({alertVisible: false});
    },
    handleAlertShow() {
        this.setState({alertVisible: true});
    }
});

export default Navigation
