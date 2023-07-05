import React, { useState, useEffect } from "react";
import {
  getChatRooms,
  getMessages,
  sendMessage,
  createChatRooms,
  addParticipant,
  getUsernames,
} from "../utils/Api";
import { getDecodedTokenFromCookie } from "../utils/Utils";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import { MdChat, MdSend, MdAddCircleOutline } from "react-icons/md"; // <-- Import an icon for selected chat
import "./HomePage.css";

const ChatList = ({
  chatrooms,
  selectedChatroom,
  onChatroomSelect,
  onCreateChatroom,
}) => (
  <div className="chatlist">
    {chatrooms.map((chatroom) => (
      <div
        key={chatroom.roomId}
        className={`chatroom-item ${
          selectedChatroom && selectedChatroom.roomId === chatroom.roomId
            ? "selected"
            : ""
        }`}
        onClick={() => {
          onChatroomSelect(chatroom);
        }}
      >
        {selectedChatroom && selectedChatroom.roomId === chatroom.roomId && (
          <MdChat />
        )}
        {chatroom.name}
      </div>
    ))}
    <button className="add-chatroom-button" onClick={onCreateChatroom}>
      <MdAddCircleOutline />
    </button>
  </div>
);

const ParticipantsList = ({ participants, onClose, onAddParticipant }) => {
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
      <b>Members:</b>
      {participants.map((participant) => (
        <div key={participant.participantId}>{participant.userId}</div>
      ))}
      <button
        className="add-participants-button"
        onClick={() => setIsModalOpen(true)}
      >
        <MdAddCircleOutline />
      </button>
      <button onClick={onClose}>Close</button>

      {isModalOpen && (
        <div className="modal">
          <h2>Select a User</h2>
          {usernames.map((username) => {
            console.log(username)
            return (
              <div
                key={username.username} // using a unique property of the object
                className={username === selectedUsername ? "selected" : ""}
                onClick={() => setSelectedUsername(username)}
              >
                {username.username}
              </div>
            );
          })}
          <button onClick={handleAddParticipant}>Add Participant</button>
          <button onClick={() => setIsModalOpen(false)}>Cancel</button>
        </div>
      )}
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
        // We need to create a new object for the chatroom to trigger a re-render
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
            className={`message ${
              message.senderId === username ? "own" : "other"
            }`}
          >
            <span className="message-content">
              {message.senderId}:{message.content}
            </span>
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

const CreateChatroomComponent = ({ username, onChatroomCreate }) => {
  const [chatroomName, setChatroomName] = useState("");
  const [users, setUsers] = useState([]);
  const [selectedUsernames, setSelectedUsernames] = useState([]);

  useEffect(() => {
    getUsernames().then(setUsers);
  }, []);

  const handleChatroomCreate = () => {
    createChatRooms(chatroomName, [username, ...selectedUsernames]).then(() => {
      // Reset the form after creating the chatroom
      setChatroomName("");
      setSelectedUsernames([]);
      // Call the callback function after chatroom creation
      if (typeof onChatroomCreate === "function") {
        onChatroomCreate();
      }
    });
  };

  const handleUsernameToggle = (username) => {
    setSelectedUsernames((prevSelectedUsernames) => {
      if (prevSelectedUsernames.includes(username)) {
        // Remove the username from the selected list
        return prevSelectedUsernames.filter((u) => u !== username);
      } else {
        // Add the username to the selected list
        return [...prevSelectedUsernames, username];
      }
    });
  };

  return (
    <div className="create-chatroom">
      <h1>Create Chatroom</h1>
      <input
        value={chatroomName}
        onChange={(e) => setChatroomName(e.target.value)}
        placeholder="Chatroom Name"
      />
      <h2>Select Participants</h2>
      <ul className="usernames-list">
        {users.map((user) => (
          <li
            key={user.username}
            className={`username-item ${
              selectedUsernames.includes(user.username) ? "selected" : ""
            }`}
            onClick={() => handleUsernameToggle(user.username)}
          >
            {user.username}
          </li>
        ))}
      </ul>
      <button onClick={handleChatroomCreate}>Create Chatroom</button>
    </div>
  );
};

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
    const token = getDecodedTokenFromCookie("token");
    if (!(token && token.iss === "chat-service")) {
      Cookies.remove("token");
      navigate("/login");
    } else if (token.exp * 1000 < Date.now()) {
      Cookies.remove("token");
      navigate("/login");
    } else {
      getChatRooms(token.username).then((rooms) => {
        console.log(rooms);
        setChatrooms(rooms);
        setSelectedChatroom(rooms[0]); // set the first chatroom as the selected one
      });
      setUsername(token.username);
    }
  }, []);

  return (
    <div className="homepage">
      <ChatList
        chatrooms={chatrooms}
        selectedChatroom={selectedChatroom}
        onChatroomSelect={handleChatroomSelect}
        onCreateChatroom={handleCreateChatroom}
      />
      {creatingChatroom ? (
        <CreateChatroomComponent
          username={username}
          onChatroomCreate={() => {
            getChatRooms(username).then((rooms) => {
              setChatrooms(rooms);
              setSelectedChatroom(rooms[0]); // set the first chatroom as the selected one
            });
          }}
        />
      ) : selectedChatroom ? (
        <ChatBox chatroom={selectedChatroom} username={username} />
      ) : null}
    </div>
  );
};

export default HomePage;
