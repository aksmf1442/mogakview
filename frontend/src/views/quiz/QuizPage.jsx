import React from "react";
import Box from "@mui/material/Box";

function QuizPage() {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "80vh",
      }}
    >
      <Box sx={{ width: 300, height: 300, backgroundColor: "black" }}></Box>
    </Box>
  );
}

export default QuizPage;
