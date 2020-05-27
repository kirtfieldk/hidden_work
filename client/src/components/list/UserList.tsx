import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as actions from "../../actions";
import { NavLink } from "react-router-dom";
import { AppState } from "../../configStore/configStore";
import { User } from "../../types/User";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
/*
    Render the list of users on topbar when a user is clicked the app pushes the user 
    tao the /stats/user/{user}/ page
    On init render we fetch all users from server 
*/
type props = LinkStateToProps & LinkDispatchProps & passedFromParent;
interface passedFromParent {
  show: (val: boolean) => void;
}
const UserList: React.FC<props> = ({ allUsers, show, fetchUsers }) => {
  const [date, setDate] = useState("");
  useEffect(() => {
    fetchUsers();
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, "0");
    var mm = String(today.getMonth() + 1).padStart(2, "0");
    var yyyy = today.getFullYear();
    setDate(yyyy + mm + dd);
  }, []);
  const renderList = () => {
    return allUsers.map((el) => {
      return (
        <div
          key={el.name}
          className="px-2 py-2 border-2 border-double hover:bg-black cursor-pointer my-2 mx-2 border-white "
        >
          <NavLink
            onClick={() => {
              show(false);
            }}
            to={`/users/${el.user_id}/week/${date}`}
          >
            {el.name}
          </NavLink>
        </div>
      );
    });
  };
  return (
    <div className="absolute bg-deepPrimary shadow-xl flex flex-wrap">
      {renderList()}
    </div>
  );
};
interface LinkDispatchProps {
  fetchUsers: () => void;
}
interface LinkStateToProps {
  allUsers: User[];
}
const mapStateToProps = (state: AppState) => {
  return { allUsers: state.allUsersReducer };
};
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchUsers: bindActionCreators(actions.fetchAllUsers, dispatch),
});

export default connect(mapStateToProps, mapDispatchToProps)(UserList);
