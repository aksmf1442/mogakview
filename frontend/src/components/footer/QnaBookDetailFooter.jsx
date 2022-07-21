import * as React from "react";
import Paper from "@mui/material/Paper";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";

export default function QnaBookDetailFooter() {
  return (
    <Paper
      sx={{
        position: "fixed",
        bottom: 0,
        left: 0,
        right: 0,
      }}
      elevation={3}
    >
      <BottomNavigation showLabels sx={{ backgroundColor: "#E3EFF6" }}>
        <BottomNavigationAction label="전체 선택" sx={{ color: "#6D73AD" }} />
        <BottomNavigationAction
          label="전체 선택 해제"
          sx={{ color: "#6D73AD" }}
        />
        <BottomNavigationAction
          label="선택한 문제 퀴즈로 풀기"
          sx={{ color: "#6D73AD" }}
        />
      </BottomNavigation>
    </Paper>
  );
}
