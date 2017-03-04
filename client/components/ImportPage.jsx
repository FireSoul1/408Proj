import React from 'react'
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'

class ImportPage extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      calID: ''
    }
  }

  renderOptions() {
    const { calendarList } = this.props

    const options = map(calendarList, calendar => {
      return (<option key={calendar.id} value={calendar.id}>{calendar.summary}</option>)
    })

    return options
  }

  render() {
    const { postCalendarAdd } = this.props
    const { calID } = this.state

    return (
      <div className='container'>
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
            className='import-page-submit'
            onClick={() => postCalendarAdd(calID)}
          >
            Submit
          </Button>

        </Jumbotron>
      </div>
    )
  }
}

export default ImportPage
