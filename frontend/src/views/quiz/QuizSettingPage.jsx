import React from "react";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import SelectQnaBook from "../../components/qnabook/SelectQnaBook";
import { styled } from "@mui/material/styles";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import { useNavigate } from "react-router-dom";

const StyledToggleButtonGroup = styled(ToggleButtonGroup)(({ theme }) => ({
  "& .MuiToggleButtonGroup-grouped": {
    margin: theme.spacing(0.5),
    border: 0,
    "&.Mui-disabled": {
      border: 0,
    },
    "&:not(:first-of-type)": {
      borderRadius: theme.shape.borderRadius,
    },
    "&:first-of-type": {
      borderRadius: theme.shape.borderRadius,
    },
  },
}));

function QuizSettingPage() {
  const navigate = useNavigate();
  const [formats, setFormats] = React.useState(() => [""]);

  const tags = ["tag1", "tag2", "tag3", "tag4", "tag5"];
  const qnaBooks = ["title1", "title2"];

  const handleFormat = (event, newFormats) => {
    setFormats(newFormats);
  };

  const handleStartButton = (event) => {
    // 리덕스로 퀴즈 풀 데이터(선택된 면접모음집) 저장하고 navigate로 퀴즈 푸는 페이지로 넘어가기
    navigate("/quiz");
  };

  const selectQnaBooks = (
    <Box sx={{ flexGrow: 1, marginBottom: 5, marginTop: 5 }}>
      <Grid container spacing={1}>
        <StyledToggleButtonGroup
          value={formats}
          onChange={handleFormat}
          color="primary"
        >
          {qnaBooks.map((qnaBook, idx) => (
            //value를 qnaBookId로 할 예정
            <ToggleButton value={qnaBook} key={idx}>
              <Grid item key={idx}>
                <SelectQnaBook title={"test"} heartCount={3} tags={tags} />
              </Grid>
            </ToggleButton>
          ))}
        </StyledToggleButtonGroup>
      </Grid>
    </Box>
  );

  return (
    <Container>
      <Typography variant="h6" gutterBottom>
        퀴즈 설정
      </Typography>
      <Typography variant="p" gutterBottom sx={{ opacity: 0.5 }}>
        어떤 퀴즈를 풀지 선택해주세요!
      </Typography>
      <Container>{selectQnaBooks}</Container>
      <Container>
        <Button
          fullWidth
          sx={{ backgroundColor: "#E3EFF6", color: "#6D73AD" }}
          onClick={handleStartButton}
        >
          시작하기
        </Button>
      </Container>
    </Container>
  );
}

export default QuizSettingPage;
