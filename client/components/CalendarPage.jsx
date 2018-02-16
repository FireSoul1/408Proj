import React from 'react'
class CalendarPage extends React.Component {
	
  constructor(props) {
  super(props)
   this.state = {
      calendarType: 'Google'
    }
  }


 renderAlert() {
      const { alertVisible } = this.state

      if (alertVisible) {
        return (
          <SweetAlert
            type="error"
            title="Error"
            onConfirm={() => this.setState({ alertVisible: false })}>
            <p><strong>Pick a Calendar to Import</strong></p>
          </SweetAlert>
        )
      }
      return null
  }

  

	 render() {
	 	return (
      <div className='container'>
         {this.renderAlert()}
        <Jumbotron>
          <p>Import Google CalendarPage.</p>
 <Button bsStyle='primary' className='Outlookbtn' onClick={() => this.setState({calendarType: 'Outlook'})}>
        {this.state.calendarType} >Outlook </Button>
        <Button bsStyle='primary' className='Outlookbtn' onClick={() => this.setState({calendarType: 'Outlook'})}>
        {this.state.calendarType} >Outlook </Button>
        </Jumbotron>
      </div>
      )
	 }
	}
	export default CalendarPage
	

	