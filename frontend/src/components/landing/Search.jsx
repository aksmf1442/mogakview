import React from "react";
import { Grid, Container } from "@mui/material";
import { SearchOption } from "../landing/index";

function Search() {
  return (
    <Container>
      <Grid container spacing={2}>
        <Grid item={true} xs={2}></Grid>
        <SearchOption />
        <Grid item={true} xs={2}></Grid>
      </Grid>
    </Container>
  );
}

export default Search;
