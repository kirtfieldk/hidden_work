/* /numbercruncher/commits/author/{}/week{} */
export interface UserCommitsWeek {
  insert: number;
  delete: number;
  commits: string;
  repository: String;
  repository_id: number;
  branch: string;
  author: string;
}
