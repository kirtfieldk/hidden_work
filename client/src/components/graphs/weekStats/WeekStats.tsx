import React, { useEffect } from "react";
import { connect } from "react-redux";
import WeekStatGraph from "./WeekStatGraph";
import UserPercents from "./UserPercents";
import ListWorkedRepos from "../../list/ListWorkedRepos";
import LoadingPage from "../../loadingPage/LoadingPage";
import Cal from "../../cal/cal";
import { AppState } from "../../../configStore/configStore";
import { StatsUserInWeek } from "../../../types/StatsUserInWeek";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../../types/actions";
import { bindActionCreators } from "redux";
import { fetchStatsWeek } from "../../../actions";
import { RouteComponentProps } from "react-router-dom";
/*
    Homepage for the weekstats page -> while fetching the stats for the week (ins/del) and percentage commits 
*/
interface urlWeek extends RouteComponentProps<{ week: string }> {}
type props = linkStateToProps & linkDispatchToProps & urlWeek;
const WeekStats: React.FC<props> = ({ match, weekStats, fetchStatsWeek }) => {
  useEffect(() => {
    fetchStatsWeek(match.params.week);
  }, [match.params.week]);
  const renderPage = () => {
    if (!weekStats) return <LoadingPage />;
    else
      return (
        <div>
          <div className="lg:w-11/12 md:w-11/12 w-full mx-auto shadow-lg">
            <div className="mt-5 border-b mx-5 text-2xl border-black font-black">
              {match.params.week}
            </div>
            <Cal currentDate={match.params.week} />
            <div className="flex flex-wrap">
              <WeekStatGraph className="flex-1" match={match.params.week} />
              <UserPercents className="flex-1" match={match.params.week} />
            </div>
          </div>
          <ListWorkedRepos week={match.params.week} />
        </div>
      );
  };

  return <div>{renderPage()}</div>;
};
interface linkStateToProps {
  weekStats: StatsUserInWeek;
}
interface linkDispatchToProps {
  fetchStatsWeek: (week: string) => void;
}
const mapStateToProps = (state: AppState) => {
  return { weekStats: state.statsUserWeekReducer };
};
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): linkDispatchToProps => ({
  fetchStatsWeek: bindActionCreators(fetchStatsWeek, dispatch),
});
export default connect(mapStateToProps, mapDispatchToProps)(WeekStats);
