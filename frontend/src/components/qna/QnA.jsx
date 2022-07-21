import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import { Button, CardActions } from "@mui/material";

export default function Qna() {
  const text =
    "quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quizquiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz quiz";
  return (
    <Card sx={{ marginTop: 3, marginBottom: 3 }}>
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
      <CardActions>
        <Button size="small" color="primary">
          수정
        </Button>
        <Button size="small" color="primary">
          삭제
        </Button>
      </CardActions>
    </Card>
  );
}
