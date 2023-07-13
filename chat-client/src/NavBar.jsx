import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell, faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import { usePopper } from "react-popper";
import { getNotifications } from "./utils/Api";
import { getDecodedTokenFromCookie } from "./utils/Utils";
import "./NavBar.css";

const NavBar = () => {
  const [showNotifications, setShowNotifications] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const [referenceElement, setReferenceElement] = useState(null);
  const [popperElement, setPopperElement] = useState(null);
  const { styles, attributes } = usePopper(referenceElement, popperElement);

  useEffect(() => {
    const fetchNotifications = () => {
      const token = getDecodedTokenFromCookie("token");
      const username = token ? token.username : null;
      if (username) {
        getNotifications(username)
          .then((notifications) => {
            setNotifications(notifications);
          })
          .catch((error) => {
            console.error("Failed to fetch notifications:", error);
          });
      }
    };

    fetchNotifications();

    const intervalId = setInterval(fetchNotifications, 5000);    
    return () => clearInterval(intervalId);
  }, []);

  const handleLogout = () => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (confirmLogout) {
      document.cookie =
        "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      window.location.reload();
    }
  };

  const bellColor = notifications.length > 0 ? "#eb647c" : "inherit";

  return (
    <div className="navbar">
      <img className="logo" src="./micro-logo.png" alt="logo"></img>
      <div className="navbar-icons">
        <div ref={setReferenceElement}>
          <FontAwesomeIcon
            icon={faBell}
            onClick={() => setShowNotifications(!showNotifications)}
            style={{ color: bellColor }}
          />
        </div>
        <FontAwesomeIcon icon={faSignOutAlt} onClick={handleLogout} />

        {showNotifications && (
          <div
            ref={setPopperElement}
            style={styles.popper}
            {...attributes.popper}
            className="notifications-dropdown"
          >
            {notifications.length > 0 ? (
              notifications.map((notification, index) => (
                <div key={index} className="notification-item">
                  <b className="notification-chat">{notification.chat}</b>
                  <div className="notification-message">
                    New message from {notification.senderId}
                  </div>
                </div>
              ))
            ) : (
              <p className="notification-empty">No notifications</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default NavBar;
