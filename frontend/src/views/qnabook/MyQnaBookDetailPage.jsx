import React from "react";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import FavoriteIcon from "@mui/icons-material/Favorite";
import Qna from "../../components/qna/QnA";
import QnaAddModal from "../../components/qna/QnaAddModal";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import ToggleButton from "@mui/material/ToggleButton";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { CardActions } from "@mui/material";
import Divider from "@mui/material/Divider";
import { styled } from "@mui/material/styles";

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

function MyQnaBookDetailPage() {
  const text =
    "quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz";

  const [formats, setFormats] = React.useState(() => [""]);

  const handleFormat = (event, newFormats) => {
    console.log(newFormats);
    setFormats(newFormats);
  };

  return (
    <Container>
      <Grid
        container
        sx={{
          display: "flex",
          justifyContent: "space-between",
          marginTop: 4,
        }}
      >
        <Typography variant="h4">title</Typography>
        <Box sx={{ display: "flex", justifyContent: "space-between" }}>
          <FavoriteIcon sx={{ color: "red" }} />
          <Typography variant="span" component="span" sx={{ color: "#6D73AD" }}>
            1
          </Typography>
        </Box>
      </Grid>
      <Grid
        container
        sx={{
          display: "flex",
          justifyContent: "space-between",
          marginTop: 4,
        }}
      >
        <Box sx={{ display: "flex" }}>
          <Typography sx={{ marginRight: 4 }}>#tag1</Typography>
          <Typography sx={{ marginRight: 4 }}>#tag2</Typography>
          <Typography sx={{ marginRight: 4 }}>#tag3</Typography>
          <Typography sx={{ marginRight: 4 }}>#tag4</Typography>
          <Typography sx={{ marginRight: 4 }}>#tag5</Typography>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "space-between" }}>
          <Button
            sx={{
              backgroundColor: "#E3EFF6",
              color: "#6D73AD",
              border: "solid #F5GGG9 1px",
              padding: 1,
            }}
          >
            수정
          </Button>
          <Button
            sx={{
              backgroundColor: "#E3EFF6",
              color: "#6D73AD",
              border: "solid #F5GGG9 1px",
              padding: 1,
            }}
          >
            삭제
          </Button>
        </Box>
      </Grid>
      <Container
        sx={{
          marginTop: 5,
          padding: "3px",
        }}
      >
        <QnaAddModal title={"title"} />
      </Container>
      <Container>
        <Qna />
        <Qna />
      </Container>
    </Container>
  );
}

export default MyQnaBookDetailPage;
