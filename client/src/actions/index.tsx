/*
    Actions to fetch Data from Backend Java API
*/

import {
  FETCH_ALL_USERS,
  FETCH_STATS_USER,
  LIFE_REPO_BRANCH,
  RESET_LIFE_REPO_BRANCH,
  ALL_USER_PERCENT,
  FETCH_STATS_WEEK,
  FETCH_BRANCHES_IN_REPO,
  RESET_FETCH_BRANCHES_IN_REPO,
  FETCH_ALL_COMMITS_IN_REPO,
  RESET_FETCH_FULL_COMMIT_INFO,
  FETCH_FULL_COMMIT_INFO,
  REPO_NUMBER_CHANGES,
  FETCH_DISTINCT_REPOS_WORKED_ON_WEEK,
  FETCH_ALL_REPO_NAME,
  FETCH_USER,
} from "../types/actions";
import axios from "axios";
import { Dispatch } from "redux";

const URL = "http://localhost:8080/api/v1";
/* Fetches All Users */
export const fetchAllUsers = () => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(`${URL}/users`);
    return dispatch({ type: FETCH_ALL_USERS, data: res.data });
  };
};
export const fetchuser = (id: string) => async (dispatch: Dispatch) => {
  const res = await axios.get(`${URL}/users/id/${id}/`);
  dispatch({ type: FETCH_USER, data: res.data });
};

/* Fetch Stats for user during the selected week */
export const fetchStatsForUser = (author: string, week: string) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(
      `${URL}/numberCruncher/stats/author/${author}/week/${week}/`
    );
    return dispatch({ type: FETCH_STATS_USER, data: res.data });
  };
};

export const fetchCommitsByUserInWeek = (author: string, week: string) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(
      `${URL}/numberCruncher/commits/author/${author}/week/${week}/`
    );
    return dispatch({ type: REPO_NUMBER_CHANGES, data: res.data });
  };
};

/* Fetch stats per branch in repo of all historic commits */
export const fetchLifecycleRepoBranch = (repoId: number) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(
      `${URL}/repositories/stats/lifecycle/repository/${repoId}/`
    );
    return dispatch({ type: LIFE_REPO_BRANCH, data: res.data });
  };
};
/* Reset lifeRepoBranch inbetween renders */
export const resetFetchLifecycleRepoBranch = () => {
  return (dispatch: Dispatch) =>
    dispatch({ type: RESET_LIFE_REPO_BRANCH, data: [] });
};

/* Fetch all user percent commiyts in week */
export const fetchAllUserPercent = (week: String) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(`${URL}/userpercent/week/${week}/`);
    dispatch({ type: ALL_USER_PERCENT, data: res.data });
  };
};

/* Fetch Combine inserts/deletes for a Week */
export const fetchStatsWeek = (week: string) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(`${URL}/numberCruncher/week/stats/${week}/`);
    dispatch({ type: FETCH_STATS_WEEK, data: res.data });
  };
};

export const fetchBranchesInRepo = (repoId: number) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(`${URL}/branch/repository/${repoId}/`);
    dispatch({ type: FETCH_BRANCHES_IN_REPO, data: res.data });
  };
};

export const resetBranchesInRepo = () => (dispatch: Dispatch) => {
  return dispatch({ type: RESET_FETCH_BRANCHES_IN_REPO, data: [] });
};

/* Fetching full Commits in a repo */
export const fetchFullCommitInfo = (repoId: number) => async (
  dispatch: Dispatch
) => {
  const res = await axios.get(`${URL}/commmits/full/repo/${repoId}/`);
  return dispatch({
    type: FETCH_FULL_COMMIT_INFO,
    data: res.data,
  });
};
export const resetFetchFullCommitInfo = () => (dispatch: Dispatch) => {
  return dispatch({
    type: RESET_FETCH_FULL_COMMIT_INFO,
    data: [],
  });
};

export const fetchDistinctReposWorkedOnweek = (week: string) => async (
  dispatch: Dispatch
) => {
  const res = await axios.get(`${URL}/repositories/distinct/week/${week}/`);
  dispatch({ type: FETCH_DISTINCT_REPOS_WORKED_ON_WEEK, data: res.data });
};

export const fetchAllrepos = () => async (dispatch: Dispatch) => {
  const res = await axios.get(`${URL}/repositories/names/`);
  dispatch({ type: FETCH_ALL_REPO_NAME, data: res.data });
};
