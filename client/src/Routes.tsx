import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import UserPercent from "./components/graphs/weekStats/UserPercent";
const Routes: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/:week" exact component={UserPercent} />
      </Switch>
    </Router>
  );
};
export default Routes;
