import * as React from "react";
import { useNavigate } from "react-router-dom";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import { Box, Container } from "@mui/material";
import FormGroup from "@mui/material/FormGroup";
import Switch from "@mui/material/Switch";
import Button from "@mui/material/Button";
import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import postQnaBookApi from "../../api/qnabook/postQnaBookApi";

export default function QnaBookAddPage() {
  const navigate = useNavigate();

  const [title, setTitle] = React.useState("");
  const [opened, setOpened] = React.useState(true);
  const [tag, setTag] = React.useState("");
  const [tags, setTags] = React.useState([]);

  const handleOpenedField = (event) => {
    setOpened(event.target.checked);
  };

  const handleTitleField = (event) => {
    const title = event.target.value;
    setTitle(title);
  };

  const handleTagFieldChange = (event) => {
    const tag = event.target.value;
    setTag(tag);
  };

  const handleTagFieldEnter = (event) => {
    if (event.keyCode === 13) {
      const tag = event.target.value;
      const newTags = [...tags, tag];
      setTags(newTags);
      setTag("");
    }
  };

  const handleTagButton = (event) => {
    const deleteTag = event.currentTarget.innerText.replace("#", "");
    const newTags = [];
    tags.forEach((tag) => {
      if (tag !== deleteTag) {
        newTags.push(tag);
      }
    });
    setTags(newTags);
  };

  const handleQnaBookAddButtonOnClick = () => {
    // qnaBook add api 추가해야함
    //title, opened, tags
    postQnaBookApi({ opened, title, tags });
    navigate("/test");
  };

  const SwitchLabels = () => {
    return (
      <FormGroup>
        <FormControlLabel
          control={<Switch checked={opened} onChange={handleOpenedField} />}
          label={opened ? "public" : "private"}
        />
      </FormGroup>
    );
  };

  return (
    <Container>
      <Typography variant="h6" gutterBottom sx={{}}>
        Q&A Book 생성
      </Typography>
      <Grid container spacing={3}>
        <Grid
          item
          xs={12}
          sx={{ display: "flex", justifyContent: "space-between" }}
        >
          <SwitchLabels />
          <Button onClick={handleQnaBookAddButtonOnClick}>완료</Button>
        </Grid>
        <Grid item xs={12} md={12}>
          <TextField
            required
            label="title"
            fullWidth
            variant="standard"
            onChange={handleTitleField}
          />
        </Grid>
        <Grid item xs={12} md={12}>
          <TextField
            required
            label="tag"
            fullWidth
            variant="standard"
            value={tag}
            onChange={handleTagFieldChange}
            onKeyDown={handleTagFieldEnter}
          />
        </Grid>
        <Grid item xs={12} md={12}>
          {tags.map((tag) => (
            <Box key={tag}>
              <Button
                onClick={handleTagButton}
                style={{ textTransform: "none" }}
              >
                <Typography variant="p" component="p">
                  <Typography
                    variant="span"
                    component="span"
                    sx={{ color: "#6D73AD" }}
                  >
                    #
                  </Typography>
                  {tag}
                </Typography>
                <HighlightOffIcon sx={{ width: 15 }} />
              </Button>
            </Box>
          ))}
        </Grid>
      </Grid>
    </Container>
  );
}
