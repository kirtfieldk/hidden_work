import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Line } from "react-chartjs-2";
import * as actions from "../../actions";
import { AppState } from "../../configStore/configStore";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
import { LifecycleRepo } from "../../types/LifeCycleRepo";
import { StatsUserInWeek } from "../../types/StatsUserInWeek";
type props = linkDispatchToProps & linkStateToProps & propsFromParent;
interface simpledict {
  repository_id: number;
}
interface propsFromParent {
  graphData?: simpledict[];
}
const LifecycleLineGraph: React.FC<props> = ({
  lifeRepoStats,
  fetchLifecycleRepoBranch,
  graphData,
  resetLifecycleRepoBranch,
}) => {
  /*
        Upon rendering this component we must fetch the lifecycle of the working repo
        For this we need the repoId, and branch
 
        If there is graphData prop is passed from parent component that we must call the 
        fetchLifecycleRepoBranch action
 
        the response data is stored in lifeRepoStats reducer
        This takes a little bit of time
    */
  useEffect(() => {
    if (graphData) {
      graphData.map((el) => fetchLifecycleRepoBranch(el.repository_id));
    }
    /* When component is off screen we clear the lifecycle reducer */
    return () => resetLifecycleRepoBranch();
  }, [graphData]);
  const setData = (data: LifecycleRepo) => {
    const labels: string[] = [];
    /*  Remove dupes dates */
    data.stat.map((e) => {
      if (labels.filter((k: string) => k === e.start).length === 0)
        labels.push(e.start);
    });
    return {
      labels: labels,
      datasets: setDataSet(data.stat, data.branch),
    };
  };

  /* We need to spawn numerous line */
  const setDataSet = (data: StatsUserInWeek[], branch: string) => {
    return {
      label: branch,
      fill: false,
      lineTension: 0.5,
      backgroundColor: "rgba(75,192,192,1)",
      borderColor: `#${Math.floor(Math.random() * 16777215).toString(16)}`,
      data: data.map((el) => el.net_change),
    };
  };

  const renderGraph = () => {
    return lifeRepoStats.map((el: LifecycleRepo) => {
      return (
        <Line
          key={`${Math.floor(Math.random() * 16777215).toString(16)}`}
          data={setData(el)}
          options={{
            title: {
              display: true,
              backgroundColor: "rgba(255,255,255,1)",
              textColor: "rgba(0,0,0,1)",
              text: `${el.repository.toUpperCase()}`,
              fontSize: 15,
            },
          }}
        />
      );
    });
  };
  return <div className="pb-10">{renderGraph()}</div>;
};
interface linkDispatchToProps {
  fetchLifecycleRepoBranch: (repoId: number) => void;
  resetLifecycleRepoBranch: () => void;
}
interface linkStateToProps {
  lifeRepoStats: LifecycleRepo[];
}
const mapStateToProps = (state: AppState) => {
  return { lifeRepoStats: state.lifecycleReducer };
};
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): linkDispatchToProps => ({
  fetchLifecycleRepoBranch: bindActionCreators(
    actions.fetchLifecycleRepoBranch,
    dispatch
  ),
  resetLifecycleRepoBranch: bindActionCreators(
    actions.resetFetchLifecycleRepoBranch,
    dispatch
  ),
});
export default connect(mapStateToProps, mapDispatchToProps)(LifecycleLineGraph);
