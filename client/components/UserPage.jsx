import React from 'react'

import StressCalendar from './StressCalendar'

class UserPage extends React.Component {
  render() {
    return (
      <div className='container'>
        <StressCalendar />
      </div>
    )
  }
}

module.exports = UserPage
