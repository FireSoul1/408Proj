import React from 'react'
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron,
  MenuItem,
  Nav,
  Navbar,
  NavDropdown
} from 'react-bootstrap'
import { map } from 'lodash'
import SweetAlert from 'react-bootstrap-sweetalert'

import ImportPage from './ImportPage'
import StressFormPage from './StressFormPage'
import UserPage from './UserPage'
import Navigation from './Navigation'


class CalendarPage extends React.Component {
	
  constructor(props) {
     console.log("props at start of calendar page")
    console.log(props)

    super(props)
     //this.setActiveView = this.setActiveView.bind(this);
    console.log("choose calendar service")
    console.log(this.props)
     
      this.calendarType = ''
      this.calendarExist =  false
     this.GoogleFunc = this.GoogleFunc.bind(this);
  }

  GoogleFunc(){
    const { getCalendars, setActiveView } = this.props
     //this.calendarType = 'Google'
     //getCalendars()


  }
  
  // buttons for importing google and outlook calendars

   render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Choose type of calendar to import.</p>
        <Button bsStyle='primary' className='Googlebtn' onClick={() => this.props.getCalendars()}> Google </Button>
        <p>     </p>
        <Button bsStyle='primary' className='Outlookbtn' > Outlook </Button>
        
        </Jumbotron>
        
      </div>
      )
   }
  }
  export default CalendarPage


	