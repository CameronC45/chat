// RedirectHandler.jsx

import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getDecodedTokenFromCookie } from "./utils/Utils";

function RedirectHandler() {
  const navigate = useNavigate();

  useEffect(() => {
    const decodedToken = getDecodedTokenFromCookie("token");
    if (decodedToken && decodedToken.iss === "chat-service") {
      navigate("/home");
    } else {
      navigate("/login");
    }
  }, []);

  return null;
}

export default RedirectHandler;
