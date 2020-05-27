import React, { useEffect } from "react";
import { connect } from "react-redux";
import { AllUsersPercentWeek } from "../../../types/AllUsersPercentWeek";
import { AppState } from "../../../configStore/configStore";
import { AppActions } from "../../../types/actions";
import { fetchAllUserPercent, fetchAllUsers } from "../../../actions";
import { ThunkDispatch } from "redux-thunk";
import { bindActionCreators } from "redux";
import { RouteComponentProps } from "react-router-dom";

interface urlWeek extends RouteComponentProps<{ week: string }> {}
// interface UrlWeek extends RouteComponentProps<weekUrl> {}
type props = LinkStateProps & LinkDispatchProps & urlWeek;
const UserPercent: React.FC<props> = ({
  fetchUserPercent,
  userPercents,
  match,
}) => {
  /*  
        On init of render We will fetch all User percents based on if the route is for a specific 
        week or not and set the fetched data to 
    */
  useEffect(() => {
    fetchUserPercent(match.params.week);
  }, []);
  return <div> Hl</div>;
};

interface LinkStateProps {
  userPercents: AllUsersPercentWeek;
}
interface LinkDispatchProps {
  fetchUserPercent: (week: string) => void;
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchUserPercent: bindActionCreators(fetchAllUserPercent, dispatch),
});
const mapStateToProps = (state: AppState) => {
  return {
    allUsersPercent: state.allUsersPercentReducer,
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserPercent);
