import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell, faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import { usePopper } from 'react-popper';
import "./NavBar.css";

const NavBar = () => {
  const [showNotifications, setShowNotifications] = useState(false);
  const [referenceElement, setReferenceElement] = useState(null);
  const [popperElement, setPopperElement] = useState(null);
  const { styles, attributes } = usePopper(referenceElement, popperElement);

  const notifications = ['Notification 1', 'Notification 2', 'Notification 3']; // replace this with your actual notifications

  const handleLogout = () => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (confirmLogout) {
      document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'; 
      window.location.reload();
    }
  };

  return (
    <div className="navbar">
      <img className="logo" src="./micro-logo.png" alt="logo"></img>
      <div className="navbar-icons">
        <div ref={setReferenceElement}>
          <FontAwesomeIcon icon={faBell} onClick={() => setShowNotifications(!showNotifications)} />
        </div>
        <FontAwesomeIcon icon={faSignOutAlt} onClick={handleLogout} />

        {showNotifications && (
          <div ref={setPopperElement} style={styles.popper} {...attributes.popper} className="notifications-dropdown">
            {notifications.map((notification, index) => (
              <p key={index}>{notification}</p>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default NavBar;
