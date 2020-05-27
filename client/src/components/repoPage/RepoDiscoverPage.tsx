import React, { useEffect } from "react";
import { connect } from "react-redux";
import { NavLink } from "react-router-dom";
import * as actions from "../../actions";
import { AppState } from "../../configStore/configStore";
import { ReposWorkedOn } from "../../types/ReposWorkedOn";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
type props = linkStateToProps & LinkDispatchProps;
const RepoDiscoverPage: React.FC<props> = ({ allRepoName, fetchAllRepos }) => {
  useEffect(() => {
    fetchAllRepos();
  }, []);
  console.log(allRepoName);
  /* Replace all _ with spaces so they will make new lines if line is too big*/
  const renderRepos = () => {
    if (allRepoName.length > 0) {
      return allRepoName.sort().map((el) => {
        return (
          <div className="bg-primarycolor border-rounded mx-2 my-5 text-white hover:shadow-2xl border-2  w-1/4">
            <NavLink to={`/repo/${el.repository_id}/`}>
              {el.repository.split("_").join(" ").toUpperCase()}
            </NavLink>
          </div>
        );
      });
    }
  };
  return <div className="flex flex-wrap mx-10">{renderRepos()}</div>;
};
interface linkStateToProps {
  allRepoName: ReposWorkedOn[];
}
const mapStateToProps = (state: AppState) => {
  return { allRepoName: state.allReposReducer };
};
interface LinkDispatchProps {
  fetchAllRepos: () => void;
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchAllRepos: bindActionCreators(actions.fetchAllrepos, dispatch),
});
export default connect(mapStateToProps, mapDispatchToProps)(RepoDiscoverPage);
