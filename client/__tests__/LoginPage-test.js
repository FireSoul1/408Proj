import React from 'react';
import ReactTestUtils from 'react-addons-test-utils';
import {shallow} from 'enzyme';
import LoginPage from '../components/LoginPage';

import renderer from 'react-test-renderer';

test('Renders Login Page correctly', () => {
  const tree = renderer.create(
    <LoginPage />
  ).toJSON();
  expect(tree).toMatchSnapshot();
});

test('Check to see if button exists and can click it', () => {
  // Render a checkbox with label in the document
  	const signButton = shallow(
	  	<LoginPage />
    );
    signButton.simulate('click');
	expect(signButton.contains('signinbtn'));
});
