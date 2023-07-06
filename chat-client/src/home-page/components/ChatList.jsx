import React from "react";
import { MdChat, MdAddCircleOutline } from "react-icons/md";
import "./ChatList.css";

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

export default ChatList;
