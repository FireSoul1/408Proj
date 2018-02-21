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

class CalendarPage extends React.Component {
	
  constructor(props) {
    super(props)
   
      this.calendarType = ''
      this.calendarExist =  false
     this.GoogleFunc = this.GoogleFunc.bind(this);
  }

  GoogleFunc(){
    const { getCalendars, setActiveView } = this.props
     window.location += './ImportPage'
     //this.calendarType = 'Google'
     setActiveView(ImportPage)
     getCalendars()
    alert('alert');

  }

/*  toImportPage() {
    const { advice, authorized, getCalendars, getCalendarType, getLogout, setActiveView } = this.props

  
      return (
               <Button bsStyle='primary' className='Calbtn' onClick={() => getCalendars()} > 
               Calendar
               </Button>

      )
    
  }*/
 renderDropdown() {
    const { advice, authorized, getCalendars, getCalendarType, getLogout, setActiveView } = this.props

    if (authorized) {
      return (
        <Nav pullRight>
          <NavDropdown title='Tools' id='basic-nav-dropdown'>
             <MenuItem onClick={() =>  setActiveView(CalendarPage)}>
              Choose Calendar Type
            </MenuItem>
            <MenuItem onClick={() => getCalendars()}>
              Import Calendar
            </MenuItem>
            <MenuItem onClick={() => setActiveView(StressFormPage)}>
              Rate Events
            </MenuItem>
            <MenuItem onClick={() => this.setState({ alertVisible: true })}>
              Advice
            </MenuItem>
            <MenuItem divider/>
            <MenuItem onClick={() => getLogout()}>
              Logout
            </MenuItem>
          </NavDropdown>
        </Nav>
      )
    }
  }
  



  
  // buttons for importing google and outlook calendars

   render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Choose type of calendar to import.</p>
        <Button bsStyle='primary' className='Googlebtn' onClick={() => this.props.setActiveView(ImportPage)}> Google </Button>
        <p>     </p>
        <Button bsStyle='primary' className='Outlookbtn' >Outlook </Button>
         <p>     </p>
        <Button bsStyle='primary' className='alertbtn' onClick={()=>{ alert('alert'); }}>alert</Button>
         <p>     </p>
        <Button bsStyle='primary' className='testbtn' href = 'ImportPage'>test</Button>
         <p>     </p>

        <Button bsStyle='primary' className='testbtn' onClick={() => GoogleFunc()} >test2</Button>
         <p>     </p>
        <Button bsStyle='primary' className='testbtn' onClick={() => this.GoogleFunc()} >test3</Button>
        </Jumbotron>
         <p>     </p>
         {this.renderDropdown()}
      </div>
  )
	 }
	}
	export default CalendarPage

        <Button bsStyle='primary' className='testbtn' onClick = {this.GoogleFunc()} >test2</Button>
        </Jumbotron>
      </div>
  )
   }
  }
  export default CalendarPage


	