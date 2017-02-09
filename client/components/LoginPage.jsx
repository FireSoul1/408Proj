import React from 'react'
import { Button, Jumbotron } from 'react-bootstrap'

class LoginPage extends React.Component {
  render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Please sign in with your Google Account to use Stress Manager.</p>
          <Button href='/login/google'>Sign in</Button>
        </Jumbotron>
      </div>
    )
  }
}

export default LoginPage
