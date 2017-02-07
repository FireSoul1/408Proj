var webpack = require('webpack');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static');
var APP_DIR = path.resolve(__dirname, 'client');

var config = {
  devtool: 'hidden-source-map',
  entry: APP_DIR + '/components/App.jsx',
  output: {
    path: BUILD_DIR,
    filename: 'js/bundle.js'
  },
  module : {
    loaders : [
      {
        test : /\.jsx?/,
        include : APP_DIR,
        loader : 'babel-loader'
      }
    ]
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        'NODE_ENV': JSON.stringify('production')
      }
    })
  ]
};

module.exports = config;
