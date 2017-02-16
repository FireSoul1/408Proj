import React from 'react'
import { render } from 'react-dom'
import ajax from 'jquery'

import 'style/bootswatch'

import LoginPage from './LoginPage'
import MainLayout from './MainLayout'

class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      activeView: LoginPage,
      authenticated: false,
      user: {}
    }
  }

  setActiveView(activeView) {
    this.setState({ activeView })
  }

  authUser() {
    ajax({
      type: 'get',
      url: '/user',
      success: data => {
        this.setState({
          user: data.userAuthentication.details.name,

          authenticated: true

        })
      }
    })
  }

  render() {
    return (
      <MainLayout
        activeView={this.state.activeView}
        authUser={() => this.authUser()}
        setActiveView={activeView => this.setActiveView(activeView)}
      />
    )
  }
}

render(<App/>, document.getElementById('app'))
