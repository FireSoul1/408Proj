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
   
      this.calendarType = ''
      this.calendarExist =  false
      this.GoogleFunc = this.GoogleFunc.bind(this);
  }

  GoogleFunc(){
    const { advice, authorized, getCalendars, getCalendarType, getLogout, setActiveView } = this.props

     this.calendarType = 'Google'
      setActiveView(ImportPage)
    //this.getCalendars()
     alert('alert');


  }

  
  // buttons for importing google and outlook calendars

	 render() {
	 	return (
      <div className='container'>
        <Jumbotron>
          <p>Choose type of calendar to import.</p>
        <Button bsStyle='primary' className='Googlebtn' onClick={() => setActiveView(ImportPage)} > Google </Button>
        <p>     </p>
        <Button bsStyle='primary' className='Outlookbtn' onClick={this.props.getCalendars()}>Outlook </Button>
         <p>     </p>
        <Button bsStyle='primary' className='alertbtn' onClick={()=>{ alert('alert'); }}>alert</Button>
        <p>     </p>
        <Button bsStyle='primary' className='testbtn' href = './ImportPage'>test</Button>
        <p>     </p>
        <Button bsStyle='primary' className='testbtn' onClick = {this.GoogleFunc.bind()} >test2</Button>
        </Jumbotron>
      </div>
  )
	 }
	}
	export default CalendarPage
	

	