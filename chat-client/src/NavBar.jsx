import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell, faEllipsisV } from "@fortawesome/free-solid-svg-icons";
import "./NavBar.css";

const NavBar = () => (
  <div className="navbar">
    <img className="logo" src="./micro-logo.png" alt="logo"></img>
    <div className="navbar-icons">
      <FontAwesomeIcon icon={faBell} />
      <FontAwesomeIcon icon={faEllipsisV} />
    </div>
  </div>
);

export default NavBar;
