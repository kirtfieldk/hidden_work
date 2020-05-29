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
      if (branchesInRepo[0].branch !== "") {
        setBranch(branchesInRepo);
      } else {
        const res = await axios.get(
          `http://localhost:8080/api/v1/branch/repository/${repoId}/`
        );
        setBranch(res.data);
      }
    };

    fetcher();
  }, [branchesInRepo, repoId]);

  const compare = (a: simplePoints, b: simplePoints) => {
    if (a.xPoint > b.xPoint) return -1;
    if (a.xPoint > b.xPoint) return -1;
    return 0;
  };
  const setStats = (dataGraph: simplePoints[][]) => {
    const labels: string[] = [];
    dataGraph.map((el) =>
      el.sort(compare).map((e) => {
        /*  Remove dupes in string array for easy Sort */
        const le = labels.filter((k) => k === e.xPoint);
        if (le.length === 0) {
          /* If not in label Array */
          return labels.push(e.xPoint);
        }

        /* Place those values Into a dateAndCommits struct */
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
    console.log(data);
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
        if (res[0]) {
          return res[0].value;
        } else return 0;
      }),
    };
  };
  /* Parses the time into YYYY-MM-DD ~ignores the hour and minutes
    Than reduce the array so that multiple commits on the same day do not affect
    the line graph
  */
  const parseData = (data: BranchesInRepo): Commits[] => {
    const reduceArr: Commits[] = [];
    /* Removes min/hour/sec of timestamp */

    data.commits.map(
      (com) => (com.committed_at = com.committed_at.split(" ")[0])
    );
    // /* Populate the data property of commit object */

    data.commits.map((el) => {
      /* Array of all commits on the same day same branch */
      const filteredCommits: Commits[] = reduceArr.filter(
        (commit) => commit.committed_at === el.committed_at
      );
      /* If this date is not in the reducedArr */
      if (filteredCommits.length === 0) {
        reduceArr.push(el);
      } else {
        /*
            if it is already In the reduced Array we Need to find each entry
            in the Original data.commit and place that value into the
            reducedArr.data = length
          */
        reduceArr.map((reduce) => {
          if (reduce.committed_at === el.committed_at)
            return (reduce.data = data.commits.filter(
              (com) => com.committed_at === el.committed_at
            ).length);
        });
      }
    });
    return reduceArr;
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
      graphData.push({ xPoint: el.committed_at, value: el.data | 0, branch });
    });
    return graphData;
  };

  // /* Main func that renders the Line graph for the num of ccommits in a repo Branch */
  // /* Need to merge all branches into one  */
  const renderLineGraphCommits = () => {
    let graphData: simplePoints[][] = [];
    if (branchRepo.length > 0) {
      branchRepo.forEach((branch) => {
        const temp: Commits[] = parseData(branch);
        branch.commits = temp.slice();
        graphData = [
          ...graphData,
          formateDataForGraph(branch.branch, branch.commits),
        ];
      });
      return (
        <Line
          key={branchRepo[0].branch_id}
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
