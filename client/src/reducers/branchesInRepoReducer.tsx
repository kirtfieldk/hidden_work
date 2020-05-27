import { BranchesInRepo } from "../types/BranchesInRepo";
import {
  FETCH_BRANCHES_IN_REPO,
  RESET_FETCH_BRANCHES_IN_REPO,
  BranchesInRepoActionType,
} from "../types/actions";

const defaultState: BranchesInRepo[] = [
  {
    branch_id: 0,
    branch: "",
    repository_id: 0,
    latest_commit: "",
    repository: "",
    commits: [
      {
        commit_id: 0,
        committer: "",
        message: "",
        committed_at: "",
        parent_commit: "",
        committer_id: "",
        repositoryId: 0,
      },
    ],
  },
];

const branchesInRepoReducer = (
  state = defaultState,
  action: BranchesInRepoActionType
): BranchesInRepo[] => {
  switch (action.type) {
    case FETCH_BRANCHES_IN_REPO:
      return action.data;
    case RESET_FETCH_BRANCHES_IN_REPO:
      return defaultState;
    default:
      return state;
  }
};

export { branchesInRepoReducer };
