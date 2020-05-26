import React from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
import { AppState } from "../configStore/configStore";
import { fetchAllUsers } from "../actions";
import { bindActionCreators } from "redux";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../types/actions";
import { User } from "../types/User";

type Props = LinkStateProps & LinkDispatchProps;
const Homepage: React.FC<Props> = ({ users, fetchAllUsersH }) => {
  useEffect(() => {
    fetchAllUsersH();
  }, []);
  const renderInfo = () => {
    return users.map((el) => {
      console.log(el);
      return (
        <div key={el.Name}>
          {el.Name}: {el.User_id}
        </div>
      );
    });
  };
  return <div> {renderInfo()}</div>;
};
interface LinkStateProps {
  users: User[];
}
interface LinkDispatchProps {
  fetchAllUsersH: () => void;
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchAllUsersH: bindActionCreators(fetchAllUsers, dispatch),
});

const mapStateToProps = (state: AppState) => {
  return {
    users: state.allUsersReducer,
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Homepage);
