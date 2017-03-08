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

  eventPropGetter(event, start, end, isSelected) {
    const selected = isSelected ? '-selected' : ''

    if (event.stressValue === null || event.stressValue === undefined) {
      return { className: `event-unrated${selected}` }
    } else if (event.stressValue === 0) {
      return { className: `event-no-stress${selected}` }
    } else {
      if (event.stressValue > 0 && event.stressValue <= 10) {
        return { className: `event-stress-${event.stressValue}${selected}` }
      } else if (event.stressValue < 0 && event.stressValue >= -10) {
        return { className: `event-destress-${Math.abs(event.stressValue)}${selected}`}
      }

      return null
    }
  }

  render() {
    return (
      <div className='container'>
        <BigCalendar
          defaultView='week'
          views={['week']}
          events={this.props.eventList}
          eventPropGetter={(event, start, end, isSelected) => this.eventPropGetter(event, start, end, isSelected)}
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
