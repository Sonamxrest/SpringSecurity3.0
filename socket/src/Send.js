import axios from 'axios';
import React, { useState } from 'react';
import SockJsClient from 'react-stomp';

const SOCKET_URL = 'http://localhost:8082/ws-message';

const Send = () => {
    const [revieved, setRecieved] = useState([]);
    const [id, setId] = useState(1);
    const [send, setSend] = useState([]);
    // const sockJs = new SockJS('', null, {});
    const [message, setMessage] = useState('');

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onMessageReceived = (msg) => {
        setRecieved((e) => [...e, msg.message]);
        // setMessage('');
    }
    const sendMsg = () => {
        let mesg = setSend((e) => [...e, message]);
        setMessage('')
        axios.post('http://localhost:8082/web/send', { id: id, message: message, sender: true }, {}).then((res) => {

        }).catch((err) => {

        })
    }

    return (
        <div>
            <SockJsClient
                url={SOCKET_URL}
                topics={['/topic/recieve/' + id]}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={false}
            />
            <div class="menu">
                <div class="back"><i class="fa fa-chevron-left"></i> <img src="https://i.imgur.com/DY6gND0.png" draggable="false" /></div>
                <div class="name">Sender</div>
                <div class="last">18:09</div>
            </div>
            <ol class="chat">
                {
                    send.map(e => {
                        return (<li class="self">
                            <div class="avatar"><img src="https://i.imgur.com/DY6gND0.png" draggable="false" /></div>
                            <div class="msg">
                                <p>{e}</p>
                                <time>20:18</time>
                            </div>
                        </li>)
                    })
                }

                {revieved.map(e => {
                    return (
                        <li class="other">
                            <div class="avatar"><img src="https://i.imgur.com/HYcn9xO.png" draggable="false" /></div>
                            <div class="msg">
                                <p>{e}</p>
                                {/* <p><a href="https://codepen.io/Varo/pen/YPmwpQ" target="parent">Chat UI 2.0</a></p> */}
                                <time>18:09</time>
                            </div>
                        </li>
                    )
                })}
            </ol>
            <input class="textarea" onChange={e => setMessage(e.target.value)} value={message} type="text" placeholder="Type here!" />
            <div class="emojis">

                <button onClick={sendMsg} >Send</button>

            </div>
            <div>


            </div>
        </div>
    );
}

export default Send;