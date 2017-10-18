var express = require('express');
var app = express();
var opn = require('opn'); //打开网页
var port = 80;
app.use(express.static('./'));


app.get('/', function (req, res) {
    res.send('Hello World!'); 
});


app.get('/get', function (req, res) {
    console.log('GET request to the homepage')
    res.send('GET request to the homepage');
});

app.post('/post', function (req, res) {
    console.log('POST request to the homepage')
    res.send('POST request to the homepage');
});

app.get('/jsonp', function (req, res) {
    var callback = req.query.callback
    res.send(callback + '('+ '{"data" : "jsonp"}'+')');
});



var server = app.listen(port, function () {
    var host = server.address().address;
    var port = server.address().port;
    // opn('http:localhost:' + port + '/index.js');
    console.log('Example app listening at http://%s:%s', host, port);
});