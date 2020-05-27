import React, { useEffect } from "react";
import { connect } from "react-redux";
import * as actions from "../../actions";
import { AppState } from "../../configStore/configStore";
import { FullCommitInfo } from "../../types/FullCommitInfo";
import { UserCommitsWeek } from "../../types/UserCommitsWeek";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
type props = linkPropToState & LinkDispatchProps;

/* Much Needed Interface to hold Branches and their associated commits */
interface branchWithCommits {
  branch: string;
  data: FullCommitInfo[];
}

const ListCommits: React.FC<props> = ({
  fullCommitInfo,
  numRepoChange,
  fetchAllCommitsInRepo,
  resetFetchAllCommitsInRepo,
}) => {
  /*
    Init on render we check if the numRepoChanges is larger than 0; if so, we know that we are on a page 
    displaying a repo with its lifecycle changes and it will refetch all nessary information when the numRepoChanges change => new repo
    Also on init render we clear out the existing fetch data so data does not persists 
  */
  useEffect(() => {
    resetFetchAllCommitsInRepo();
    const reduceArr: number[] = [];
    if (numRepoChange.length > 0) {
      numRepoChange.forEach((el) => {
        if (reduceArr.filter((e) => e === el.repository_id).length === 0)
          reduceArr.push(el.repository_id);
      });
    }
    reduceArr.forEach((el) => fetchAllCommitsInRepo(el));
    return () => {
      /* Cleanup */
      resetFetchAllCommitsInRepo();
    };
  }, [numRepoChange]);
  /* We split all the fetch data by branch so we displat all commits within certin branch */
  const spitIntoBranchSegments = () => {
    const returnArr: branchWithCommits[] = [{ branch: "master", data: [] }];
    fullCommitInfo.forEach((el) => {
      /* If not in return array we add it to the return array */
      if (returnArr.filter((e) => e.branch === el.branch).length === 0)
        returnArr.push({ branch: el.branch, data: [el] });
      else
        returnArr.map((e) => {
          if (e.branch === el.branch) e.data.push(el);
        });
    });
    return returnArr;
  };
  const renderLinksOfCommits = (arr: FullCommitInfo[]) => {
    return arr.map((e) => {
      return (
        <a
          key={e.commit}
          href={`http://mapmg-uat.appl.kp.org:7990/projects/${e.project}/repos/${e.repo}/commits/${e.commit}`}
          className="flex flex-wrap hover:bg-blue-700 text-gray-500 mx-6 hover:underline cursor-pointer hover:text-white border-b w-5/12 py-2"
        >
          <div className="w-full text-xs">{e.date.split(" ")[0]}</div>
          <div className="flex-1 text-sm">{e.commit}</div>
          <div className=" text-xs">{e.author}</div>
        </a>
      );
    });
  };

  const renderCommits = () => {
    if (fullCommitInfo.length > 0) {
      const finalArr: branchWithCommits[] = spitIntoBranchSegments();
      return finalArr.map((element) => {
        /* Sort by date uploaded */
        element.data.sort((a, b) => {
          const dateOne = new Date(a.date);
          const dateTwo = new Date(b.date);
          return dateOne > dateTwo ? -1 : dateOne < dateTwo ? 1 : 0;
        });
        return (
          <div key={element.branch} className="w-11/12 mx-auto">
            <div className="text-2xl font-black py-3">
              {element.branch ? element.branch.toUpperCase() : ""}
            </div>
            <div className="flex flex-wrap">
              {renderLinksOfCommits(element.data)}
            </div>
          </div>
        );
      });
    }
  };
  /* Master Render */
  return <div>{renderCommits()}</div>;
};
interface LinkDispatchProps {
  fetchAllCommitsInRepo: (repoId: number) => void;
  resetFetchAllCommitsInRepo: () => void;
}
interface linkPropToState {
  fullCommitInfo: FullCommitInfo[];
  numRepoChange: UserCommitsWeek[];
}
const mapStateToProps = (state: AppState) => {
  return {
    fullCommitInfo: state.fullCommitReducer,
    numRepoChange: state.userCommitsInWeekReducer,
  };
};
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchAllCommitsInRepo: bindActionCreators(
    actions.fetchFullCommitInfo,
    dispatch
  ),
  resetFetchAllCommitsInRepo: bindActionCreators(
    actions.resetFetchFullCommitInfo,
    dispatch
  ),
});

export default connect(mapStateToProps, mapDispatchToProps)(ListCommits);
