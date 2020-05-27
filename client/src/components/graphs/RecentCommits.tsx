import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import axios from "axios";
import * as actions from "../../actions";
import * as type from "../../config/configVariables";
type props = null;
const RecentCommits: React.FC<props> = ({
  fetchRecentCommits,
  recentCommits,
}) => {
  const [branches, setBranches] = useState([]);
  const [init, setInit] = useState(true);
  useEffect(() => {
    if (fetchRecentCommits.length === 0) fetchRecentCommits();
    renderLifecycleGraphs();
  }, [init, branches]);
  /*
    Returns an array of objects with repoId and [branches] 
*/
  const fetchBranches = () => {
    return recentCommits.map(async (comm) => {
      const res = await fetchBranchesRequest(comm);
      console.log(res);
      return setBranches((branch) => [
        ...branch,
        { repo: res.repository_id, branches: res.branch },
      ]);
    });
  };

  const fetchBranchesRequest = async (comm) => {
    const res = await axios.get(
      `${type.dbFeederUrl}/branch/repository/${comm.repositoryId}/`
    );
    return res.data;
  };
  /*
    For each of the recent commits we can call the lifecycle api endpoint 
    to fetch the lifecycle of those repos and display them as graphs
 
    There will always be 5 recent commits
  */
  const renderLifecycleGraphs = () => {
    if (recentCommits.length !== 0) {
      fetchBranches();
      setInit(false);
      return <div>s</div>;
    }
    return;
  };
  console.log(branches);
  return <div>rthtrh</div>;
};
const mapStateToProps = ({ recentCommits }) => {
  return { recentCommits };
};
export default connect(mapStateToProps, actions)(RecentCommits);
