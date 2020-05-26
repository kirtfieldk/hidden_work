/*
    Actions to fetch Data from Backend Java API
*/

import {
  FETCH_ALL_USERS,
  FETCH_STATS_USER,
  LIFE_REPO_BRANCH,
  RESET_LIFE_REPO_BRANCH,
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
/* Fetch Stats for user during the selected week */
export const fetchStatsForUser = (author: string, week: string) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(
      `${URL}/numberCruncher/stats/author/${author}/week/${week}`
    );
    return dispatch({ type: FETCH_STATS_USER, data: res.data });
  };
};
/* Fetch stats per branch in repo of all historic commits */
export const fetchLifecycleRepoBranch = (repoId: number) => {
  return async (dispatch: Dispatch) => {
    const res = await axios.get(
      `${URL}/repositories/stats/lifecycle/repository/${repoId}`
    );
    return dispatch({ type: LIFE_REPO_BRANCH, data: res.data });
  };
};
/* Reset lifeRepoBranch inbetween renders */
export const resetFetchLifecycleRepoBranch = () => {
  return (dispatch: Dispatch) =>
    dispatch({ type: RESET_LIFE_REPO_BRANCH, data: [] });
};
