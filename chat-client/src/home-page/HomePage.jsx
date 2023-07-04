import React, { useState, useEffect } from 'react';
import { getChatRooms, getMessages, sendMessage } from '../utils/Api';
import { getDecodedTokenFromCookie } from '../utils/Utils';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { MdChat, MdSend, MdAddCircleOutline } from "react-icons/md"; // <-- Import an icon for selected chat
import './HomePage.css';

const ChatList = ({ chatrooms, selectedChatroom, onChatroomSelect, onCreateChatroom }) => (
  <div className="chatlist">
    {chatrooms.map((chatroom) => (
      <div 
        key={chatroom.roomId} 
        className={`chatroom-item ${selectedChatroom && selectedChatroom.roomId === chatroom.roomId ? 'selected' : ''}`} 
        onClick={() => {
          onChatroomSelect(chatroom);
        }}
      >
        {selectedChatroom && selectedChatroom.roomId === chatroom.roomId && <MdChat />}
        {chatroom.name}
      </div>
    ))}
    <button className="add-chatroom-button" onClick={onCreateChatroom}>
      <MdAddCircleOutline />
    </button>
  </div>
);

const ChatBox = ({ chatroom, username }) => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  useEffect(() => {
    if (chatroom) {
      getMessages(chatroom.roomId).then(setMessages);  
    }
  }, [chatroom]);

  const handleSendMessage = () => {
    sendMessage(chatroom.roomId, newMessage, username).then((newMessage) => {
      setMessages([...messages, newMessage]);
      setNewMessage('');
    });
  };

  return (
    <div className="chatbox">
      <div className="messages-container">
        {messages.map((message, index) => (
          <div 
            key={index}
            className={`message ${message.senderId === username ? 'own' : 'other'}`}
          >
            <span className="message-content">{message.senderId}:{message.content}</span>
          </div>
        ))}
      </div>
      <div className="input-container">
        <div className="input-send-container">
          <input
            className="chat-input"
            value={newMessage}
            onChange={(e) => setNewMessage(e.target.value)}
            placeholder="Type your message"
          />
          <MdSend className="send-icon" onClick={handleSendMessage} />
        </div>
      </div>
    </div>
  );
};

const CreateChatroomComponent = () => (
  <div className="create-chatroom">
    <h1>Create Chatroom</h1>
  </div>
);

export const HomePage = () => {
  const navigate = useNavigate();
  const [chatrooms, setChatrooms] = useState([]);
  const [selectedChatroom, setSelectedChatroom] = useState(null);
  const [username, setUsername] = useState();
  const [creatingChatroom, setCreatingChatroom] = useState(false);

  const handleChatroomSelect = (chatroom) => {
    setSelectedChatroom(chatroom);
    if (creatingChatroom) setCreatingChatroom(false);
  };

  const handleCreateChatroom = () => {
    setSelectedChatroom(null);
    setCreatingChatroom(true);
  };

  useEffect(() => {
    const token = getDecodedTokenFromCookie('token');
    if (!(token && token.iss === 'chat-service')) {
      Cookies.remove('token');
      navigate('/login');
    } else if (token.exp * 1000 < Date.now()) {
      Cookies.remove('token');
      navigate('/login');
    } else {
      getChatRooms(token.username).then((rooms) => {
        console.log(rooms)
        setChatrooms(rooms);
        setSelectedChatroom(rooms[0]); // set the first chatroom as the selected one
      });
      setUsername(token.username);
    }
  }, []);

  return (
    <div className="homepage">
      <ChatList chatrooms={chatrooms} selectedChatroom={selectedChatroom} onChatroomSelect={handleChatroomSelect} onCreateChatroom={handleCreateChatroom} />
      {creatingChatroom ? <CreateChatroomComponent /> : selectedChatroom ? <ChatBox chatroom={selectedChatroom} username={username}/> : null}
    </div>
  );
};

export default HomePage;
