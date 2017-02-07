import React from 'react'

class MainLayout extends React.Component {
  render() {
    return (
      <div id='container'>
        <p>Hello</p>
        <this.props.activeView />
      </div>
    )
  }
}

export default MainLayout
