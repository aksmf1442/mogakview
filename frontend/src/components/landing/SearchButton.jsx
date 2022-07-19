import * as React from "react";
import { styled } from "@mui/material/styles";
import Button from "@mui/material/Button";

const ColorButton = styled(Button)(({ theme }) => ({
  color: "#7B7B7B",
  backgroundColor: "white",
  "&:hover": {
    backgroundColor: "#E4E4E4",
  },
  marginRight: "-10px",
  boxShadow: "none",
  border: "solid",
  borderColor: "#D9D9D9",
}));

export default function CustomizedButtons({ value }) {
  return <ColorButton variant="contained">{value}</ColorButton>;
}
