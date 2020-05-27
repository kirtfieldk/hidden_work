import React, { useEffect } from "react";
import { connect } from "react-redux";
import * as actions from "../../actions";
import LifecycleLineGraph from "../graphs/LifecycleLineGraph";
import NumCommitsInBranches from "../graphs/NumCommitsInBranch";
import ListAuthorWhoWorkedOnRepo from "../list/ListAuthorWhoWorkedOnRepo";
import { AppState } from "../../configStore/configStore";
import { RouteComponentProps } from "react-router-dom";
import { LifecycleRepo } from "../../types/LifeCycleRepo";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
type props = urlRepoId & linkStateToProps & LinkDispatchProps;
interface urlRepoId extends RouteComponentProps<{ repoId: string }> {}
const RepoPage: React.FC<props> = ({
  match,
  lifeRepoStats,
  fetchLifecycleRepoBranch,
  clearBranchesInRepo,
  resetLifecycleRepoBranch,
  fetchBranchesInRepo,
}) => {
  useEffect(() => {
    fetchLifecycleRepoBranch(parseInt(match.params.repoId));
    fetchBranchesInRepo(parseInt(match.params.repoId));
    return () => {
      /* upon unmounting clear out the branches redeucer and the lifecycle reducer*/
      resetLifecycleRepoBranch();
      clearBranchesInRepo();
    };
  }, []);
  const renderGraphs = () => {
    if (lifeRepoStats.length > 0) return <LifecycleLineGraph />;
  };
  return (
    <div className="flex flex-wrap my-5">
      <div className="flex-1 w-1/2 mx-1">{renderGraphs()}</div>
      <div className="flex-1 w-1/2 mx-1">
        <NumCommitsInBranches repoId={parseInt(match.params.repoId)} />
      </div>
      <div className="w-full">
        <ListAuthorWhoWorkedOnRepo />
      </div>
    </div>
  );
};
interface linkStateToProps {
  lifeRepoStats: LifecycleRepo[];
}
const mapStateToProps = (state: AppState) => {
  return { lifeRepoStats: state.lifecycleReducer };
};
interface LinkDispatchProps {
  fetchLifecycleRepoBranch: (repoId: number) => void;
  fetchBranchesInRepo: (repoId: number) => void;
  resetLifecycleRepoBranch: () => void;
  clearBranchesInRepo: () => void;
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchLifecycleRepoBranch: bindActionCreators(
    actions.fetchLifecycleRepoBranch,
    dispatch
  ),
  resetLifecycleRepoBranch: bindActionCreators(
    actions.resetFetchLifecycleRepoBranch,
    dispatch
  ),
  clearBranchesInRepo: bindActionCreators(
    actions.resetBranchesInRepo,
    dispatch
  ),
  fetchBranchesInRepo: bindActionCreators(
    actions.fetchBranchesInRepo,
    dispatch
  ),
});

export default connect(mapStateToProps, mapDispatchToProps)(RepoPage);
