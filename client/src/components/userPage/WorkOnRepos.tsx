import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import LifecycleLineGraph from "../graphs/LifecycleLineGraph";
import { AppState } from "../../configStore/configStore";
import { UserCommitsWeek } from "../../types/UserCommitsWeek";
type props = linkStateToProps;
interface simpleInterface {
  repository_id: number;
}
const WorkOnRepos: React.FC<props> = ({ numRepoChange }) => {
  const [data, setDate] = useState([{ repository_id: 0 }]);
  /*
        Upon rendering this component if the user and week params of the url exists we will fetch the
        number of changes for that week. If they do not exists, skip
 
        Eff of useEffct is suck that the life repo graph resets with the new data fetched
    */
  useEffect(() => {
    setDate(reduceReducerByRepository().slice());
  }, [numRepoChange]);
  /*
        Reducer the reducer because there are multiple ins/del that relate to the same repository
        After reducing the fetched data we parse the data to the life cycle line graph
  */
  const reduceReducerByRepository = (): simpleInterface[] => {
    var int = new Map();
    var del = new Map();
    var repoId: simpleInterface[] = [];
    /* Populate the maps SO that values stack and dont spread */
    numRepoChange.forEach((el) => {
      if (int.has(el.repository)) {
        int.set(el.repository, int.get(el.repository) + el.insert);
        del.set(el.repository, del.get(el.repository) + el.delete);
      } else {
        int.set(el.repository, el.insert);
        del.set(el.repository, el.delete);
        repoId.push({ repository_id: el.repository_id });
      }
    });

    var val = {
      inserts: int,
      deletes: del,
      repoId,
    };

    return repoId;
  };
  return (
    <div>
      <div className="hover:shadow">
        <div className="bg-gray-200">
          <LifecycleLineGraph graphData={data} />
        </div>
      </div>
    </div>
  );
};
interface linkStateToProps {
  numRepoChange: UserCommitsWeek[];
}
const mapStateToProps = (state: AppState) => {
  return { numRepoChange: state.userCommitsInWeekReducer };
};
export default connect(mapStateToProps)(WorkOnRepos);
