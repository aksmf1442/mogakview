import * as React from "react";
import { useNavigate } from "react-router-dom";
import {
  AppBar,
  Box,
  Toolbar,
  IconButton,
  Typography,
  MenuItem,
  Menu,
  Button,
} from "@mui/material";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MoreIcon from "@mui/icons-material/MoreVert";
import { LoginModal, ProfileModal } from "../index";

const profile = "프로필";
const login = "로그인";
const pages = ["면접준비", "퀴즈준비", "새글쓰기"];
const pageNav = ["/", "/quizSetting", "/qnabookAdd"];

function Header() {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);
  const [isLogin, setIsLogin] = React.useState(false);

  const [loginOpen, setLoginOpen] = React.useState(false);
  const [profileOpen, setProfileOpen] = React.useState(false);
  // todo: 프로필에 나타낼 이미지 추가해야 함(백엔드에 아직 안만듬)
  const [userInfo, setUserInfo] = React.useState({
    userId: null,
    userName: "",
    userThumbnailURL: null,
  });

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
    // todo: api 추가해야 함(백엔드에서 이와 관련된 것 만든 후에)
    // getProfileInfo();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const handleLoginOpen = () => {
    setLoginOpen(true);
  };

  const handleLoginClose = () => {
    setLoginOpen(false);
  };

  const handleOnClickNavMenu = (event) => {
    const value = event.target.innerText;
    pages.forEach((page, idx) => {
      if (page === value) {
        navigate(pageNav[idx]);
      }
    });
    if (value === "모각뷰") {
      navigate("/");
    }
  };

  const handleProfileEdit = () => {
    setProfileOpen(true);
    handleMenuClose();
  };

  const handleProfileClose = () => {
    setProfileOpen(false);
  };

  const handleLogout = () => {
    handleMenuClose();
  };

  const menuId = "primary-search-account-menu";
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem onClick={handleProfileEdit}>
        <ProfileModal open={profileOpen} onClose={handleProfileClose} />
      </MenuItem>
      <MenuItem onClick={handleLogout}>로그아웃</MenuItem>
    </Menu>
  );
  const mobileMenuId = "primary-search-account-menu-mobile";
  const renderMobileMenu = (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >
      {isLogin && (
        <MenuItem>
          <Button
            // onClick={handleCloseNavMenu}
            size="large"
            aria-label="account of current user"
            aria-controls="primary-search-account-menu"
            aria-haspopup="true"
            color="inherit"
            sx={{ color: "#6D73AD" }}
          >
            {profile}
          </Button>
        </MenuItem>
      )}
      {!isLogin && (
        <MenuItem>
          <Button
            onClick={handleLoginOpen}
            size="large"
            aria-label="account of current user"
            aria-controls="primary-search-account-menu"
            aria-haspopup="true"
            color="inherit"
            sx={{ color: "#6D73AD" }}
          >
            {login}
          </Button>
        </MenuItem>
      )}
      {pages.map((page) => (
        <MenuItem key={page}>
          <Button
            // onClick={handleCloseNavMenu}
            size="large"
            aria-label="account of current user"
            aria-controls="primary-search-account-menu"
            aria-haspopup="true"
            color="inherit"
            sx={{ color: "#6D73AD" }}
          >
            {page}
          </Button>
        </MenuItem>
      ))}
      {isLogin && (
        <MenuItem>
          <Button
            // onClick={handleCloseNavMenu}
            size="large"
            aria-label="account of current user"
            aria-controls="primary-search-account-menu"
            aria-haspopup="true"
            color="inherit"
            sx={{ color: "#6D73AD" }}
          >
            로그아웃
          </Button>
        </MenuItem>
      )}
    </Menu>
  );

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar
        position="static"
        sx={{ backgroundColor: "#FFFFFF", color: "#1B1C3A" }}
      >
        <Toolbar>
          <Button
            onClick={handleOnClickNavMenu}
            size="large"
            aria-label="account of current user"
            aria-controls="primary-search-account-menu"
            aria-haspopup="true"
            color="inherit"
          >
            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              모각뷰
            </Typography>
            <Typography
              variant="p"
              noWrap
              component="div"
              sx={{ display: { xs: "block", sm: "none" } }}
            >
              모각뷰
            </Typography>
          </Button>
          <Box sx={{ flexGrow: 1 }} />
          <Box sx={{ display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => (
              <Button
                key={page}
                onClick={handleOnClickNavMenu}
                sx={{ my: 2, color: "#6D73AD", display: "block" }}
              >
                {page}
              </Button>
            ))}
            {isLogin && (
              <Button onClick={handleProfileMenuOpen}>
                <AccountCircle />
              </Button>
            )}
            {!isLogin && (
              <Button
                onClick={handleLoginOpen}
                sx={{ my: 2, color: "#6D73AD", display: "block" }}
              >
                {login}
              </Button>
            )}
          </Box>
          <Box sx={{ display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-label="show more"
              aria-controls={mobileMenuId}
              aria-haspopup="true"
              onClick={handleMobileMenuOpen}
              color="inherit"
            >
              <MoreIcon />
            </IconButton>
          </Box>
        </Toolbar>
      </AppBar>
      <LoginModal open={loginOpen} onClose={handleLoginClose} />
      {/* <ProfileModal open={profileOpen} onClose={handleProfileClose} /> */}
      {renderMobileMenu}
      {renderMenu}
    </Box>
  );
}

export default Header;
