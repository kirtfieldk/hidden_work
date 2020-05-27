/* repositories/stats/lifecycle/repository/{} */
import { StatsUserInWeek } from "./StatsUserInWeek";
export interface LifecycleRepo {
  author: string;
  branch: string;
  repositroy_id: number;
  repository: string;
  stat: StatsUserInWeek[];
}
