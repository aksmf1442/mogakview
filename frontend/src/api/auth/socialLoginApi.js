import { axiosInstance } from "../axiosInstance";

export default async function socialLogin({ socialType, code }) {
  const config = {};

  const body = {
    code,
  };

  const accessToken = await axiosInstance(config)
    .post(`/api/auth/login/${socialType}`, body)
    .then((res) => res.data);

  return accessToken.token;
}
