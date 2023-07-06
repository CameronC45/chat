import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell, faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import "./NavBar.css";

const NavBar = () => {
  const handleLogout = () => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (confirmLogout) {
      // delete cookie
      document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'; 
      // refresh the page
      window.location.reload();
    }
  };

  return (
    <div className="navbar">
      <img className="logo" src="./micro-logo.png" alt="logo"></img>
      <div className="navbar-icons">
        <FontAwesomeIcon icon={faBell} />
        <FontAwesomeIcon icon={faSignOutAlt} onClick={handleLogout} />
      </div>
    </div>
  );
}

export default NavBar;
