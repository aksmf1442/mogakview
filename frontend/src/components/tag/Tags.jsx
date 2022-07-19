import * as React from "react";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

export default function Tags({ tags }) {
  return (
    <>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={1}>
          {tags.map((tag) => (
            <Grid item xs={4} key={tag}>
              #{tag}
            </Grid>
          ))}
        </Grid>
      </Box>
    </>
  );
}
