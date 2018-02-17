import React from 'react'
<<<<<<< HEAD
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'
import SweetAlert from 'react-bootstrap-sweetalert'
<<<<<<< HEAD

import './CalendarVariables.jsx'

=======
>>>>>>> parent of d11d86e... made import statements on CalendarPage.jsx and App.jsx
=======
>>>>>>> 57210a18ffa9012ed729a11d818b59f7dcbc191b
class CalendarPage extends React.Component {
	
  constructor(props) {
  super(props)
   this.state = {
      calendarType: 'Google'
    }
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
	

	