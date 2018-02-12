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

   postImportCalendar() {
    const data = {
      userName: this.state.user.name
    }
    ajax({
      url: '/calendar/import',
      type: 'post',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: () => {
        // TODO give feedback to user
        console.log("Added Imported Calendar Successfully")
        this.getEventList()
        //this.setActiveView(UserPage)
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
	

	