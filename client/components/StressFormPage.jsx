import React from 'react'
import { extend, map } from 'lodash'
import {
  ControlLabel,
  FormControl,
  FormGroup,
  HelpBlock,
  Panel
} from 'react-bootstrap'

class StressFormPage extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      value: {}
    }
  }

  getValidationState(id) {
    if (this.state.value[id]) {
      const val = this.state.value[id]
      const num = this.filterInt(val)

      if (Number.isNaN(num)) { return 'error' }
      if (num > 10 || num < -10) {return 'error'}

      return 'success'
    }

    return null
  }

  filterInt(value) {
    if (/^(\-|\+)?([0-9]+|Infinity)$/.test(value)) { return Number(value) }

    return NaN
  }

  handleChange(e, id) {
    let newVal = {}
    newVal[id] = e.target.value

    const value = extend(this.state.value, newVal)
    this.setState({ value })
  }

  renderForms() {
    const { unratedEvents } = this.props

    return map(unratedEvents, event => {
      return (
        <FormGroup
          controlId='formBasicText'
          key={event.id}
          validationState={this.getValidationState(event.id)}
        >
          <ControlLabel>{event.summary}</ControlLabel>
          <FormControl
            type='text'
            value={this.state.value[event.id] || ''}
            placeholder='Enter Text'
            onChange={e => this.handleChange(e, event.id)}
          />
          <HelpBlock>Validation is based integers between -10 and 10.</HelpBlock>
        </FormGroup>
      )
    })
  }

  render() {
    const title = (<h3>How to DESTRESS your week!!</h3>)

    return (
      <div className='container'>
        <Panel header={title}>
          <p>For each event rate how stressed you feel about it on a scale of -10 to 10.</p>
          <p>Any event that stresses you out should have a positive value, and any event that destresses you should have a negative value. </p>
          <p>10 means you are the most stressed about this event.</p>
          <p>-10 means this event is most destressing for you.</p>
        </Panel>

        {this.renderForms()}
      </div>
    )
  }
}

export default StressFormPage
