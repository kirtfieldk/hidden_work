import { LifecycleRepo } from "../types/LifeCycleRepo";
import {
  LifecycleActionType,
  LIFE_REPO_BRANCH,
  RESET_LIFE_REPO_BRANCH,
} from "../types/actions";
const defaultState: LifecycleRepo[] = [
  {
    author: "",
    branch: "",
    stats: [
      {
        insert: 0,
        delete: 0,
        start: "",
        end: "",
        net_change: 0,
      },
    ],
  },
];
/* if not state provided it is set to default state */
const lifecycleReducer = (
  state = defaultState,
  action: LifecycleActionType
): LifecycleRepo[] => {
  switch (action.type) {
    case LIFE_REPO_BRANCH:
      if (action.data.length === 0) return state;
      /* It has fetched Something from the API */ else
        return [...state, action.data];
    case RESET_LIFE_REPO_BRANCH:
      return defaultState;
    default:
      return defaultState;
  }
};
export { lifecycleReducer };
