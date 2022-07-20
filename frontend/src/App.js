import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { LandingPage, OauthCallbackPage } from "./views";
import { Header } from "./components";
import QnaBookAddPage from "./views/qnabook/QnaBookAddPage";
import QuizPage from "./views/quiz/QuizPage";

function App() {
  return (
    <>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/quiz" element={<QuizPage />} />
          <Route path="/qnabookAdd" element={<QnaBookAddPage />} />
          <Route path="/google/callback" element={<OauthCallbackPage />} />
          <Route path="/kakao/callback" element={<OauthCallbackPage />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
