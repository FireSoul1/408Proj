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
      calendarType: ''
      calendarExist: false
    }
  }

  GoogleFunc(){
    this.setState({ calendarType: 'Google' })}
    this.setActiveView(ImportPage)

  }

  
  // button for importing google and outlook calendars

	 render() {
	 	return (
      <div className='container'>
        <Jumbotron>
          <p>Choose type of calendar to import.</p>
        <Button bsStyle='primary' className='Googlebtn'  onClick={() => this.setState({ calendarType: 'Google' })} > Google </Button>
        <p>     </p>
        <Button bsStyle='primary' className='Outlookbtn' onClick={() => this.setState({ calendarType: 'Outlook' })}>Outlook </Button>
        </Jumbotron>
      </div>
  )
	 }
	}
	export default CalendarPage
	

	