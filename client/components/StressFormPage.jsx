import React from 'react'
import { extend, map } from 'lodash'
import {
  FormControl,
  FormGroup,
  ControlLabel,
  HelpBlock
} from 'react-bootstrap'

import {eventListResponse} from 'mock/events'

class StressFormPage extends React.Component {

	constructor(props) {
	    super(props)

	    this.state = {
	      value: {}
	    }
    }

	getValidationState(id) {

		if (this.state.value[id]) {
		    const num = this.state.value[id]

		    if (fliterInt(num).isNan()) return 'error';
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

		const options = map(eventListResponse.items, event => {
		  	return (
			  <form key={event.id}>
		        <FormGroup
		          controlId="formBasicText"
		          validationState={this.getValidationState(event.id)}
		        >
		          <ControlLabel>{event.summary}</ControlLabel>
		          <FormControl
		            type="text"
		            value={this.state.value[event.id]}
		            placeholder="Enter Text"
		            onChange={e => this.handleChange(e, event.id)}
		          />
		          <FormControl.Feedback />
		          <HelpBlock>Validation is based on string length.</HelpBlock>
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