import { axiosInstance } from "../axiosInstance";

export default async function getUserInfoApi() {
  const config = {};

  await axiosInstance(config).post(`/api/auth/logout`);
}
