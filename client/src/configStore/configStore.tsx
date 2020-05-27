/*
  Setting up Redux store to store all variables
*/
import { createStore, combineReducers, applyMiddleware } from "redux";
import thunk, { ThunkMiddleware } from "redux-thunk";
import { AppActions } from "../types/actions";
/* Import Reducers */
import { userReducer } from "../reducers/fetchUserReducer";
import { allUsersPercentReducer } from "../reducers/allUsersPercentReducer";
import { commitsInRepoReducer } from "../reducers/CommitsInRepoReducer";
import { lifecycleReducer } from "../reducers/lifecycleReducer";
import { workedOnReposReducer } from "../reducers/reposWorkedOnReducer";
import { statsUserWeekReducer } from "../reducers/statsUserWeekReeducer";
import { userCommitWeekReducer } from "../reducers/userCommitsWeekReducer";
import { allUsersReducer } from "../reducers/allUsersReducer";
import { branchesInRepoReducer } from "../reducers/branchesInRepoReducer";
import { fullCommitReducer } from "../reducers/fullCommitsReducer";
import { userCommitsInWeekReducer } from "../reducers/userCommitsInWeekReducer";
import { distinctReposWorkedOnWeekReducer } from "../reducers/distinctReposWorkedOnWeek";
import { allReposReducer } from "../reducers/allReposReducer";
export const rootReducer = combineReducers({
  userReducer,
  allUsersPercentReducer,
  commitsInRepoReducer,
  lifecycleReducer,
  workedOnReposReducer,
  branchesInRepoReducer,
  allReposReducer,
  statsUserWeekReducer,
  userCommitWeekReducer,
  allUsersReducer,
  fullCommitReducer,
  distinctReposWorkedOnWeekReducer,
  userCommitsInWeekReducer,
});
export type AppState = ReturnType<typeof rootReducer>;
export const store = createStore(
  rootReducer,
  applyMiddleware(thunk as ThunkMiddleware<AppState, AppActions>)
);
