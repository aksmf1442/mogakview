import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import socialLogin from "../../api/auth/socialLogin";
import { SOCIAL_TYPE, TOKEN } from "../../utils/CommonValue";

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
      const accessToken = await socialLogin({ socialType, code });
      window.localStorage.setItem(TOKEN.ACCESS_TOKEN, accessToken);

      navigate(-1);
    } catch (error) {
      console.error(error);
    }
  };

  login();

  return null;
}

export default OauthCallbackPage;
