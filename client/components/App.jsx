import React from 'react'
import { render } from 'react-dom'
import { ajax } from 'jquery'
import { isEmpty, filter } from 'lodash'

import 'style/bootswatch'

import ImportPage from './ImportPage'
import LoginPage from './LoginPage'
import MainLayout from './MainLayout'
import UserPage from './UserPage'
import StressFormPage from './StressFormPage'
import SweetAlert from 'react-bootstrap-sweetalert';
import { Button, Alert } from 'react-bootstrap'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      activeView: LoginPage,
      authorized: false,
      calendarList: [],
      eventList: [],
      user: {},
      alert: false,
      advice: "Got nothing"
    }
  }

  // Component Lifecycle Methods

  componentDidMount() {
    this.getAuthorized()
    this.getAdvice()
  }

  componentDidUpdate() {
    const { authorized } = this.state

    if (!this.isActiveView(LoginPage) && !authorized) {
      this.setActiveView(LoginPage)
    }
  }

  // API Helpers

  responseIsJson(xhr) {
    const ct = xhr.getResponseHeader('content-type') || '';

    return (ct.indexOf('json') > -1)
  }

  // API Methods

  getAdvice() {
    ajax({
      url: '/advice',
      type: 'get',
      success: (data, status, xhr) => {
        this.setState({ advice: data.advice })
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  getAuthorized() {
    ajax({
      url: '/me',
      type: 'get',
      success: (user, status, xhr) => {
        if (this.responseIsJson(xhr)) {
          this.setState({ user, authorized: true })
          this.setActiveView(UserPage)
          this.getEventList()
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
    const data = {
      userName: this.state.user.name
    }

    ajax({
      url: '/me/calendar/events',
      type: 'post',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: (data) => {
        this.setState({ eventList: data.items })
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

  postCalendarAdd(calID) {
    const data = {
      calID,
      userName: this.state.user.name
    }

    ajax({
      url: '/calendar/add',
      type: 'post',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: () => {
        // TODO give feedback to user
        console.log("Added Calendar Successfully")

        this.setActiveView(UserPage)
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  postCalendarEvent(calEvent, stressValue, navigateTo) {
    const data = {
      calEvent,
      stressValue,
      userName: this.state.user.name
    }

    ajax({
      url: '/calendar/event',
      type: 'post',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: () => {
        console.log(`Added stressValue ${stressValue} to event with id ${calEvent}`)

        if (navigateTo) {
          this.setActiveView(navigateTo)
        }
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
  }

  // App Methods

  isActiveView(view) {
    return view === this.state.activeView
  }

  setActiveView(activeView) {
    this.setState({ activeView })
  }

  unratedEvents() {
    return filter(this.state.eventList, event => {
      return event.stressValue === null || event.stressValue === undefined
    })
  }

  render() {
    return (
      <div className="container">
        <MainLayout
          activeView={this.state.activeView}
          advice={this.state.advice}
          authorized={this.state.authorized}
          calendarList={this.state.calendarList}
          eventList={this.state.eventList}
          getCalendars={() => this.getCalendars()}
          getLogout={() => this.getLogout()}
          postCalendarAdd={calId => this.postCalendarAdd(calId)}
          postCalendarEvent={(calEvent, stressValue, navigateTo) => this.postCalendarEvent(calEvent, stressValue, navigateTo)}
          unratedEvents={this.unratedEvents()}
          user={this.state.user}
          setActiveView={activeView => this.setActiveView(activeView)}
        />
      </div>
    )
  }
}


render(<App/>, document.getElementById('app'))
