const path = require("path");
module.exports = {
	lintOnSave: false,
	outputDir: "../src/main/resources/vue-static",
	indexPath: "../vue-static/index.html",
	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:9989',
				ws: true,
				changeOrigin: true
			},'/static': {
				target: 'http://localhost:9989',
				changeOrigin: true,
				ws: true
			}
		},
		overlay: false
	},
}