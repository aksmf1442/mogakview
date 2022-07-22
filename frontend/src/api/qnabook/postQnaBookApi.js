import { axiosInstance } from "../axiosInstance";
import { TOKEN } from "../../utils/CommonValue";

export default async function postQnaBookApi({ opened, title, tags }) {
  const accessToken = window.localStorage.getItem(TOKEN.ACCESS_TOKEN);
  if (!accessToken) {
    //return 시킬 예정
  }
  console.log(accessToken);
  const config = {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };

  const body = {
    opened,
    title,
    tags,
  };

  const qnaBook = await axiosInstance(config)
    .post(`/api/qna-books`, body)
    .then((res) => res.data);
  console.log(qnaBook);
  return qnaBook;
}
