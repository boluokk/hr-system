const proxyObj = {
  '/': {
    ws: false,
    target: 'http://127.0.0.1:9527',
    changeOrigin: true,
    pathRewrite: {
      '^/': ''
    }
  },
  '/ws': {
    ws: true,
    target: 'ws://127.0.0.1:9527'
  }
}
module.exports = {
  devServer: {
    host: 'localhost',
    port: 9090,
    proxy: proxyObj
  }
}
