import { AllUsersPercentWeek } from "../types/AllUsersPercentWeek";
import {
  AllUsersCommitsWeekActionType,
  ALL_USER_PERCENT,
} from "../types/actions";
const defaultState: AllUsersPercentWeek[] = [];

const allUsersPercentReducer = (
  state = defaultState,
  action: AllUsersCommitsWeekActionType
): AllUsersPercentWeek[] => {
  switch (action.type) {
    case ALL_USER_PERCENT:
      return action.data;
    default:
      return state;
  }
};
export { allUsersPercentReducer };
