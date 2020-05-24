import { CommitFullRepo } from "../types/CommitFullInfo";
import {
  CommitsInRepoActionType,
  FETCH_ALL_COMMITS_IN_REPO,
} from "../types/actions";
const defaultState: CommitFullRepo[] = [];
const commitsInRepoReducer = (
  state = defaultState,
  action: CommitsInRepoActionType
): CommitFullRepo[] => {
  switch (action.type) {
    case FETCH_ALL_COMMITS_IN_REPO:
      return action.commits;
    default:
      return state;
  }
};

export { commitsInRepoReducer };
