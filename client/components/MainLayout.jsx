import React from 'react'

import Navigation from './Navigation'

class MainLayout extends React.Component {
  render() {
    return (
      <div>
        <Navigation
          authorized={this.props.authorized}
          getCalendars={() => this.props.getCalendars()}
          getLogout={() => this.props.getLogout()}
          getAdvice={() => this.props.getAdvice()}
          setActiveView={activeView => this.props.setActiveView(activeView)}
          advice={this.props.advice}
          user={this.props.user}
        />
        <this.props.activeView
          calendarList={this.props.calendarList}
          eventList={this.props.eventList}
          postCalendarAdd={calID => this.props.postCalendarAdd(calID)}
          postCalendarEvent={(calEvent, stressValue, navigateTo) => this.props.postCalendarEvent(calEvent, stressValue, navigateTo)}
          unratedEvents={this.props.unratedEvents}
        />
      </div>
    )
  }
}

export default MainLayout
