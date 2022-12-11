import axios from 'axios';
import React, { useState } from 'react';
import SockJsClient from 'react-stomp';

const SOCKET_URL = 'http://localhost:8082/ws-message';

const App = () => {
  const [revieved, setRecieved] = useState('You server message here.');

  const [message, setMessage] = useState('');

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    console.log('message', msg)
    setRecieved(msg.message);
  }
  const send = () => {
    axios.post('http://localhost:8082/web/send', { message: message }, {}).then((res) => {

    }).catch((err) => {

    })
  }

  return (
    <div>
      <SockJsClient
        url={SOCKET_URL}
        topics={['/topic/message', '/topic/hello']}
        onConnect={onConnected}
        onDisconnect={console.log("Disconnected!")}
        onMessage={msg => onMessageReceived(msg)}
        debug={false}
      />
      <div>{revieved}</div>

      <input type="text" onChange={e => setMessage(e.target.value)} value={message} />
      <button onClick={send} >Send</button>
    </div>
  );
}

export default App;