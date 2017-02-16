import React from 'react'
import { render } from 'react-dom'
import ajax from 'jquery'
import { isEmpty } from 'lodash'

import 'style/bootswatch'

import LoginPage from './LoginPage'
import MainLayout from './MainLayout'
import UserPage from './UserPage'

class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      activeView: LoginPage,
      authorized: false,
      user: {}
    }
  }

  componentDidMount() {
    this.getAuthorized()
  }

  componentDidUpdate() {
    const { authorized } = this.state

    if (!this.isActiveView(UserPage) && authorized) {
      this.setActiveView(UserPage)
    }

    if (!this.isActiveView(LoginPage) && !authorized) {
      this.setActiveView(LoginPage)
    }
  }

  getAuthorized() {
    ajax({
      url: '/me',
      method: 'GET',
      success: (user, status, xhr) => {
        const ct = xhr.getResponseHeader('content-type') || '';

        if (ct.indexOf('json') > -1) {
          this.setState({ user, authorized: true })
          return
        }

        this.setState({ user: {}, authorized: false })
      },
      error: response => {
        this.setState({ user: {}, authorized: false })
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
        isActiveView={view => this.isActiveView(view)}
        setActiveView={activeView => this.setActiveView(activeView)}
        user={this.state.user}
      />
    )
  }
}

render(<App/>, document.getElementById('app'))
