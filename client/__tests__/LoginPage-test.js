import React from 'react';
import ReactTestUtils from 'react-addons-test-utils'
import LoginPage from '../components/LoginPage';

test('Check to see if button is clickable', () => {
  // Render a checkbox with label in the document
  const button = shallowRenderer.render(
    <LoginPage labelOn="On" labelOff="Off" />
  );

  expect(button.text()).toEqual('Sign in');

  //button.find('input').simulate('change');

  //expect(checkbox.text()).toEqual('On');
});