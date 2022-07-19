import * as React from "react";
import { CssBaseline } from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { MainFeaturePost, Search } from "../../components/landing";
import together from "../../assets/together.png";

const mainFeaturedPost = {
  title: "면접을 혼자 준비하는 것은 어렵습니다!",
  description: `만나서 면접 스터디를 하는 것은 다양한 이유로 어려울 수 있습니다.
  이 곳에 모여서 각자 인터뷰를 준비하고,
  면접 질문 리스트를 서로 공유하고,
  취약한 부분을 퀴즈로 풀어봐요!`,
  image: together,
  imageLabel: "together",
};

const theme = createTheme();

function LandingPage() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <MainFeaturePost post={mainFeaturedPost} />
      <Search />
    </ThemeProvider>
  );
}

export default LandingPage;
