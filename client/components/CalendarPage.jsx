import React from 'react'
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'
import SweetAlert from 'react-bootstrap-sweetalert'
class CalendarPage extends React.Component {
	
  constructor(props) {
  super(props)
   this.state = {
      calendarType: 'Google'
    }
  }

  

	 render() {
	 	return (
      <div className='container'>
        <Jumbotron>
          <p>Import Google CalendarPage.</p>
        <Button bsStyle='primary' className='Googlebtn'  > Google </Button>
        <Button bsStyle='primary' className='Outlookbtn' >Outlook </Button>
        </Jumbotron>
      </div>
  )
	 }
	}
	export default CalendarPage
	

	