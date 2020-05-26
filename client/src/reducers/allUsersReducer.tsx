import { User } from "../types/User";
import { FETCH_ALL_USERS, AllUsersActionType } from "../types/actions";

const defaultState: User[] = [];

const allUsersReducer = (
  state = defaultState,
  action: AllUsersActionType
): User[] => {
  switch (action.type) {
    case FETCH_ALL_USERS:
      return action.data;
    default:
      return state;
  }
};
export { allUsersReducer };
