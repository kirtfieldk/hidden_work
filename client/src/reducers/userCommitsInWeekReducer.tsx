import { UserCommitsWeek } from "../types/UserCommitsWeek";
import {
  REPO_NUMBER_CHANGES,
  userCommitsInWeekActionType,
} from "../types/actions";

const defaultState: UserCommitsWeek[] = [];

const userCommitsInWeekReducer = (
  state = defaultState,
  action: userCommitsInWeekActionType
): UserCommitsWeek[] => {
  switch (action.type) {
    case REPO_NUMBER_CHANGES:
      return action.data;
    default:
      return state;
  }
};

export { userCommitsInWeekReducer };
