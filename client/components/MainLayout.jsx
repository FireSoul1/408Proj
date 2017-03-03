import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    return (
      <div>
        <Navigation
          authorized={this.props.authorized}
          getCalendars={() => this.props.getCalendars()}
          user={this.props.user}
        />
        <this.props.activeView
          calendarList={this.props.calendarList}
          eventList={this.props.eventList}
        />
      </div>
    )
  }
}

export default MainLayout
