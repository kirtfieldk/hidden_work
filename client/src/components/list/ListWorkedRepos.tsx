import React, { useEffect } from "react";
import { connect } from "react-redux";
import { NavLink } from "react-router-dom";
import * as actions from "../../actions";
import { AppState } from "../../configStore/configStore";
import { ReposWorkedOn } from "../../types/ReposWorkedOn";
import { ThunkDispatch } from "redux-thunk";
import { AppActions } from "../../types/actions";
import { bindActionCreators } from "redux";
import { RouteComponentProps } from "react-router-dom";

type props = linkStateToProps & LinkDispatchProps & urlWeek;
interface urlWeek extends RouteComponentProps<{ week: string }> {}
const ListWorkedRepos: React.FC<props> = ({
  match,
  fetchAllDistinctReposWorkedOn,
  reposWorkedOnWeek,
}) => {
  useEffect(() => {
    if (match.params.week) {
      fetchAllDistinctReposWorkedOn(match.params.week);
    }
  }, [match.params.week]);
  const renderWorkedOnRepos = () => {
    if (reposWorkedOnWeek.length > 0) {
      return reposWorkedOnWeek.map((el) => {
        return (
          <div
            key={el.repository_id}
            className="border-2 flex-1 text-center text-white border-gray-500 border-double rounded-md my-2 hover:bg-black hover:text-white px-1 mx-2 py-4"
          >
            <NavLink to={`/repo/${el.repository_id}`}>
              {el.repository.toUpperCase()}
            </NavLink>
          </div>
        );
      });
    }
  };

  return (
    <div className="bg-primarycolor py-10">
      <div className="text-2xl font-bold text-white text-center border-b-2 w-11/12 mx-auto mb-4">
        Repos Worked On
      </div>
      <div className="md:w-11/12 lg:w-11/12 w-screen text-sm mx-auto flex flex-wrap">
        {renderWorkedOnRepos()}
      </div>
    </div>
  );
};
interface LinkDispatchProps {
  fetchAllDistinctReposWorkedOn: (week: string) => void;
}
interface linkStateToProps {
  reposWorkedOnWeek: ReposWorkedOn[];
}
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  fetchAllDistinctReposWorkedOn: bindActionCreators(
    actions.fetchDistinctReposWorkedOnweek,
    dispatch
  ),
});
const mapStateToProps = (state: AppState) => {
  return { reposWorkedOnWeek: state.distinctReposWorkedOnWeekReducer };
};
export default connect(mapStateToProps, actions)(ListWorkedRepos);
