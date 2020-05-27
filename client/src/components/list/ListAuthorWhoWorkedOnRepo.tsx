import React from "react";
import { connect } from "react-redux";
import { AppState } from "../../configStore/configStore";
import { LifecycleRepo } from "../../types/LifeCycleRepo";

/*
    This component should only be rendered within the users/repo 
    pages thus the reducer lifeRepoStats should be populated!
*/
type props = linkPropsToState;
const ListAuthorWhoWorkedOnRepo: React.FC<props> = ({ lifeRepoStats }) => {
  const renderList = () => {
    return lifeRepoStats.map((el) => {
      return (
        <div key={el.branch} className="text-white mt-5 flex mx-1 pr-2 text-xl">
          {el.author} / {el.branch}
        </div>
      );
    });
  };
  return (
    <div className="bg-primarycolor font-xl">
      <div className="text-white text-center">Author Of Branches</div>
      <div className="flex w-11/12 justify-between pb-10 mx-auto">
        {renderList()}
      </div>
    </div>
  );
};
interface linkPropsToState {
  lifeRepoStats: LifecycleRepo[];
}
const mapPropsToState = (state: AppState) => {
  return { lifeRepoStats: state.lifecycleReducer };
};

export default connect(mapPropsToState)(ListAuthorWhoWorkedOnRepo);
