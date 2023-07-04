import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchMessages = createAsyncThunk(
  'messages/fetchMessages',
  async (chatId, { getState }) => {
    try {
      const response = await fetch(`http://localhost:8080/chat/${chatId}`, {
        method: 'GET',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      const data = await response.json();
      return data.data;
    } catch (error) {
      console.log("Failed to fetch messages: ", error);
    }
  }
);

export const sendMessage = createAsyncThunk(
  'messages/sendMessage',
  async ({ chatId, newMessage, username }, { dispatch }) => {
    try {
      const response = await fetch(`http://localhost:8080/messaging/${chatId}`, {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ content: newMessage, recipientUsername: username ,senderId: username }),
      });
      const data = await response.json();
      if (data.success) {
        // dispatch fetchMessages again after a message is successfully sent
        dispatch(fetchMessages(chatId));
      }
    } catch (error) {
      console.log("Failed to send message: ", error);
    }
  }
);

const messagesSlice = createSlice({
  name: 'messages',
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchMessages.fulfilled, (state, action) => {
      return action.payload;
    });
  },
});

export default messagesSlice.reducer;
