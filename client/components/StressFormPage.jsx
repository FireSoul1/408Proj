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
		    const length = this.state.value[id].length

		    if (length > 10) return 'success';
		    else if (length > 5) return 'warning';
		    else if (length > 0) return 'error';
		}
		
		return null
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