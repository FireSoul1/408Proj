import React from 'react';
import {shallow} from 'enzyme';
import StressFormPage from '../components/StressFormPage';
import ReactTestUtils from 'react-addons-test-utils';

// Note: test renderer must be required after react-native.
import renderer from 'react-test-renderer';

//tests to see if stress form page renders correctly
test('Renders stress form page correctly', () => {
  const tree = renderer.create(
    <StressFormPage />
  ).toJSON();
  expect(tree).toMatchSnapshot();
});


test('Check to see if submit button exists and can click it', () => {
  // Render a checkbox with label in the document
  	const submitButton = shallow(
	  	<StressFormPage />
    );
    submitButton.simulate('click');
	expect(submitButton.contains('submit'));
});