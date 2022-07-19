import * as React from "react";
import { Grid, Typography, Box, Paper, CardMedia } from "@mui/material";
import PropTypes from "prop-types";

function MainFeaturedPost(props) {
  const { post } = props;
  const descriptions = post.description.split("\n");
  return (
    <Paper
      sx={{
        position: "relative",
        backgroundColor: "#E3EFF6",
        color: "black",
        mb: 4,
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        display: { xs: "none", sm: "none", md: "block" },
      }}
    >
      <Grid container>
        <Grid item xs={8}>
          <Box
            sx={{
              position: "relative",
              p: { xs: 3, md: 6 },
              pr: { md: 0 },
            }}
          >
            <Typography
              component="h5"
              variant="h5"
              color="inherit"
              gutterBottom
            >
              {post.title}
            </Typography>
            {<br />}
            <div style={{ color: "#6D73AD" }}>
              {descriptions.map((description) => {
                return (
                  <Box key={description}>
                    {description}
                    {<br />}
                  </Box>
                );
              })}
            </div>
          </Box>
        </Grid>
        <Grid item xs={4}>
          <Box
            sx={{
              position: "relative",
              p: { xs: 3, md: 6 },
              pr: { md: 0 },
            }}
          >
            <CardMedia
              component="img"
              sx={{ width: 160 }}
              image={post.image}
              alt={post.imageLabel}
            />
          </Box>
        </Grid>
      </Grid>
    </Paper>
  );
}

MainFeaturedPost.propTypes = {
  post: PropTypes.shape({
    description: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
  }).isRequired,
};

export default MainFeaturedPost;
