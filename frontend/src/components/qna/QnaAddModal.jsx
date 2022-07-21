import * as React from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import Card from "@mui/material/Card";
import TextareaAutosize from "@mui/material/TextareaAutosize";
import CardContent from "@mui/material/CardContent";
import Divider from "@mui/material/Divider";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import CloseIcon from "@mui/icons-material/Close";
import Slide from "@mui/material/Slide";

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function FullScreenDialog({ title }) {
  const [open, setOpen] = React.useState(false);
  const [question, setQuestion] = React.useState("");
  const [answer, setAnswer] = React.useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const save = () => {
    // createQnaAPI 만들어서 보내기
    handleClose();
  };

  return (
    <div>
      <Button
        onClick={handleClickOpen}
        fullWidth
        sx={{
          backgroundColor: "#E3EFF6",
          color: "#6D73AD",
          border: "solid #F5GGG9 1px",
          padding: 1,
        }}
      >
        새 Q&A 추가
      </Button>
      <Dialog
        fullScreen
        open={open}
        onClose={handleClose}
        TransitionComponent={Transition}
      >
        <AppBar sx={{ position: "relative" }}>
          <Toolbar
            sx={{
              backgroundColor: "#E3EFF6",
              color: "#6D73AD",
            }}
          >
            <IconButton
              edge="start"
              color="inherit"
              onClick={handleClose}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
            <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
              {title}
            </Typography>
            <Button autoFocus color="inherit" onClick={handleClose}>
              save
            </Button>
          </Toolbar>
        </AppBar>
        <Card sx={{ height: "100%" }}>
          <CardContent sx={{ fontSize: 16, height: "40%" }}>
            <Typography variant="h6" component="h6" paragraph>
              Q.
            </Typography>
            <TextareaAutosize
              value={question}
              onChange={(e) => {
                setQuestion(e.target.value);
              }}
              minRows={15}
              maxRows={15}
              placeholder="이곳에 글을 적어주세요!"
              style={{ width: "100%", border: "none", outline: "none" }}
            />
          </CardContent>
          <Typography
            variant="p"
            component="p"
            paragraph
            sx={{ color: "blue" }}
          >
            {question.length}/500
          </Typography>
          <Divider />
          <CardContent sx={{ fontSize: 16, height: "40%" }}>
            <Typography variant="h6" component="h6" paragraph>
              A.
            </Typography>
            <TextareaAutosize
              value={answer}
              onChange={(e) => {
                setAnswer(e.target.value);
              }}
              minRows={15}
              maxRows={15}
              placeholder="이곳에 글을 적어주세요!"
              style={{
                width: "100%",
                border: "none",
                outline: "none",
              }}
            />
          </CardContent>
          <Typography
            variant="p"
            component="p"
            paragraph
            sx={{ color: "blue" }}
          >
            {answer.length}/500
          </Typography>
        </Card>
      </Dialog>
    </div>
  );
}
