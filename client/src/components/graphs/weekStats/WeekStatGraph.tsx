import React, { useEffect } from "react";
import { Bar } from "react-chartjs-2";
import { connect } from "react-redux";
import { AppState } from "../../../configStore/configStore";
import { StatsUserInWeek } from "../../../types/StatsUserInWeek";
/*
    Renders the bar graph for inserts and deletes for a given week 
    This takes a second to load so we will implement the loading reducer 
    around the load time of the bar graph
*/

type props = linkStateToProps;
const WeekStatGraph: React.FC<props> = ({ weekStats }) => {
  const renderInfo = () => {
    const stats = {
      labels: ["Inserts", "Deletes", "Net Changes"],
      datasets: [
        {
          label: ["Amount"],
          backgroundColor: ["rgba(100,22,333,23)", "#18c651", "#c618c3"],
          borderColor: ["rgba(100,22,333,23)", "#18c651", "#c618c3"],
          data: [weekStats.insert, weekStats.delete, weekStats.net_change],
        },
      ],
    };
    return (
      <div className="mx-auto">
        <Bar
          data={stats}
          options={{
            title: {
              display: true,
              fontColor: "black",
              text: "Ins/Del",
              fontSize: 20,
            },
            legend: { display: true, positoion: "right" },
          }}
        />
      </div>
    );
  };

  return <div className="w-full md:w-1/2 lg:w-1/2 py-10">{renderInfo()}</div>;
};
interface linkStateToProps {
  weekStats: StatsUserInWeek;
}
const mapStateToProps = (state: AppState) => {
  return { weekStats: state.statsUserWeekReducer };
};
export default connect(mapStateToProps)(WeekStatGraph);
