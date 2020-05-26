import React, { useState, useEffect } from "react";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux";
import { AppActions } from "../../types/actions";
import { ThunkDispatch } from "redux-thunk";
import { bindActionCreators } from "redux";
import { fetchAllUsers } from "../../actions";
interface passedFromParent {
  user?: String;
  currentDate: String;
}
interface LinkDispatchProps {
  reset: () => void;
}
type props = passedFromParent & LinkDispatchProps;

const Cal: React.FC<props> = ({ user, currentDate, reset }) => {
  const [date, setDate] = useState({ year: "", month: "", day: "" });
  useEffect(() => {
    reset();
    setDate({ year: "", month: "", day: "" });
  }, [currentDate]);
  const renderInput = () => {
    return (
      <div className="flex justify-end">
        <div className="text-xl font-bold mx-5">Date:</div>
        <input
          className="border-b-2 flex w-10"
          value={date.year}
          placeholder="YYYY"
          onChange={(e) => {
            e.target.value.length <= 4
              ? setDate({
                  year: e.target.value,
                  month: date.month,
                  day: date.day,
                })
              : null;
          }}
        />
        /
        <input
          className="border-b-2 flex w-10"
          value={date.month}
          placeholder="MM"
          onChange={(e) => {
            e.target.value.length <= 2
              ? setDate({
                  year: date.year,
                  month: e.target.value,
                  day: date.day,
                })
              : null;
          }}
        />
        /
        <input
          className="border-b-2 flex w-10"
          value={date.day}
          placeholder="DD"
          onChange={(e) => {
            e.target.value.length <= 2
              ? setDate({
                  year: date.year,
                  month: date.month,
                  day: e.target.value,
                })
              : null;
          }}
        />
        <div className="border-2 mx-4 border-deepPrimary px-5 text-center bg-primarycolor text-white hover:bg-black">
          <NavLink
            to={
              user
                ? `/users/${user}/week/${date.year}/${date.month}/${date.day}`
                : `/stats/week/${date.year}/${date.month}/${date.day}`
            }
          >
            GO!
          </NavLink>
        </div>
      </div>
    );
  };
  return <div className="mx-10 my-6">{renderInput()}</div>;
};
/* Connect To Store */
const mapDispatchToProps = (
  dispatch: ThunkDispatch<any, any, AppActions>
): LinkDispatchProps => ({
  reset: bindActionCreators(fetchAllUsers, dispatch),
});

export default connect(null, mapDispatchToProps)(Cal);
