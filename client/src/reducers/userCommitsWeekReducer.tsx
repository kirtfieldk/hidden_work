import { UserCommitsWeek } from "../types/UserCommitsWeek";
import { UserCommitActionType, FETCH_USER_COMMITS } from "../types/actions";
// Starts with empty array of user commits
const defaultState: UserCommitsWeek[] = [];
const userCommitWeekReducer = (
  state = defaultState,
  action: UserCommitActionType
): UserCommitsWeek[] => {
  switch (action.type) {
    case FETCH_USER_COMMITS:
      return action.commits;
    default:
      return defaultState;
  }
};

export { userCommitWeekReducer };
