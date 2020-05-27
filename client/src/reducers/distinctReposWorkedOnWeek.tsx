import { ReposWorkedOn } from "../types/ReposWorkedOn";
import {
  distinctReposWorkedOnActionType,
  FETCH_DISTINCT_REPOS_WORKED_ON_WEEK,
} from "../types/actions";

const defaultState: ReposWorkedOn[] = [];

const distinctReposWorkedOnWeekReducer = (
  state = defaultState,
  action: distinctReposWorkedOnActionType
): ReposWorkedOn[] => {
  switch (action.type) {
    case FETCH_DISTINCT_REPOS_WORKED_ON_WEEK:
      return action.data;
    default:
      return state;
  }
};

export { distinctReposWorkedOnWeekReducer };
