import { StatsUserInWeek } from "../types/StatsUserInWeek";
import {
  UserStatsActionType,
  FETCH_STATS_USER,
  FETCH_STATS_WEEK,
} from "../types/actions";
/*  
    This info is used to render bar graph If we are
    fetching specific user Stats it overrides the week stats
    and vice versa
*/
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
    case FETCH_STATS_WEEK:
      return action.data;
    default:
      return state;
  }
};
export { statsUserWeekReducer };
