import { createStore, combineReducers, applyMiddleware } from "redux";
import thunk, { ThunkMiddleware } from "redux-thunk";
import {
  UserCommitAction,
  UserActionType,
  CommitsInRepoActionType,
  UserStatsActionType,
  LifecycleActionType,
  UserPercentActionType,
  ReposWordedOnActionType,
  AppActions,
} from "../types/actions";

export const rootReducer = combineReducers();
