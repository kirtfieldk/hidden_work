import React from "react";

const LoadingPage = () => {
  const renderLoadPage = () => {
    return (
      <div className="w-11/12 mx-auto overflow-x-hidden shadow justify-between flex flex-wrap">
        <div className="border-2 mx-4 bg-gray-200 h-64 my-6 w-7/12"></div>
        <div className="border-2 mx-4 bg-gray-200 h-64 my-6 w-4/12"></div>
        <div className="border-2 mx-4 bg-gray-200 h-64 my-1 w-full"></div>
      </div>
    );
  };
  return <div>{renderLoadPage()}</div>;
};

export default LoadingPage;
