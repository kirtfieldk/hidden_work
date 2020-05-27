import { FullCommitInfo } from "../types/FullCommitInfo";
import {
  RESET_FETCH_FULL_COMMIT_INFO,
  FullCommitInfoActionType,
  FETCH_FULL_COMMIT_INFO,
} from "../types/actions";

const defaultState: FullCommitInfo[] = [];

const fullCommitReducer = (
  state = defaultState,
  action: FullCommitInfoActionType
): FullCommitInfo[] => {
  switch (action.type) {
    case FETCH_FULL_COMMIT_INFO:
      return action.data;
    case RESET_FETCH_FULL_COMMIT_INFO:
      return [];
    default:
      return state;
  }
};
export { fullCommitReducer };
