import React from "react";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import ToggleButton from "@mui/material/ToggleButton";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Divider from "@mui/material/Divider";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";

const StyledToggleButtonGroup = styled(ToggleButtonGroup)(({ theme }) => ({
  "& .MuiToggleButtonGroup-grouped": {
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

function QuizResultPage() {
  const text =
    "quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz";

  const [formats, setFormats] = React.useState(() => [""]);

  const handleFormat = (event, newFormats) => {
    setFormats(newFormats);
  };

  const handleSelectAllQna = () => {
    // 전체 선택되게 변경해햐 함
    const newFormats = [1, 2];
    setFormats(newFormats);
  };

  const handleDeleteAllQna = () => {
    setFormats([""]);
  };

  const handleNavigateToQuiz = () => {
    // 선택한 문제들 퀴즈로 풀 수 있게 해야 함
  };

  const footer = (
    <Paper
      sx={{
        position: "fixed",
        bottom: 0,
        left: 0,
        right: 0,
      }}
      elevation={3}
    >
      <BottomNavigation showLabels sx={{ backgroundColor: "#E3EFF6" }}>
        <BottomNavigationAction
          label="전체 선택"
          sx={{ color: "#6D73AD" }}
          onClick={handleSelectAllQna}
        />
        <BottomNavigationAction
          label="전체 선택 해제"
          sx={{ color: "#6D73AD" }}
          onClick={handleDeleteAllQna}
        />
        <BottomNavigationAction
          label="선택한 문제 다시 풀기"
          sx={{ color: "#6D73AD" }}
          onClick={handleNavigateToQuiz}
        />
      </BottomNavigation>
    </Paper>
  );

  return (
    <Box>
      <Container>
        <Grid
          container
          sx={{
            display: "flex",
            justifyContent: "space-between",
            marginTop: 4,
          }}
        >
          <Typography variant="h4">퀴즈 결과</Typography>
        </Grid>
        <Container>
          <StyledToggleButtonGroup
            value={formats}
            onChange={handleFormat}
            color="primary"
            fullWidth
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              flexDirection: "column",
              marginBottom: 10,
            }}
          >
            <ToggleButton value={1} style={{ textTransform: "none" }}>
              <Card sx={{ width: "100%" }}>
                <CardContent>
                  <Typography variant="h6" component="h6" paragraph>
                    title
                  </Typography>
                </CardContent>
                <Divider />
                <CardContent sx={{ fontSize: 16 }}>
                  <Typography variant="p" component="p" paragraph>
                    Q. {text}
                  </Typography>
                </CardContent>
                <Divider />
                <CardContent sx={{ fontSize: 16 }}>
                  <Typography gutterBottom variant="p" component="p">
                    A. {1}
                  </Typography>
                </CardContent>
              </Card>
            </ToggleButton>
            <ToggleButton value={2} style={{ textTransform: "none" }}>
              <Card sx={{ width: "100%" }}>
                <CardContent>
                  <Typography variant="h6" component="h6" paragraph>
                    title
                  </Typography>
                </CardContent>
                <Divider />
                <CardContent sx={{ fontSize: 16 }}>
                  <Typography variant="p" component="p" paragraph>
                    Q. {text}
                  </Typography>
                </CardContent>
                <Divider />
                <CardContent sx={{ fontSize: 16 }}>
                  <Typography gutterBottom variant="p" component="p">
                    A. {1}
                  </Typography>
                </CardContent>
              </Card>
            </ToggleButton>
          </StyledToggleButtonGroup>
        </Container>
      </Container>
      {footer}
    </Box>
  );
}

export default QuizResultPage;
