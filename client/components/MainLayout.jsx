
import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    const { authUser } = this.props
    return (
      <div>
        <Navigation
          authorized={this.props.authorized}
          getCalendars={() => this.props.getCalendars()}
          getLogout={() => this.props.getLogout()}
          user={this.props.user}
        />
        <this.props.activeView
          calendarList={this.props.calendarList}
        />
      </div>
    )
  }
}

export default MainLayout
