import React, { useState, useEffect } from "react";
import { NavLink } from "react-router-dom";
import UserList from "../list/UserList";
/*
  CHange the home route out of dev
*/
const TopBar: React.FC = () => {
  const [showUsers, setShowUsers] = useState(false);
  const [date, setDate] = useState("");
  useEffect(() => {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, "0");
    var mm = String(today.getMonth() + 1).padStart(2, "0");
    var yyyy = today.getFullYear();
    setDate(yyyy + mm + dd);
  }, []);
  const renderTopBar = () => {
    return (
      <div className="w-full bg-primarycolor text-white sticky  font-black">
        <div className="flex justify-between w-11/12 mx-auto">
          <NavLink to={`/stats/week/${date}`}>
            <div className="">WorkloggerPro</div>
          </NavLink>
          <div className="flex">
            <div
              onClick={() => setShowUsers(!showUsers)}
              className={`flex px-12 hover:underline cursor-pointer ${
                showUsers ? "bg-deepPrimary underline" : ""
              }`}
            >
              Users
            </div>
            <NavLink to="/discover/repos/">Repos</NavLink>
          </div>
        </div>
        {showUsers ? <UserList show={setShowUsers} /> : ""}
      </div>
    );
  };
  return <div>{renderTopBar()}</div>;
};

export default TopBar;
