import axios from "axios";

export function axiosInstance(config) {
  const axiosInstance = axios.create({
    // timeout: 20 * 1000,
    ...config,
    headers: {
      "Content-Type": "application/json",
      ...config.headers,
    },
  });
  return axiosInstance;
}
