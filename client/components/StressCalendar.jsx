import React from 'react'
import BigCalendar from 'react-big-calendar'
import moment from 'moment'

import events from 'mock/events'

BigCalendar.momentLocalizer(moment)

class StressCalendar extends React.Component {
  render() {
    return (
      <BigCalendar events={events} />
    )
  }
}

module.exports = StressCalendar
