import React from 'react'
import BigCalendar from 'react-big-calendar'
import { has } from 'lodash'
import moment from 'moment'

BigCalendar.momentLocalizer(moment)

class UserPage extends React.Component {
  accessor(time, event) {
    const dateTimeString = `${time}.dateTime`
    const dateString = `${time}.date`

    if (has(event, dateTimeString)) {
      return moment(event[time]['dateTime']).toDate()
    } else if (has(event, dateString)) {
      return moment(event[time]['date']).toDate()
    }
  }

  render() {
    return (
      <div className='container'>
        <BigCalendar
          defaultView='week'
          views={['week']}
          events={this.props.eventList}
          startAccessor={event => this.accessor('start', event)}
          endAccessor={event => this.accessor('end', event)}
          allDayAccessor={event => has(event, 'start.date') && has(event, 'end.date')}
          titleAccessor='summary'
        />
      </div>
    )
  }
}

module.exports = UserPage
