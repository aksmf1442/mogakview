import React from "react";
import { useNavigate } from "react-router-dom";
import ReactCardFlip from "react-card-flip";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import { Typography } from "@mui/material";
import Carousel from "react-material-ui-carousel";

function QuizPage() {
  const naviate = useNavigate();
  const [isFlipped, setIsFlipped] = React.useState(false);
  const [page, setPage] = React.useState(1);

  const qnaBook1 = {
    title: "testTitle1",
    question: "question1",
    answer: "answer1",
  };

  const qnaBook2 = {
    title: "testTitle2",
    question: "question2",
    answer: "answer2",
  };
  const qnaBooks = [qnaBook1, qnaBook2];

  const handleCardClick = (e) => {
    setIsFlipped(!isFlipped);
  };

  const handlePageLeftMove = () => {
    if (page > 1) {
      setPage(page - 1);
      setIsFlipped(false);
    }
  };

  const handlePageRightMove = () => {
    if (page < qnaBooks.length) {
      setPage(page + 1);
      setIsFlipped(false);
    }
  };

  const handleQuizFinish = () => {
    naviate("/quizResult");
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "80vh",
      }}
    >
      <Box style={{ backgroundColor: "#F6F7F9" }}>
        <Carousel
          autoPlay={false}
          indicators={false}
          animation="slide"
          navButtonsAlwaysVisible={true}
          cycleNavigation={false}
          NavButton={({ onClick, style, next, prev }) => {
            return (
              <Button
                onClick={(e) => {
                  onClick();
                  if (next) {
                    handlePageRightMove();
                  }
                  if (prev) {
                    handlePageLeftMove();
                  }
                }}
                style={style}
              >
                {prev && <ChevronLeftIcon />}
                {next && <ChevronRightIcon />}
              </Button>
            );
          }}
          sx={{ width: "60vw", height: "50vh" }}
        >
          {qnaBooks.map((qnaBook, idx) => (
            <ReactCardFlip
              isFlipped={isFlipped}
              flipDirection="vertical"
              key={idx}
            >
              <Button
                className="front"
                onClick={handleCardClick}
                style={{ textTransform: "none" }}
              >
                <Box sx={{ width: "60vw", height: "50vh" }}>
                  <Typography variant="h5">{qnaBook.title}</Typography>
                  <Typography variant="h6">Q.</Typography>
                  <Box
                    sx={{
                      width: "80%",
                      height: "80%",
                      display: "flex",
                      alignItems: "center",
                    }}
                  >
                    <Typography
                      sx={{
                        textAlign: "left",
                        alignItems: "center",
                      }}
                    >
                      {qnaBook.question}
                    </Typography>
                  </Box>
                </Box>
              </Button>
              <Button
                className="back"
                onClick={handleCardClick}
                style={{ textTransform: "none" }}
              >
                <Box sx={{ width: "60vw", height: "50vh" }}>
                  <Typography variant="h5">{qnaBook.title}</Typography>
                  <Typography variant="h6">A.</Typography>
                  <Box
                    sx={{
                      width: "80%",
                      height: "80%",
                      display: "flex",
                      alignItems: "center",
                    }}
                  >
                    <Typography
                      sx={{
                        textAlign: "left",
                        alignItems: "center",
                      }}
                    >
                      {qnaBook.answer}
                    </Typography>
                  </Box>
                </Box>
              </Button>
            </ReactCardFlip>
          ))}
        </Carousel>
      </Box>
      <Box textAlign={"center"}>
        <Typography variant="span">
          {page} / {qnaBooks.length}
        </Typography>
      </Box>
      <Button
        onClick={handleQuizFinish}
        sx={{
          width: "60vw",
          marginTop: 5,
          backgroundColor: "#E3EFF6",
          color: "#6D73AD",
        }}
      >
        퀴즈 끝내기!
      </Button>
    </Box>
  );
}

export default QuizPage;
