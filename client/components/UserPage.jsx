import React from 'react'
import BigCalendar from 'react-big-calendar'
import { has } from 'lodash'
import { ajax } from 'jquery'
import moment from 'moment'
import SweetAlert from 'react-bootstrap-sweetalert'


import 'style/bootswatch'


BigCalendar.momentLocalizer(moment)

class UserPage extends React.Component {
    constructor(props) {
        super(props)

        this.state = {
            calID: '',
            alert: true,
            eventList: []
        }
    }

    accessor(time, event) {
        const dateTimeString = `${time}.dateTime`
        const dateString = `${time}.date`

        if (has(event, dateTimeString)) {
            return moment(event[time]['dateTime']).toDate()
        } else if (has(event, dateString)) {
            return moment(event[time]['date']).toDate()
        }
    }

    componentDidMount() {
        this.getEventList()
    }
    getEventList() {
        const data = {
            userName: this.props.user.name
        }

        ajax({
            url: '/api/calendar/events',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: (data) => {
                this.setState({ eventList: data.items })
                this.setState({alert: false})

            },
            error: response => {
                // TODO give feedback to user
                console.log(response)
            }
        })
        ;

    }
    renderAlert() {
        const { alert } = this.state
        if (alert) {
            return (
                <SweetAlert
                    title="Loading User Data"
                    onConfirm={() => this.setState({alert:false})}
                    >
                    <div className="loader"></div>
                </SweetAlert>
            )
        }
        return (
            <div></div>
        )
    }
    renderCalendar() {
        const { alert } = this.state

        if (!alert) {
            return (
                <BigCalendar
                    defaultView='week'
                    views={['week']}
                    events={this.state.eventList}
                    eventPropGetter={(event, start, end, isSelected) => this.eventPropGetter(event, start, end, isSelected)}
                    startAccessor={event => this.accessor('start', event)}
                    endAccessor={event => this.accessor('end', event)}
                    allDayAccessor={event => has(event, 'start.date') && has(event, 'end.date')}
                    titleAccessor='summary'
                    />
            )
        }
        return (
            <div></div>
        )
    }
    eventPropGetter(event, start, end, isSelected) {
        const selected = isSelected ? '-selected' : ''

        if (event.stressValue === null || event.stressValue === undefined) {
            return { className: `event-unrated${selected}` }
        } else if (event.stressValue === 0) {
            return { className: `event-no-stress${selected}` }
        } else if (event.stressValue > 0 && event.stressValue <= 10) {
            return { className: `event-stress-${event.stressValue}${selected}` }
        } else if (event.stressValue < 0 && event.stressValue >= -10) {
            return { className: `event-destress-${Math.abs(event.stressValue)}${selected}`}
        }

        return { className: `event-unrated${selected}` }
    }

    render() {
        return (
            <div className='container'>
                {this.renderAlert()}
                {this.renderCalendar()}
            </div>
        )
    }
}

module.exports = UserPage
