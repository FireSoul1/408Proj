import React from 'react'
class CalendarPage extends React.Component {
	getImportCalendar() {
    ajax({
      url: '/calendar/import',
      type: 'get',
      success: (data, status, xhr) => {
        this.setState({ authorized: true })
      },
      error: response => {
        // TODO give feedback to user
        console.log(response)
      }
    })
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
	

	