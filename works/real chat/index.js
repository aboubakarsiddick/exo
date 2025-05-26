const express = require('express');
const http = require('http');
const WebSocket = require('ws');
const path = require('path');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });


app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});
wss.on('connection' , (ws) => {
    console.log('Client connected');
    ws.send("hello")

    ws.on('message' , (message) => {
        console.log(`Received: ${message}`)
        const msg = message.toString()
        ws.send(msg)
    })
}) 

const port = 1000
server.listen(port, () => {
    console.log(` server ecouter sur le port ${port}` );
    
})