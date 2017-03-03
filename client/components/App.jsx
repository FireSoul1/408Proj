import React from 'react'
import { render } from 'react-dom'
import { ajax } from 'jquery'
import { isEmpty } from 'lodash'

import 'style/bootswatch'

import ImportPage from './ImportPage'
import LoginPage from './LoginPage'
import MainLayout from './MainLayout'
import UserPage from './UserPage'

class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      activeView: LoginPage,
      authorized: false,
      calendarList: [],
      eventList: [],
      user: {}
    }
  }

  componentDidMount() {
    this.getAuthorized()
    this.getEventList()
  }

  componentDidUpdate() {
    const { authorized } = this.state

    if (!this.isActiveView(LoginPage) && !authorized) {
      this.setActiveView(LoginPage)
    }
  }

  responseIsJson(xhr) {
    const ct = xhr.getResponseHeader('content-type') || '';

    return (ct.indexOf('json') > -1)
  }

  getAuthorized() {
    ajax({
      url: '/me',
      type: 'get',
      success: (user, status, xhr) => {
        if (this.responseIsJson(xhr)) {
          this.setState({ user, authorized: true })
          this.setActiveView(UserPage)
          return
        }

        this.setState({ user: {}, authorized: false })
      },
      error: response => {
        this.setState({ user: {}, authorized: false })
      }
    })
  }

  getCalendars() {
    ajax({
      url: '/calendar/list',
      type: 'get',
      success: (data, status, xhr) => {
        if (this.responseIsJson(xhr)) {
          this.setState({ calendarList: data.items })
        }

        this.setActiveView(ImportPage)
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  getEventList() {
    ajax({
      url: '/me/calendar/events',
      type: 'get',
      success: (data, status, xhr) => {
        if (this.responseIsJson(xhr)) {
          this.setState({ eventList: data.items })
        }
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  getLogout() {
    ajax({
      url: '/logout',
      type: 'get',
      success: (data, status, xhr) => {
        this.setState({ authorized: false })
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  setActiveView(activeView) {
    this.setState({ activeView })
  }

  isActiveView(view) {
    return view === this.state.activeView
  }

  render() {
    return (
      <MainLayout
        authorized={this.state.authorized}
        activeView={this.state.activeView}
        calendarList={this.state.calendarList}
        eventList={this.state.eventList}
        getCalendars={() => this.getCalendars()}
        getLogout={() => this.getLogout()}
        user={this.state.user}
      />
    )
  }
}

render(<App/>, document.getElementById('app'))
