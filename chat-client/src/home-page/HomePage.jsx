import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getChatRooms } from "../utils/Api";
import { getDecodedTokenFromCookie } from "../utils/Utils";
import Cookies from "js-cookie";
import ChatList from "./components/ChatList";
import ChatBox from "./components/ChatBox";
import CreateChatRoomComponent from "./components/CreateChatRoomComponent";
import "./HomePage.css";

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
        <CreateChatRoomComponent
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
