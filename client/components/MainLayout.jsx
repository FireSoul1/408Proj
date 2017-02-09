
import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    const { authUser } = this.props
    return (
      <div>
        <Navigation />
        <this.props.activeView
          authUser={() => authUser()}
        />
      </div>
    )
  }
}

export default MainLayout