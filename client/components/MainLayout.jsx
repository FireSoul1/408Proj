import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    return (
      <div>
        <Navigation />
        <this.props.activeView />
      </div>
    )
  }
}

export default MainLayout
