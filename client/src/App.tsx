import React from "react";
import { Provider } from "react-redux";
import { store } from "./configStore/configStore";
import Homepage from "./components/Homepage";
const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Homepage />
    </Provider>
  );
};

export default App;
