import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import { LoginPage } from "./login-page/LoginPage";
import { HomePage } from "./home-page/HomePage";
import RedirectHandler from "./RedirectHandler";
import { MainLayout } from "./MainLayout";

function App() {
  return (
    <Router>
      <RedirectHandler />
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/home" element={<MainLayout />}>
          <Route index element={<HomePage />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
