import * as React from "react";
import {
  Avatar,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  DialogTitle,
  Dialog,
  Typography,
  Link,
} from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import { blue } from "@mui/material/colors";

const GOOGLE_LOGIN = "GOOGLE";
const KAKAO_LOGIN = "KAKAO";
const socialTypes = [GOOGLE_LOGIN, KAKAO_LOGIN];

function LoginModal(props) {
  const { onClose, selectedValue, open } = props;

  const handleClose = () => {
    onClose(selectedValue);
  };

  const handleSocialLogin = (socialType) => {
    window.localStorage.setItem("socialType", socialType);
  };

  const requestUri = {
    google: `https://accounts.google.com/o/oauth2/auth?client_id=${process.env.REACT_APP_GOOGLE_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_GOOGLE_LOGIN_REDIRECT_URL}&scope=profile&response_type=code`,
  };

  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle textAlign="center">
        <Typography component={"span"} variant="h5" align="center">
          LOGIN
        </Typography>
      </DialogTitle>
      <List sx={{ pt: 0 }}>
        {socialTypes.map((socialType) => (
          <Link href={requestUri.google} key={socialType}>
            <ListItem button onClick={() => handleSocialLogin(socialType)}>
              <ListItemAvatar>
                <Avatar sx={{ bgcolor: blue[100], color: blue[600] }}>
                  <PersonIcon />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary={socialType} />
            </ListItem>
          </Link>
        ))}
      </List>
    </Dialog>
  );
}

export default LoginModal;
