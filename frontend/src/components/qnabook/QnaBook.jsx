import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { Button, CardActionArea, CardActions } from "@mui/material";
import FavoriteBorderSharpIcon from "@mui/icons-material/FavoriteBorderSharp";
import Tags from "../../components/tag/Tags";

export default function QnaBook({ title, tags, heartCount }) {
  return (
    <Card sx={{ maxWidth: 240, marginLeft: 7 }}>
      <CardActionArea>
        <CardMedia component="div" height="140" />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {title}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            <Tags tags={tags}></Tags>
          </Typography>
        </CardContent>
        <CardActions>
          <FavoriteBorderSharpIcon sx={{ color: "red" }} />
          {heartCount}
        </CardActions>
      </CardActionArea>
    </Card>
  );
}
