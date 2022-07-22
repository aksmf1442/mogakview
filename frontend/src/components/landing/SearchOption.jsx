import * as React from "react";
import Stack from "@mui/material/Stack";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import Typography from "@mui/material/Typography";
import FormControl from "@mui/material/FormControl";
import NativeSelect from "@mui/material/NativeSelect";
import { styled, alpha } from "@mui/material/styles";
import SearchIcon from "@mui/icons-material/Search";
import { InputBase, Box, Button } from "@mui/material";
import getSearchQnaBooksApi from "../../api/search/getSearchQnaBooksApi";

const BootstrapInput = styled(InputBase)(({ theme }) => ({
  "label + &": {
    marginTop: theme.spacing(3),
  },
  "& .MuiInputBase-input": {
    borderRadius: 4,
    // position: "relative",
    backgroundColor: theme.palette.background.paper,
    border: "1px solid #ced4da",
    fontSize: 13.5,
    padding: "9px 12px 10px 12px",
    transition: theme.transitions.create(["border-color", "box-shadow"]),
    // Use the system font instead of the default Roboto font.
    fontFamily: [
      "-apple-system",
      "BlinkMacSystemFont",
      '"Segoe UI"',
      "Roboto",
      '"Helvetica Neue"',
      "Arial",
      "sans-serif",
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(","),
    "&:focus": {
      borderRadius: 4,
      borderColor: "#80bdff",
      boxShadow: "0 0 0 0.2rem rgba(0,123,255,.25)",
    },
  },
}));

const SearchStyled = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(3.3, 1, 1, 0),

    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: "20ch",
    },
  },
}));

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

const latest = "latest";
const populate = "populate";

const title = "title";
const tag = "tag";
const author = "author";

export default function SearchOption() {
  const [sort, setSort] = React.useState(latest);
  const [where, setWhere] = React.useState(title);
  const [query, setQuery] = React.useState("");
  const [resultQuery, setResultQuery] = React.useState("");
  const [qnaBookId, setQnaBookId] = React.useState("");

  const getQnaBooks = async () => {
    const qnaBooks = await getSearchQnaBooksApi({
      sort,
      where,
      query: resultQuery,
      qnaBookId,
    });
    // qnaBooks를 리덕스로 빼기
    // 백엔드에서 API 만든 후에 적용하자.
  };

  const handleInitialButtonClick = (e) => {
    setSort(latest);
    setWhere(title);
    setResultQuery("");
    setQuery("");
    setQnaBookId("");
  };

  const handleSearchFieldChange = (e) => {
    setQuery(e.currentTarget.value);
  };

  const handleSearchFieldEnter = (e) => {
    if (e.keyCode === 13) {
      setResultQuery(query);
    }
  };

  const handleWhereChange = (e) => {
    setWhere(e.target.value);
  };

  const handleSortChange = (e) => {
    setSort(e.currentTarget.value);
  };

  React.useEffect(() => {
    getQnaBooks();
  }, [sort, where, resultQuery]);

  const searchSelect = (
    <FormControl sx={{ m: 1 }} variant="standard">
      <NativeSelect
        id="demo-customized-select-native"
        value={where}
        onChange={handleWhereChange}
        input={<BootstrapInput />}
      >
        <option value={title}>제목</option>
        <option value={tag}>태그</option>
        <option value={author}>작성자</option>
      </NativeSelect>
    </FormControl>
  );

  const searchQuery = (
    <SearchStyled>
      <SearchIconWrapper>
        <SearchIcon />
      </SearchIconWrapper>
      <StyledInputBase
        placeholder="Search…"
        inputProps={{ "aria-label": "search" }}
        value={query}
        onChange={handleSearchFieldChange}
        onKeyDown={handleSearchFieldEnter}
      />
    </SearchStyled>
  );

  const allButton = (
    <ColorButton variant="contained" onClick={handleInitialButtonClick}>
      초기화
    </ColorButton>
  );

  return (
    <Box sx={{ display: "flex" }}>
      <Stack direction="row" spacing={4} sx={{ height: 40, margin: 2.4 }}>
        {allButton}
        {searchSelect}
        <ToggleButtonGroup value={sort} exclusive onChange={handleSortChange}>
          <ToggleButton value={latest} aria-label="left aligned">
            <Typography component="p" variant="p" color="inherit" gutterBottom>
              최신순
            </Typography>
          </ToggleButton>
          <ToggleButton value={populate} aria-label="centered">
            <Typography component="p" variant="p" color="inherit" gutterBottom>
              인기순
            </Typography>
          </ToggleButton>
        </ToggleButtonGroup>
      </Stack>
      {searchQuery}
    </Box>
  );
}
