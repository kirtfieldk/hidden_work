/*
    Exports of types and such
*/
import { CommitFullRepo } from "./CommitFullInfo";
import { UserCommitsWeek } from "./UserCommitsWeek";
import { User } from "./User";
import { StatsUserInWeek } from "./StatsUserInWeek";
import { LifecycleRepo } from "./LifeCycleRepo";
import { UserPercentWeek } from "./UserPercentWeek";
import { ReposWorkedOn } from "./ReposWorkedOn";
export const dbFeederUrl = "http://localhost:8080/api/v1";

export const FETCH_BRANCHES_IN_REPO = "FETCH_BRANCHES_IN_REPO";
export const FETCH_COMMIT_IN_BRANCH = "FETCH_COMMIT_IN_BRANCH";
export const FETCH_ALL_PROJECT = "FETCH_ALL_PROJECT";
export const FETCH_ALL_REPO_NAME = "FETCH_ALL_REPO_NAME";
export const FETCH_STATS_CURRENT_WEEK = "FETCH_STATS_CURRENT_WEEK";
export const USER_COMMITS_CURRENT_WEEK = "USER_COMMITS_CURRENT_WEEK";
export const FETCH_COMMITS = "FETCH_COMMITS";
export const FETCH_STATS_WEEK = "FETCH_STATS_WEEK";
export const FETCH_STATS_FOR_REPO_CURRENT_WEEK =
  "FETCH_STATS_FOR_REPO_CURRENT_WEEK";
export const FETCH_COMMITS_FOR_REPO_CURRENT_WEEK =
  "FETCH_COMMITS_FOR_REPO_CURRENT_WEEK";
export const FETCH_STATS_USER_CURRENT_WEEK = "FETCH_STATS_USER_CURRENT_WEEK";
export const FETCH_STATS_USER = "FETCH_STATS_USER";
export const LIFE_REPO_BRANCH = "LIFE_REPO_BRANCH";
export const ALL_USER_PERCENT = "ALL_USER_PERCENT";
export const ALL_USER_PERCENT_CURRENT_WEEK = "ALL_USER_PERCENT_CURRENT_WEEK";
export const FETCH_RECENT_COMMIT = "FETCH_RECENT_COMMIT";
export const REPO_NUMBER_CHANGES = "REPO_NUMBER_CHANGES";
export const REPOS_WORK_WEEK = "REPOS_WORK_WEEK";
export const FETCH_ALL_USERS = "FETCH_ALL_USERS";
export const FETCH_USER = "FETCH_USER";
export const LOADING = "LOADING";
export const LIFE_REPO_BRANCH_RESET = "LIFE_REPO_BRANCH_RESET";
export const FETCH_ALL_COMMITS_IN_REPO = "FETCH_ALL_COMMITS_IN_REPO";
export const RESET_FETCH_FULL_COMMIT_INFO = "RESET_FETCH_FULL_COMMIT_INFO";

export interface fetchUserCommitWeek {
  type: typeof FETCH_COMMITS;
  commits: UserCommitsWeek[];
}
export interface fetchUser {
  type: typeof FETCH_USER;
  user: User;
}
export interface allCommitsInRepo {
  type: typeof FETCH_ALL_COMMITS_IN_REPO;
  commits: CommitFullRepo;
}
export interface fetchStatsUserWeek {
  type: typeof FETCH_STATS_USER;
  data: StatsUserInWeek;
}
export interface fetchLifecycleRepo {
  type: typeof LIFE_REPO_BRANCH;
  data: LifecycleRepo;
}
export interface fetchUserPercentWeek {
  type: typeof ALL_USER_PERCENT;
  data: UserPercentWeek;
}
export interface fetchReposWorkedOn {
  type: typeof REPOS_WORK_WEEK;
  data: ReposWorkedOn[];
}
