import { ReposWorkedOn } from "../types/ReposWorkedOn";
import { ReposWordedOnActionType, REPOS_WORK_WEEK } from "../types/actions";
const defaultState: ReposWorkedOn[] = [];

const workedOnReposReducer = (
  state = defaultState,
  action: ReposWordedOnActionType
): ReposWorkedOn[] => {
  switch (action.type) {
    case REPOS_WORK_WEEK:
      return action.data;
    default:
      return state;
  }
};
export { workedOnReposReducer };
