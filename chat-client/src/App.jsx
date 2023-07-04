import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import { LoginPage } from './login-page/LoginPage';
import { HomePage } from './home-page/HomePage';
import RedirectHandler from './RedirectHandler';
import { MainLayout } from './MainLayout';

function App() {
    return (
        <div className="app-container">
            <Router>
                <RedirectHandler />
                <Routes>
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/home" element={<MainLayout />}>
                        <Route index element={<HomePage />} />
                        {/* Add more routes here where you want the NavBar to be present */}
                    </Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
