import React from 'react';
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
