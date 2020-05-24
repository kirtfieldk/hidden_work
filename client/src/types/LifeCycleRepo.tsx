/* repositories/stats/lifecycle/repository/{} */
import { StatsUserInWeek } from "./StatsUserInWeek";
export interface LifecycleRepo {
  author: string;
  branch: string;
  stats: StatsUserInWeek[];
}
