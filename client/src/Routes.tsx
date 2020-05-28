import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import UserPercent from "./components/graphs/weekStats/UserPercent";
import Home from "./components/Homepage";
import WeekStats from "./components/graphs/weekStats/WeekStats";
import UserPage from "./components/userPage/UserPage";
import RepoPage from "./components/repoPage/RepoPage";
import LoadingPage from "./components/loadingPage/LoadingPage";
import RepoDiscoverPage from "./components/repoPage/RepoDiscoverPage";
import PageNotFound from "./components/pageNotFound/PageNoFound";
const Routes: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/stats/week/:week" component={WeekStats} />
        <Route path="/users/:user/week/:week" component={UserPage} />
        <Route path="/repo/:repoId" component={RepoPage} />
        <Route path="/loading/" component={LoadingPage} />
        <Route path="/discover/repos/" component={RepoDiscoverPage} />
        <Route path="*" component={PageNotFound} />
      </Switch>
    </Router>
  );
};
export default Routes;
