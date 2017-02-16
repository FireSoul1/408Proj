
import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    const { authUser } = this.props
    return (
      <div>
        <Navigation
          authorized={this.props.authorized}
          user={this.props.user}
        />
        <this.props.activeView />
      </div>
    )
  }
}

export default MainLayout
