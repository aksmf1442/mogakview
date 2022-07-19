import * as React from "react";
import Stack from "@mui/material/Stack";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import Typography from "@mui/material/Typography";
import { SearchButton, SearchSelect } from "../landing";

export default function ToggleButtonNotEmpty() {
  const [alignment, setAlignment] = React.useState("left");

  const handleAlignment = (event, newAlignment) => {
    if (newAlignment !== null) {
      setAlignment(newAlignment);
    }
  };

  return (
    <Stack direction="row" spacing={4} sx={{ height: 40, margin: 2.4 }}>
      <SearchButton value={"전체글"} />
      <SearchSelect />
      <ToggleButtonGroup value={alignment} exclusive onChange={handleAlignment}>
        <ToggleButton value="left" aria-label="left aligned">
          <Typography component="p" variant="p" color="inherit" gutterBottom>
            최신순
          </Typography>
        </ToggleButton>
        <ToggleButton value="center" aria-label="centered">
          <Typography component="p" variant="p" color="inherit" gutterBottom>
            인기순
          </Typography>
        </ToggleButton>
      </ToggleButtonGroup>
    </Stack>
  );
}
