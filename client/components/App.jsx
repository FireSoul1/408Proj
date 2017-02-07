import React from 'react'
import { render } from 'react-dom'

import 'style/bootswatch'

import MainLayout from './MainLayout'

class TestComponent extends React.Component {
  render() {
    return (
      <p>Hi</p>
    )
  }
}

class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      activeView: TestComponent
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
