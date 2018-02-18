import React from 'react'
import {
  Button,
  FormControl,
  FormGroup,
  Jumbotron
} from 'react-bootstrap'
import { map } from 'lodash'
import SweetAlert from 'react-bootstrap-sweetalert'
import ImportPage from './ImportPage'

class CalendarPage extends React.Component {
	
  constructor(props) {
    super(props)
    this.state = {
      calID: '',
      alertVisible: false
    }
   
      this.calendarType = ''
      this.calendarExist =  false
  }

  GoogleFunc(){
    this.calendarType = 'Google'
    this.setActiveView(ImportPage)
    this.getCalendars()
    return

  }
    postTheCalID(calID) {
      const { postCalendarAdd, getEventList } = this.props

      if(calID =="") {
          this.setState({alertVisible: true})
          return
      }
      postCalendarAdd(calID)
      
  }

  
  // button for importing google and outlook calendars

	 render() {
	 	return (
      <div className='container'>
        <Jumbotron>
          <p>Choose type of calendar to import.</p>
         <Button
            bsStyle='primary'
            className='submit-button-right'
            onClick={() => this.postTheCalID(calID)}
          >
            Submit
          </Button>
        </Jumbotron>
      </div>
  )
	 }
	}
	export default CalendarPage
	

	