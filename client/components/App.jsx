import React from 'react'
import { render } from 'react-dom'

import 'style/bootswatch'

import LoginPage from './LoginPage'
import MainLayout from './MainLayout'

class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      activeView: LoginPage
    }
  }

  setActiveView(activeView) {
    this.setState({ activeView })
  }

  render() {
    return (
      <MainLayout
        activeView={this.state.activeView}
        setActiveView={activeView => this.setActiveView(activeView)}
      />
    )
  }
}

render(<App/>, document.getElementById('app'))
