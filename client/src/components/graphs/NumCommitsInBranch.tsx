import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import axios from "axios";
import { Line } from "react-chartjs-2";
import { AppState } from "../../configStore/configStore";
import { BranchesInRepo } from "../../types/BranchesInRepo";
import { Commits } from "../../types/Commits";
type props = linkStateToProps & parentProp;
interface parentProp {
  repoId: number;
}
interface simplePoints {
  xPoint: string;
  value: number;
  branch: string;
}
const NumCommitsInBranches: React.FC<props> = ({ branchesInRepo, repoId }) => {
  /*
        Upon init render we fetch the branches in the repo along with all the commits 
    */
  const [branchRepo, setBranch] = useState(branchesInRepo);
  useEffect(() => {
    const fetcher = async () => {
      if (branchesInRepo.length > 0) {
        setBranch(branchesInRepo);
      } else {
        const res = await axios.get(
          `http://localhost:8080/branch/repository/${repoId}/`
        );
        setBranch(res.data);
      }
    };

    fetcher();
  }, [branchesInRepo]);
  const compare = (a: simplePoints, b: simplePoints) => {
    if (a.xPoint > b.xPoint) return -1;
    if (a.xPoint > b.xPoint) return -1;
    return 0;
  };
  const setStats = (dataGraph: simplePoints[][]) => {
    const labels: string[] = [];
    dataGraph.map((el) =>
      el.sort(compare).map((e) => {
        /*  Remove dupes */
        if (labels.filter((k) => k !== e.xPoint).length > 0)
          labels.push(e.xPoint);
      })
    );
    return {
      labels: labels.sort(),
      datasets: dataGraph.map((el) => {
        return setDataset(el, labels);
      }),
    };
  };
  /* For multiple lines in the line graph we need to map over the branches and plot their points */
  const setDataset = (data: simplePoints[], labels: string[]) => {
    return {
      label: data[0].branch,
      fill: false,
      lineTension: 0.5,
      backgroundColor: "rgba(75,192,192,1)",
      borderColor: `#${Math.floor(Math.random() * 16777215).toString(16)}`,
      /* First we iterate through the labels arr which is the dates */
      /* Than we filter through the original array and if it has a value for the week */
      /* we plot that value else we plot a Zero */
      data: labels.sort().map((el) => {
        const res = data.filter((e) => e.xPoint === el);
        if (res.length > 0) {
          return res[0].value;
        } else return 0;
      }),
    };
  };
  /* Parses the time into YYYY-MM-DD ~ignores the hour and minutes 
    Than reduce the array so that multiple commits on the same day do not affect 
    the line graph
  */
  const parseData = (data: BranchesInRepo) => {
    const reduceArr: Commits[] = [];
    data.commits.map(
      (com) => (com.committed_at = com.committed_at.split(" ")[0])
    );
    data.commits.map((el) => {
      el.data = data.commits.filter(
        (commit) => !el.data && el.committed_at === commit.committed_at
      ).length;
    });
    data.commits.map((el) => {
      if (
        reduceArr.filter((e) => e.committed_at === el.committed_at).length === 0
      )
        reduceArr.push(el);
    });
    data.commits = reduceArr;
    return data;
  };
  /*
    For a Line graph to be made we need an array of labels [X_AXIS]
    and an array of data [[1,2,3],[3,2,1], [etc.]]
  */
  const formateDataForGraph = (
    branch: string,
    data: Commits[]
  ): simplePoints[] => {
    const graphData: simplePoints[] = [];
    data.forEach((el) => {
      graphData.push({ xPoint: el.committed_at, value: el.data, branch });
    });
    return graphData;
  };

  /* Main func that renders the Line graph for the num of ccommits in a repo Branch */
  /* Need to merge all branches into one  */
  const renderLineGraphCommits = () => {
    let graphData: simplePoints[][] = [];
    if (branchRepo.length > 0) {
      branchRepo.forEach((branch) => {
        branch = parseData(branch);
        graphData = [
          ...graphData,
          formateDataForGraph(branch.branch, branch.commits),
        ];
      });
      return (
        <Line
          key={branchRepo[1].repository_id}
          data={setStats(graphData)}
          options={{
            title: {
              display: true,
              backgroundColor: "rgba(255,255,255,1)",
              textColor: "rgba(0,0,0,1)",
              text: `${branchRepo[0].repository.toUpperCase()} Commits`,
              fontSize: 15,
            },
          }}
        />
      );
    }
    return <div>Loading</div>;
  };
  /* Main render */
  return <div className="w-full">{renderLineGraphCommits()}</div>;
};
interface linkStateToProps {
  branchesInRepo: BranchesInRepo[];
}
const mapStateToProps = (state: AppState) => {
  return { branchesInRepo: state.branchesInRepoReducer };
};

export default connect(mapStateToProps)(NumCommitsInBranches);
