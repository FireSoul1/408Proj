import React from 'react'
class CalendarPage extends React.Component {
	
  constructor(props) {
  super(props)
  }

  

	 render() {
	 	return (
      <div className='container'>
        <Jumbotron>
          <p>Choose which calendar to import.</p>
          <Button bsStyle='primary' className='signinbtn' >Sign in</Button>
        </Jumbotron>
      </div>
)
	 }
	}
	export default CalendarPage
	

	