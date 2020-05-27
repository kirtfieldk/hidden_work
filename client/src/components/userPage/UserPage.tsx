import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Bar } from "react-chartjs-2";
import * as actions from "../../actions";
import WorkOnRepos from "./WorkOnRepos";
import LoadingPage from "../loadingPage/LoadingPage";
import ListCommits from "../list/ListCommits";
import NumCommitsInBranches from "../graphs/NumCommitsInBranch";
import Cal from "../cal/cal";
import { RouteComponentProps } from "react-router-dom";
import { AppState } from "../../configStore/configStore";
import { StatsUserInWeek } from "../../types/StatsUserInWeek";
import { User } from "../../types/User";
import { UserCommitsWeek } from "../../types/UserCommitsWeek";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";

/* grabbing the User param out of the URL */
interface urlUser extends RouteComponentProps<{ user: string; week: string }> {}
type props = urlUser & linkStateToProps & LinkDispatchProps;

const UserPage: React.FC<props> = ({
  match,
  fetchStatsForUser,
  statsUser,
  user,
  resetLifecycleRepoBranch,
  fetchUser,
  numRepoChange,
  fetchReposNumberChages,
}) => {
  /*
        Upon rendering the UI we load the user stats via the Http paramater USER
    */
  useEffect(() => {
    if (user.user_id === "") {
      fetchUser(match.params.user);
    }
    fetchStatsForUser(match.params.user, match.params.week);
    fetchReposNumberChages(match.params.user, match.params.week);
    return () => resetLifecycleRepoBranch();
  }, [match.params.user]);
  const fetchStats = () => {
    return {
      labels: ["Inserts", "Deletes", "Net Changes"],
      datasets: [
        {
          label: ["Amount"],
          backgroundColor: ["rgba(100,22,333,23)", "#18c651", "#c618c3"],
          borderColor: ["rgba(100,22,333,23)", "#18c651", "#c618c3"],
          data: [statsUser.insert, statsUser.delete, statsUser.net_change],
        },
      ],
    };
  };

  const rendermanylineGraphs = () => {
    const reduceArr: number[] = [];
    if (numRepoChange) {
      numRepoChange.map((el) => {
        if (reduceArr.filter((e) => e === el.repository_id).length === 0)
          reduceArr.push(el.repository_id);
      });
      return reduceArr.map((el) => {
        return <NumCommitsInBranches key={el} repoId={el} />;
      });
    }
  };
  /* Implement Grid for better positioning*/
  const renderUsers = () => {
    const stats = fetchStats();
    return (
      <div className="grid grid-cols-12  gap-2">
        <div className=" col-start-1 hover:shadow-lg mx-4 row-auto col-end-7">
          <div className="grid grid-cols-4">
            <Bar
              data={stats}
              options={{
                title: {
                  display: true,
                  text: `Stats For Week: ${statsUser.start} <-> ${statsUser.end}`,
                  fontSize: 20,
                },
                legend: { display: true, positoion: "right" },
              }}
            />
          </div>
          <div className=" col-start-1 row-auto">{rendermanylineGraphs()}</div>
        </div>
        <div className="col-start-7 row-auto mx-4 col-end-13">
          <WorkOnRepos user={match.params.user} week={match.params.week} />
        </div>
      </div>
    );
  };
  const renderHome = () => {
    if (statsUser)
      return (
        <div>
          <div className="text-2xl w-full bg-black text-white shadow text-center mb-4 font-black mt-6">
            {user ? `${match.params.user} / ${user.name}` : ""}
          </div>
          <Cal user={match.params.user} currentDate={match.params.week} />
          <div className="w-full">{renderUsers()}</div>
          <div className="border-b-2 mt-10 w-11/12 mx-auto"></div>
          <ListCommits />
        </div>
      );
    else return <LoadingPage />;
  };
  /*
    Render User Name and essential info
  */
  return <div className="w-11/12 shadow-lg mx-auto pb-20">{renderHome()}</div>;
};
interface linkStateToProps {
  statsUser: StatsUserInWeek;
  user: User;
  numRepoChange: UserCommitsWeek[];
}
interface LinkDispatchProps {
  fetchUser: (userId: string) => void;
  fetchStatsForUser: (userId: string, week: string) => void;
  resetLifecycleRepoBranch: () => void;
  fetchReposNumberChages: (author: string, week: string) => void;
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchUser: bindActionCreators(actions.fetchuser, dispatch),
  fetchStatsForUser: bindActionCreators(actions.fetchStatsForUser, dispatch),
  resetLifecycleRepoBranch: bindActionCreators(
    actions.resetFetchLifecycleRepoBranch,
    dispatch
  ),
  fetchReposNumberChages: bindActionCreators(
    actions.fetchCommitsByUserInWeek,
    dispatch
  ),
});

const mapStateToProps = (state: AppState) => {
  return {
    statsUser: state.statsUserWeekReducer,
    user: state.userReducer,
    numRepoChange: state.userCommitsInWeekReducer,
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserPage);
