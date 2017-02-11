import 'jsdom-global/register'

import React from 'react'
import renderer from 'react-test-renderer'
import {mount} from 'enzyme';

import MainLayout from '../components/MainLayout'

const FirstActiveView = () => {
  return (
    <p>First active view</p>
  )
}

const SecondActiveView = () => {
  return (
    <p>Second active view</p>
  )
}

test('Renders main layout', () => {
  const tree = renderer.create(
    <MainLayout
      activeView={FirstActiveView}
    />
  ).toJSON();
  expect(tree).toMatchSnapshot();
});

test('Renders active views correctly', () => {
  const firstTree = mount(
    <MainLayout
      activeView={FirstActiveView}
    />
  )

  const secondTree = mount(
    <MainLayout
      activeView={SecondActiveView}
    />
  )

  expect(firstTree).not.toBe(secondTree)
  expect(firstTree.contains(<p>First active view</p>)).toBeTruthy()
  expect(secondTree.contains(<p>Second active view</p>)).toBeTruthy()
})
