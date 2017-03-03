import React from 'react'
import {
  MenuItem,
  Nav,
  Navbar,
  NavDropdown
} from 'react-bootstrap'

import ImportPage from './ImportPage'

class Navigation extends React.Component {
  renderDropdown() {
    const { authorized, getCalendars } = this.props

    if (authorized) {
      return (
        <Nav pullRight>
          <NavDropdown title='Tools' id='basic-nav-dropdown'>
            <MenuItem onClick={() => getCalendars()}>
              Import Calendar
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
            <a href="#">Stress Manager</a>
          </Navbar.Brand>
        </Navbar.Header>
        {this.renderDropdown()}
      </Navbar>
    )
  }
}

export default Navigation
