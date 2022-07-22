import { axiosInstance } from "../axiosInstance";

export default async function putUserInfoApi({ thumbnailUrl, nickname }) {
  const accessToken = "";
  if (!accessToken) {
    //return 시킬 예정
  }
  // todo
  // 대대적으로 수정해야할 지도
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const body = {
    // thumbnailUrl,
    // nickname
  };

  const userInfo = await axiosInstance(config)
    .put(`/api/users/me`)
    .then((res) => res.data);

  return userInfo;
}
