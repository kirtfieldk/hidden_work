import { ReposWorkedOn } from "../types/ReposWorkedOn";
import { allReposActionType, FETCH_ALL_REPO_NAME } from "../types/actions";
const defaultState: ReposWorkedOn[] = [];

const allReposReducer = (
  state = defaultState,
  action: allReposActionType
): ReposWorkedOn[] => {
  switch (action.type) {
    case FETCH_ALL_REPO_NAME:
      return action.data;
    default:
      return state;
  }
};

export { allReposReducer };
