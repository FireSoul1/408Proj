import React from 'react'
import { Button, Jumbotron } from 'react-bootstrap'

import UrlHelper from 'helpers/UrlHelper'

class LoginPage extends React.Component {
  render() {
    return (
      <div className='container'>
        <Jumbotron>
          <p>Please sign in with your Google Account to use Stress Manager.</p>
          <Button href={UrlHelper.serverUrl('/login/google')} className='signinbtn'>Sign in</Button>
        </Jumbotron>
      </div>
    )
  }
}

export default LoginPage
