import React from 'react'
import { Jumbotron } from 'react-bootstrap'

class LoginPage extends React.Component {
  render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Please sign in with your Google Account to use Stress Manager.</p>
        </Jumbotron>
      </div>
    )
  }
}

export default LoginPage
