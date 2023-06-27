import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import { LoginPage } from './login-page/LoginPage';
import { HomePage } from './home-page/HomePage';
import RedirectHandler from './RedirectHandler';

function App() {
    return (
        <Router>
            <RedirectHandler />
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/home" element={<HomePage />} />
            </Routes>
        </Router>
    );
}

export default App;
