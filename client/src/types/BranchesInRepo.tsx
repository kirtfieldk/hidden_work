import { Commits } from "./Commits";

export interface BranchesInRepo {
  branch_id: number;
  branch: string;
  repository_id: number;
  latest_commit: string;
  repository: string;
  commits: Commits[];
}
