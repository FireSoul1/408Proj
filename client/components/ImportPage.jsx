import React from 'react'
import {
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'

class ImportPage extends React.Component {
  renderOptions() {
    const { calendarList } = this.props

    const options = map(calendarList, calendar => {
      return (<option value={calendar.id}>{calendar.summary}</option>)
    })

    return options
  }

  render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Please select a calendar to import from the dropdown below</p>

          <FormGroup controlId="formControlsSelect">
            <FormControl componentClass="select" placeholder="select">
              {this.renderOptions()}
            </FormControl>
          </FormGroup>

        </Jumbotron>
      </div>
    )
  }
}

export default ImportPage
