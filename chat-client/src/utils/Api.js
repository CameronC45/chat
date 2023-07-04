export const getChatRooms = async (username) => {
    try {
      const response = await fetch('http://localhost:8080/chat/user', {
          method: 'POST',
          credentials: 'include',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({
              "userId": `${username}`
          })
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
          method: 'GET',
          credentials: 'include',
          headers: {
            'Content-Type': 'application/json',
          },
        });
        const data = await response.json();
        return data.messages;
      } catch (error) {
        console.log("Failed to fetch messages: ", error);
      }
  }

  export const sendMessage = async (chatId, newMessage, username) => {
    try {
        const response = await fetch(`http://localhost:8080/messaging/${chatId}`, {
          method: 'POST',
          credentials: 'include',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ content: newMessage, recipientUsername: username ,senderId: username }),
        });
        return await response.json();
      } catch (error) {
        console.log("Failed to send message: ", error);
      }
  }