import React, { useState, useEffect } from "react";
import { createChatRooms, getUsernames } from "../../utils/Api";
import "./CreateChatRoomComponent.css";

const CreateChatRoomComponent = ({ username, onChatroomCreate }) => {
  const [chatroomName, setChatroomName] = useState("");
  const [users, setUsers] = useState([]);
  const [selectedUsernames, setSelectedUsernames] = useState([]);

  useEffect(() => {
    getUsernames().then((usernames) => {
      const otherUsers = usernames.filter((user) => user.username !== username);
      setUsers(otherUsers);
    });
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

export default CreateChatRoomComponent;
