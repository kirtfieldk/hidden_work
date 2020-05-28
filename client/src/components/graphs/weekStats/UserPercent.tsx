import React, { useEffect } from "react";
import { connect } from "react-redux";
import { AllUsersPercentWeek } from "../../../types/AllUsersPercentWeek";
import { AppState } from "../../../configStore/configStore";
import { AppActions } from "../../../types/actions";
import { Doughnut } from "react-chartjs-2";
import { fetchAllUserPercent, fetchAllUsers } from "../../../actions";
import { ThunkDispatch } from "redux-thunk";
import { bindActionCreators } from "redux";
import { RouteComponentProps } from "react-router-dom";

interface passedFromParent {
  week: string;
}
// interface UrlWeek extends RouteComponentProps<weekUrl> {}
type props = LinkStateProps & LinkDispatchProps & passedFromParent;
const UserPercent: React.FC<props> = ({
  fetchUserPercent,
  userPercents,
  week,
}) => {
  /*  
        On init of render We will fetch all User percents based on if the route is for a specific 
        week or not and set the fetched data to 
    */
  /*
        On init Render we will have to fetch all userPercents based on 
        if the route is for a specific week or not and set the fetched
        data to oue sataSets State
  */
  useEffect(() => {
    if (week) fetchUserPercent(week);
  }, [week]);
  /*
    Upon recieveing the allUserPercent Reducer we set the Labels of the pie graph
    to the users and the data to the percentCommits
    and generate random colors
*/
  const pieGraphValues = () => {
    if (userPercents.length > 0) {
      return {
        labels: userPercents.map((el) => el.user),
        datasets: [
          {
            label: "Values",
            backgroundColor: userPercents.map(
              (el) => `#${Math.floor(Math.random() * 16777215).toString(16)}`
            ),
            data: userPercents.map((el) => el.percent),
          },
        ],
      };
    }
    return [];
  };
  /*
    Rendering the pie graph
*/
  const renderPieGraph = () => {
    const state = pieGraphValues();
    return state !== [] ? (
      <Doughnut
        data={state}
        options={{
          title: {
            display: true,
            text: "Commits",
            fontColor: "black",
            fontSize: 20,
          },
          legend: {
            display: true,
            fontColor: "black",
            position: "right",
          },
        }}
      />
    ) : (
      ""
    );
  };
  return (
    <div className="w-full md:w-1/2 lg:w-1/2 py-10">{renderPieGraph()}</div>
  );

  return <div> Hl</div>;
};

interface LinkStateProps {
  userPercents: AllUsersPercentWeek[];
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
    userPercents: state.allUsersPercentReducer,
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserPercent);
