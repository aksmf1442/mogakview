import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import { CardActions } from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import Tags from "../../components/tag/Tags";

export default function SelectQnaBook({ title, tags, heartCount }) {
  return (
    <Card>
      <CardContent style={{ textTransform: "none" }}>
        <Typography gutterBottom variant="h5" component="div">
          {title}
        </Typography>
        <Typography component="div" variant="body2" color="text.secondary">
          <Tags tags={tags} />
        </Typography>
      </CardContent>
      <CardActions>
        <FavoriteIcon sx={{ color: "red" }} />
        {heartCount}
      </CardActions>
    </Card>
  );
}
