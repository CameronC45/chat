import NavBar from "./NavBar";
import "./MainLayout.css";
import { Outlet } from "react-router-dom";

export const MainLayout = () => {
  return (
    <>
      <NavBar />
      <Outlet />
    </>
  );
};
