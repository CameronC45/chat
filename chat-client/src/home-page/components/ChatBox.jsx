import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom";
import {
  getMessages,
  sendMessage,
  addParticipant,
  getUsernames,
} from "../../utils/Api";
import { MdSend } from "react-icons/md";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUsers, faUserPlus } from "@fortawesome/free-solid-svg-icons";
import "./ChatBox.css";

const Modal = ({ children, isOpen }) => {
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
    onAddParticipant({username: selectedUsername});  // Pass an object instead of a string.
    setSelectedUsername("");
    setIsModalOpen(false);
  };

  // Get the list of participants' usernames.
  const participantUsernames = participants.map(participant => participant.userId);

  // Filter out usernames that are already in participants.
  const nonParticipantUsernames = usernames.filter(username => !participantUsernames.includes(username.username));

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
        <FontAwesomeIcon icon={faUserPlus} />
      </button>
      <Modal isOpen={isModalOpen} closeModal={() => setIsModalOpen(false)}>
        <div className="modal">
          <h2>Select a User</h2>
          <ul className="usernames-list">
            {nonParticipantUsernames.map((username) => (
              <li
                key={username.username}
                className={`username-item ${
                  username.username === selectedUsername ? "selected" : ""
                }`}
                onClick={() => setSelectedUsername(username.username)}
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
    const recipients = chatroom.participants
      .filter((participant) => participant.userId !== username)
      .map((participant) => participant.userId);

    sendMessage(
      chatroom.roomId,
      chatroom.name,
      newMessage,
      username,
      recipients
    ).then((newMessage) => {
      setMessages([...messages, newMessage]);
      setNewMessage("");
    });
  };

  const handleAddParticipant = (newParticipant) => {
    addParticipant(chatroom.roomId, newParticipant.username).then( 
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
            <div
              className={`message ${
                message.senderId === username ? "own" : "other"
              }`}
            >
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
        <button className="participants-button" onClick={toggleParticipants}>
          <FontAwesomeIcon className="participants-icon" icon={faUsers} />
        </button>
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
