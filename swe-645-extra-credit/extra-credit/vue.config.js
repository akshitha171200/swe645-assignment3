// module.exports = {
//   devServer: {
//     proxy: {
//       '/api': {
//         target: 'http://ec2-54-87-40-151.compute-1.amazonaws.com:30879',  // Backend URL
//         changeOrigin: true,
//         // pathRewrite: {
//         //   '^/api': ''  // Remove '/api' from the request path
//         // }
//       }
//     }
//   }
// };
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8081', // Backend API base URL
        changeOrigin: true,
      },
    },
  },
};
