import { axiosInstance } from "../axiosInstance";

export default async function socialLogin({ socialType, code }) {
  const config = {};

  const accessToken = await axiosInstance(config)
    .post(`/api/auth/login/${socialType}`, {
      code,
    })
    .then((res) => res.data);

  return accessToken.token;
}
