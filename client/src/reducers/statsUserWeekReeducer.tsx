import { StatsUserInWeek } from "../types/StatsUserInWeek";
import { UserStatsActionType, FETCH_STATS_USER } from "../types/actions";
const defaultState: StatsUserInWeek = {
  insert: 0,
  delete: 0,
  start: "",
  end: "",
  net_change: 0,
};

const statsUserWeekReducer = (
  state = defaultState,
  action: UserStatsActionType
): StatsUserInWeek => {
  switch (action.type) {
    case FETCH_STATS_USER:
      return action.data;
    default:
      return state;
  }
};
export { statsUserWeekReducer };
