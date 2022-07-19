import * as React from "react";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import QnaBook from "./QnaBook";

export default function QnaBooks() {
  const tags = ["tag1", "tag2", "tag3", "tag4", "tag5"];
  return (
    <Box sx={{ flexGrow: 1, marginBottom: 20 }}>
      <Grid container spacing={1}>
        <Grid item xs={4}>
          <QnaBook title={"test"} heartCount={3} tags={tags} />
        </Grid>
        <Grid item xs={4}>
          <QnaBook title={"test"} heartCount={3} tags={tags} />
        </Grid>
        <Grid item xs={4}>
          <QnaBook title={"test"} heartCount={3} tags={tags} />
        </Grid>
        <Grid item xs={4}>
          <QnaBook title={"test"} heartCount={3} tags={tags} />
        </Grid>
      </Grid>
    </Box>
  );
}
