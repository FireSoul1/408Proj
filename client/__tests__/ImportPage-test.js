import React from 'react';
import {shallow} from 'enzyme';
import ImportPage from '../components/ImportPage';

// Note: test renderer must be required after react-native.
import renderer from 'react-test-renderer';

//tests to see if import page renders correctly
test('Renders import page correctly', () => {
  const tree = renderer.create(
    <ImportPage />
  ).toJSON();
  expect(tree).toMatchSnapshot();
});