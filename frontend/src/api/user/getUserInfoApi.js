import { axiosInstance } from "../axiosInstance";

export default async function getUserInfoApi() {
  const accessToken = "";
  if (!accessToken) {
    //return 시킬 예정
  }

  const config = {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const userInfo = await axiosInstance(config)
    .get(`/api/users/me`)
    .then((res) => res.data);

  return userInfo;
}
