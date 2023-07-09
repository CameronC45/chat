export const getChatRooms = async (username) => {
  try {
    const response = await fetch("http://localhost:8080/chat/user", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userId: `${username}`,
      }),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};

export const createChatRooms = async (chatName, participants) => {
  try {
    const response = await fetch("http://localhost:8080/chat", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: `${chatName}`,
        participants: participants.map((userId) => ({ userId })),
      }),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};

export const addParticipant = async (chatId, participant) => {
  try {
    const response = await fetch(
      `http://localhost:8080/participant/${chatId}`,
      {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: `${participant}`,
        }),
      }
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};

export const getUsernames = async () => {
  try {
    const response = await fetch(`http://localhost:8080/users`, {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};

export const getMessages = async (chatId) => {
  try {
    const response = await fetch(`http://localhost:8080/chat/${chatId}`, {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();
    return data.messages;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};

export const sendMessage = async (
  chatId,
  name,
  newMessage,
  username,
  recipients
) => {
  try {
    const response = await fetch(`http://localhost:8080/messaging/${chatId}`, {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        chat: name,
        content: newMessage,
        recipientUsername: recipients,
        senderId: username,
      }),
    });
    return await response.json();
  } catch (error) {
    console.log("Failed to send message: ", error);
  }
};

export const getNotifications = async (userId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/notification/recipients/${userId}`,
      {
        method: "GET",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("Failed to fetch messages: ", error);
  }
};
