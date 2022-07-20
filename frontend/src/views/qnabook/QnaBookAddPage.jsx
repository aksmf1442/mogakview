import * as React from "react";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import { Container } from "@mui/system";
import FormGroup from "@mui/material/FormGroup";
import Switch from "@mui/material/Switch";
import Button from "@mui/material/Button";

export default function QnaBookAddPage() {
  const [title, setTitle] = React.useState("");
  const [isPublic, setIsPublic] = React.useState(true);
  const [tag, setTag] = React.useState("");
  const [tags, setTags] = React.useState([]);

  const handleIsPublicField = (event) => {
    setIsPublic(event.target.checked);
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

  const handleQnaBookAddButtonOnClick = () => {
    // qnaBook add api 추가해야함
  };

  const SwitchLabels = () => {
    return (
      <FormGroup>
        <FormControlLabel
          control={<Switch checked={isPublic} onChange={handleIsPublicField} />}
          label={isPublic ? "public" : "private"}
        />
      </FormGroup>
    );
  };

  return (
    <Container>
      <Typography variant="h6" gutterBottom sx={{}}>
        면접 질문 모음집 추가
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
            <Typography
              variant="p"
              component="p"
              sx={{ opacity: 0.6 }}
              key={tag}
            >
              <Typography
                variant="span"
                component="span"
                sx={{ opacity: 1, color: "#6D73AD" }}
              >
                #
              </Typography>
              {tag}
            </Typography>
          ))}
        </Grid>
      </Grid>
      <Button />
    </Container>
  );
}
