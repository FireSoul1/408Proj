import React from 'react'
import './CalendarVariables.jsx'
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
          <Button bsStyle='primary' 
          className='Googlebtn' 
           onClick={() => tthis.setState({ calendarType: 'Google' })}
          Google 
          </Button>
           <Button bsStyle='primary' className='Outlookbtn' onClick={() => this.setState({calendarType: 'Outlook'})}>
        {this.state.calendarType} >Outlook </Button>
        </Jumbotron>
      </div>
)
	 }
	}
	export default CalendarPage
	

	