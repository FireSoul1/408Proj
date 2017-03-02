import React from 'react';
import {shallow} from 'enzyme';
import Navigation from '../components/Navigation';

// Note: test renderer must be required after react-native.
import renderer from 'react-test-renderer';

//tests to see if navigation renders correctly
test('Renders navigation correctly', () => {
  const tree = renderer.create(
    <Navigation />
  ).toJSON();
  expect(tree).toMatchSnapshot();
});

//regression tests to see if active view is created
test('Regression tests for active view', () => {
    const nav = shallow(
	  	<Navigation />
    );
  expect(nav.contains('activeView'));
});