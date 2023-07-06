import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom";
import {
  getMessages,
  sendMessage,
  addParticipant,
  getUsernames,
} from "../../utils/Api";
import { MdSend, MdAddCircleOutline } from "react-icons/md";
import "./ChatBox.css";

const Modal = ({ children, isOpen}) => {
  if (!isOpen) return null;

  return ReactDOM.createPortal(
    <div className="modal-overlay">{children}</div>,
    document.body
  );
};

const ParticipantsList = ({ participants, onAddParticipant }) => {
  const [usernames, setUsernames] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedUsername, setSelectedUsername] = useState("");

  useEffect(() => {
    getUsernames().then(setUsernames);
  }, []);

  const handleAddParticipant = () => {
    onAddParticipant(selectedUsername);
    setSelectedUsername("");
    setIsModalOpen(false);
  };

  return (
    <div className="participants-list">
      {participants.map((participant) => (
        <div key={participant.participantId} className="participant-name">
          {participant.userId}
        </div>
      ))}
      <button
        className="add-participants-button"
        onClick={() => setIsModalOpen(true)}
      >
        <MdAddCircleOutline />
      </button>
      <Modal isOpen={isModalOpen} closeModal={() => setIsModalOpen(false)}>
        <div className="modal">
          <h2>Select a User</h2>
          <ul className="usernames-list">
            {usernames.map((username) => (
              <li
                key={username.username}
                className={`username-item ${
                  username === selectedUsername ? "selected" : ""
                }`}
                onClick={() => setSelectedUsername(username)}
              >
                {username.username}
              </li>
            ))}
          </ul>
          <button onClick={handleAddParticipant}>Add</button>
          <button onClick={() => setIsModalOpen(false)}>Cancel</button>
        </div>
      </Modal>
    </div>
  );
};

const ChatBox = ({ chatroom, username }) => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const [showParticipants, setShowParticipants] = useState(false);

  useEffect(() => {
    if (chatroom) {
      getMessages(chatroom.roomId).then(setMessages);
    }
  }, [chatroom]);

  const handleSendMessage = () => {
    sendMessage(chatroom.roomId, newMessage, username).then((newMessage) => {
      setMessages([...messages, newMessage]);
      setNewMessage("");
    });
  };

  const handleAddParticipant = (newParticipantUsername) => {
    addParticipant(chatroom.roomId, newParticipantUsername.username).then(
      (participant) => {
        chatroom.participants.push(participant);
        setShowParticipants(false);
        setSelectedChatroom({ ...chatroom });
      }
    );
  };

  const toggleParticipants = () => {
    setShowParticipants((prevShowParticipants) => !prevShowParticipants);
  };

  return (
    <div className="chatbox">
      <div className="messages-container">
        {messages.map((message, index) => (
          <div
            key={index}
            className={`message-wrapper ${
              message.senderId === username ? "own" : "other"
            }`}
          >
            {message.senderId !== username && (
              <div className="message-sender">{message.senderId}</div>
            )}
            <div className={`message ${message.senderId === username ? "own" : "other"}`}>
              <div className="message-content">{message.content}</div>
            </div>
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
        <button onClick={toggleParticipants}>Participants</button>
      </div>
      {showParticipants && (
        <ParticipantsList
          participants={chatroom.participants}
          onClose={toggleParticipants}
          onAddParticipant={handleAddParticipant}
        />
      )}
    </div>
  );
};

export default ChatBox;


