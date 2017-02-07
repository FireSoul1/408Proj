const webpack = require('webpack')
const path = require('path')

var BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static');
const APP_DIR = path.resolve(__dirname, 'client')

const config = {
  devtool: 'eval',
  entry: APP_DIR + '/components/App.jsx',
  output: {
    path: BUILD_DIR,
    filename: 'js/bundle.js'
  },
  resolve: {
    modules: [
      APP_DIR,
      'node_modules'
    ],
    extensions: ['.js', '.jsx', '.less']
  },
  module: {
    rules: [
      {
        test: /\.jsx?/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.less$/,
        use: [
          'style-loader',
          'css-loader',
          'less-loader'
        ]
      },
      {
        test: /\.(woff|woff2|eot|ttf)$/,
        loader: 'url-loader?limit=10000&name=fonts/[name].[ext]'
      },
      {
        test: /\.(png|jpg|svg)$/,
        loader: 'url-loader?limit=10000&name=img/[name].[ext]'
      }
    ]
  }
}

module.exports = config
