import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import { CardActionArea, CardActions } from "@mui/material";
import FavoriteBorderSharpIcon from "@mui/icons-material/FavoriteBorderSharp";
import Tags from "../../components/tag/Tags";

export default function QnaBook({ title, tags, heartCount }) {
  return (
    <Card sx={{ maxWidth: 240, marginLeft: 7 }}>
      <CardActionArea>
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {title}
          </Typography>
          <Typography component="div" variant="body2" color="text.secondary">
            <Tags tags={tags} />
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
