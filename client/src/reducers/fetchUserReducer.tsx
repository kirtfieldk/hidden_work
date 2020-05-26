import { User } from "../types/User";
import { UserActionType, FETCH_USER } from "../types/actions";
const defaultState: User = { User_id: "", Name: "" };
const userReducer = (state = defaultState, action: UserActionType): User => {
  switch (action.type) {
    case FETCH_USER:
      return action.user;
    default:
      return state;
  }
};
export { userReducer };
