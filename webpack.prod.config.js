const webpack = require('webpack')
const path = require('path')

var BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static');
const APP_DIR = path.resolve(__dirname, 'client')

const config = {
  devtool: 'hidden-source-map',
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
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        'NODE_ENV': JSON.stringify('production')
      }
    }),
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        warnings: false,
        screw_ie8: true,
        conditionals: true,
        unused: true,
        comparisons: true,
        sequences: true,
        dead_code: true,
        evaluate: true,
        if_return: true,
        join_vars: true,
        drop_debugger: true,
        drop_console: true,
      }
    }),
    new webpack.LoaderOptionsPlugin({
      minimize: true,
      debug: false
    }),
  ]
}

module.exports = config
