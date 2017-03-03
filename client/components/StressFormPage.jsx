import React from 'react'
import { extend, map } from 'lodash'
import {
  FormControl,
  FormGroup,
  ControlLabel,
  HelpBlock
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

  		if (/^(\-|\+)?([0-9]+|Infinity)$/.test(value)) { return Number(value); }

  		return NaN;
  	}

  	handleChange(e, id) {
  		let newVal = {}
  		newVal[id] = e.target.value
  		const value = extend(this.state.value, newVal)
   		this.setState({ value });
  	}

	renderForms() {

		const { unratedEvents } = this.props
		const options = map(unratedEvents, event => {
		  	return (
			  <form key={event.id}>
		        <FormGroup
		          controlId="formBasicText"
		          validationState={this.getValidationState(event.id)}
		        >
		          <ControlLabel>{event.summary}</ControlLabel>
		          <FormControl
		            type="text"
		            value={this.state.value[event.id] || ''}
		            placeholder="Enter Text"
		            onChange={e => this.handleChange(e, event.id)}
		          />
		          <HelpBlock>Validation is based integers between -10 and 10.</HelpBlock>
		        </FormGroup>
		      </form>
			)
		})

		return options
  	}

	render() {
	    return (
	      <div className='container'>

	      	{this.renderForms()}
	        
	      </div>
	    )
 	}
}

export default StressFormPage