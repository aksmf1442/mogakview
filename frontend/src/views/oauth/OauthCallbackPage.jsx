import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import socialLogin from "../../api/auth/socialLogin";

function OauthCallbackPage() {
  const navigate = useNavigate();
  const { search } = useLocation();
  const code = new URLSearchParams(search).get("code");
  // socialType 상수로 뺴줘야 함
  const socialType =
    window.localStorage.getItem("socialType") || JSON.stringify(null);

  if (!code) {
    return null;
  }

  const login = async () => {
    try {
      await socialLogin({ socialType, code });
      return null;
    } catch (error) {
      console.error(error);
    }
  };

  login();

  return null;
}

export default OauthCallbackPage;
