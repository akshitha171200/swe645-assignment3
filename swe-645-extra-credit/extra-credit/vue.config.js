module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8081',  // Backend URL
        changeOrigin: true
      }
    }
  }
};
