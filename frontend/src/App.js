import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { LandingPage, OauthCallbackPage } from "./views";
import { Header } from "./components";

function App() {
  return (
    <>
      <Header />
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/google/callback" element={<OauthCallbackPage />} />
          <Route path="/kakao/callback" element={<OauthCallbackPage />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
