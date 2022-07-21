import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { LandingPage, OauthCallbackPage } from "./views";
import { Header } from "./components";
import QnaBookAddPage from "./views/qnabook/QnaBookAddPage";
import QuizSettingPage from "./views/quiz/QuizSettingPage";
import QnaBookDetailPage from "./views/qnabook/QnaBookDetailPage";
import QnaBookEditPage from "./views/qnabook/QnaBookEditPage";
import QuizPage from "./views/quiz/QuizPage";
import QuizResultPage from "./views/quiz/QuizResultPage";

function App() {
  return (
    <>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/quiz" element={<QuizPage />} />
          <Route path="/quizResult" element={<QuizResultPage />} />
          <Route path="/quizSetting" element={<QuizSettingPage />} />
          <Route path="/qnabookAdd" element={<QnaBookAddPage />} />
          <Route path="/qnabookEdit" element={<QnaBookEditPage />} />
          {/* 테스트용 라우터 */}
          <Route path="/test" element={<QnaBookDetailPage />} />
          <Route path="/google/callback" element={<OauthCallbackPage />} />
          <Route path="/kakao/callback" element={<OauthCallbackPage />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
