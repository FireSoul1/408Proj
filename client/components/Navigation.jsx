import React from 'react'
import {
  MenuItem,
  Nav,
  Navbar,
  NavDropdown
} from 'react-bootstrap'
import SweetAlert from 'react-bootstrap-sweetalert'

import ImportPage from './ImportPage'
import StressFormPage from './StressFormPage'
import UserPage from './UserPage'
import CalendarPage from './CalendarPage'

class Navigation extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      alertVisible: false
    }
  }

  renderAlert() {
    const { advice } = this.props
    const { alertVisible } = this.state

    if (alertVisible) {
      return (
        <SweetAlert
          title="Advice"
          onConfirm={() => this.setState({ alertVisible: false })}>
          <h4>{advice}</h4>
        </SweetAlert>
      )
    }

    return null
  }

  renderDropdown() {
    const { advice, authorized, getCalendars, getLogout, setActiveView } = this.props

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
            <MenuItem onClick={() => setActiveView(CalendarPage)}>
              Calendar Page
            </MenuItem>
            <MenuItem onClick={() => this.setState({ alertVisible: true })}>
              Advice
            </MenuItem>
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
            <a href="#" onClick={() => this.props.setActiveView(UserPage)}>Stress Manager</a>
          </Navbar.Brand>
        </Navbar.Header>
        {this.renderDropdown()}
        {this.renderAlert()}
      </Navbar>
    )
  }
}

export default Navigation
