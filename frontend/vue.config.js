module.exports = {
	"devServer": {
		"proxy": "http://localhost:9902/"
	},
	"runtimeCompiler": true,
	"publicPath": process.env.NODE_ENV === 'production' ? '/responsabilidade-tecnica' : '/',
	"outputDir": "../backend/src/main/resources/static",
	"transpileDependencies": [
		"vuetify"
	]
};