var path = require("path");

module.exports = {
    entry: {
        index: "./src/index.js"
    },
    output: {
        path: "./build/",
        publicPath: "./build/",
        filename: "[name].js"
    },
    module: {
        loaders: [
            {test: /\.css$/, loader: "style!css"},
            {
                test: /.jsx?$/,
                loaders: ['babel?presets[]=es2015'],
                exclude: "/node_modules/",
                include: path.resolve(__dirname, "src")
            },
            // {test: /\.scss$/, loader: "style!css!sass"},
            {test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'}
        ]
    },
    devtool: false,
    resolve: {
        extensions: ['', '.js', '.scss', ".css", 'jsx']  //自动补全识别后缀
    }
}