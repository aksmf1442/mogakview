import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import socialLoginApi from "../../api/auth/socialLoginApi";
import { SOCIAL_TYPE, TOKEN } from "../../utils/CommonValue";
import getUserInfoApi from "../../api/user/getUserInfoApi";

function OauthCallbackPage() {
  const navigate = useNavigate();
  const { search } = useLocation();
  const code = new URLSearchParams(search).get("code");

  const socialType =
    window.localStorage.getItem(SOCIAL_TYPE.TITLE) || JSON.stringify(null);

  if (!code & !socialType) {
    return null;
  }

  const login = async () => {
    try {
      const accessToken = await socialLoginApi({ socialType, code });
      window.localStorage.setItem(TOKEN.ACCESS_TOKEN, accessToken);
      // todo
      // accessToken으로 getUserInfoApi를 통해 값을 가져오고 리덕스 스토어에 넣기
      // const user = await getUserInfoApi("asdf");
      navigate(-1);
    } catch (error) {
      console.error(error);
    }
  };

  login();

  return null;
}

export default OauthCallbackPage;
