import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { Box, Typography } from "@mui/material";
import AccountCircle from "@mui/icons-material/AccountCircle";
import getUserInfoApi from "../../api/user/getUserInfoApi";
import putUserInfoApi from "../../api/user/putUserInfoApi";

export default function ProfileModal() {
  const [open, setOpen] = React.useState(false);
  const [imageSrc, setImageSrc] = React.useState("");

  const encodeFileToBase64 = (fileBlob) => {
    const reader = new FileReader();
    reader.readAsDataURL(fileBlob);
    return new Promise((resolve) => {
      reader.onload = () => {
        setImageSrc(reader.result);
        resolve();
      };
    });
  };

  const handleClickOpen = () => {
    // todo
    // accessToken localStorage에서 꺼내서 null이 아니면 일단 넘기고 유효하다면 바꿈
    // accessToken으로 getUserInfoApi를 통해 값을 가져오고 리덕스 스토어에 넣기
    // const user = await getUserInfoApi("asdf");
    setOpen(true);
  };

  const handleSaveButton = () => {
    // todo
    // multipart/form-data 어떤식으로 보내줘야하는지 알아보기
    // putUserInfoApi();
    handleClose();
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Box onClick={handleClickOpen}>프로필</Box>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>프로필 수정</DialogTitle>
        <DialogContent>
          <DialogContentText>대표 이미지</DialogContentText>
          <Typography sx={{ opacity: "0.3" }}>
            이미지를 클릭 시 대표 이미지 수정 가능
          </Typography>
          <label for="input-file">
            <Button
              type="file"
              color="primary"
              component="file"
              variant="file"
              fullWidth
            >
              <Box className="preview">
                {imageSrc ? (
                  <img
                    src={imageSrc}
                    alt="preview-img"
                    style={{ width: "20vw", height: "20vw" }}
                  />
                ) : (
                  <AccountCircle sx={{ width: "20vw", height: "20vw" }} />
                )}
              </Box>
            </Button>
          </label>
          <input
            type="file"
            id="input-file"
            style={{ display: "none" }}
            onChange={(e) => {
              encodeFileToBase64(e.target.files[0]);
            }}
          />
        </DialogContent>
        <DialogContent>
          <DialogContentText>닉네임</DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            type="name"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleSaveButton}>저장</Button>
          <Button onClick={handleClose}>나가기</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
