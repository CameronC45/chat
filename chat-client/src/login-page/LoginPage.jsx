import { useState } from 'react';
import './LoginPage.css';
import { isEmailValid } from '../utils/Utils';


const LoginForm = ({ changeForm }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const handleSubmit = async event => {
    event.preventDefault();
    const response = await fetch('http://localhost:8080/auth/login', {
          method: 'POST', // Specify the method
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            username: username,
            password: password
          })
        });
    
        if (!response.ok) { // Check if the request was successful
          window.alert('Sign up failed. Please try again.');
          return;
        }
    
        const jwtToken = await response.text(); // Get the JSON data from the response
        console.log(jwtToken);
        document.cookie = `token=${jwtToken}; Secure; SameSite=Strict`;
        location.reload();

      
  };

  return (
    <div className="form-container">
        <img src="/micro-chat.png" alt="Micro Chat" className="side-image" />
      <form onSubmit={handleSubmit}>
        <label>
          <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
        </label>
        <label>
          <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
        </label>
        <input type="submit" value="Log In" />
      </form>
      <p>Don't have an account? <a href="#" onClick={changeForm}>Sign Up</a></p>
    </div>
  );
};

const SignUpForm = ({ changeForm }) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    
    const handleSubmit = async event => {
        event.preventDefault();
    
        if (!isEmailValid(email)) {
          window.alert('Invalid email. Please try again.');
          return;
        }
    
        if (password !== confirmPassword) {
          window.alert('Passwords do not match. Please try again.');
          return;
        }
    
        const response = await fetch('http://localhost:8080/users', {
          method: 'POST', // Specify the method
          headers: {
            'Content-Type': 'application/json' // Specify the data type
          },
          body: JSON.stringify({ // Convert the JavaScript object to a JSON string
            email: email,
            username: username,
            password: password
          })
        });
    
        if (!response.ok) { // Check if the request was successful
          window.alert('Sign up failed. Please try again.');
          return;
        }
        changeForm();
      };
    
  
    return (
      <div className="form-container">
          <img src="/micro-chat.png" alt="Micro Chat" className="side-image" />
        <form onSubmit={handleSubmit}>
          <label>
            <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
          </label>
          <label>
            <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
          </label>
          <label>
            <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
          </label>
          <label>
            <input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} />
          </label>
          <input type="submit" value="Sign Up" />
        </form>
        <p>Already have an account? <a href="#" onClick={changeForm}>Log In</a></p>
      </div>
    );
  };
  
  

export function LoginPage() {
  const [isLogin, setIsLogin] = useState(true);
  
  return (
    <div>
      {isLogin ? 
        <LoginForm changeForm={() => setIsLogin(false)} /> :
        <SignUpForm changeForm={() => setIsLogin(true)} />
      }
    </div>
  );
}
