import React from 'react'
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'
import SweetAlert from 'react-bootstrap-sweetalert'


class ImportPage extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      calID: '',
      alertVisible: false,
      rickVisible: false
    }
  }

  renderOptions() {
    const { calendarList } = this.props

    let options = map(calendarList, calendar => {
      return (<option key={calendar.id} value={calendar.id}>{calendar.summary}</option>)
    })

    options.push(<option key='rick' value='rick'>Rick Astley</option>)

    return options
  }
  postTheCalID(calID) {
      const { postCalendarAdd, getEventList } = this.props

      if (calID =="") {
          this.setState({alertVisible: true})
          return
      } else if (calID == 'rick') {
        this.setState({ rickVisible: true })
        return
      }
      postCalendarAdd(calID)

  }
  renderAlert() {
      const { alertVisible } = this.state

      if (alertVisible) {
        return (
          <SweetAlert
            type="error"
            title="Error"
            onConfirm={() => this.setState({ alertVisible: false })}>
            <p><strong>Pick a Calendar to Import</strong></p>
          </SweetAlert>
        )
      }
      return null
  }

  renderRick() {
    const { rickVisible } = this.state

    if (rickVisible) {
      return (
        <SweetAlert
          title="Rick Astley's Calendar"
          onConfirm={() => this.setState({ rickVisible: false })}
        >
          <iframe src="https://www.youtube.com/embed/dQw4w9WgXcQ?autoplay=1" frameBorder="0" allowFullScreen/>
        </SweetAlert>
      )
    }
  }

  render() {
    const { postCalendarAdd } = this.props
    const { calID } = this.state

    return (
      <div className='container'>
          {this.renderAlert()}
          {this.renderRick()}
        <Jumbotron>
          <p>Please select a calendar to import from the dropdown below</p>
          <FormGroup controlId="formControlsSelect">
            <FormControl
              componentClass="select"
              onChange={e => this.setState({ calID: e.target.value })}
              placeholder="select"
            >
              {this.renderOptions()}
            </FormControl>
          </FormGroup>

          <Button
            bsStyle='primary'
            className='submit-button-right'
            onClick={() => this.postTheCalID(calID)}
          >
            Submit
          </Button>

        </Jumbotron>
      </div>
    )
  }
}

export default ImportPage
