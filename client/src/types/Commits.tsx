/* 
  This is used as the sub arr when fetching all the commits in a branch with data
  being the number of changes in that commit
*/
export interface Commits {
  commit_id: number;
  committer: string;
  message: string;
  committed_at: string;
  parent_commit: string;
  committer_id: string;
  repositoryId: number;
  data: number;
}
