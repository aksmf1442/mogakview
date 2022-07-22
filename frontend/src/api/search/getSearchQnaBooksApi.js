import { axiosInstance } from "../axiosInstance";

export default async function getSearchQnaBooksApi({
  sort,
  where,
  query,
  qnaBookId,
}) {
  const config = {};
  const limit = 15;
  const queryString = `limit=${limit}&sort=${sort}&where=${where}&query=${query}&qnaBookId=${qnaBookId}`;
  const qnaBooks = await axiosInstance(config)
    .get(`/api/search/qna-books?${queryString}`)
    .then((res) => res.data.qnaBooks);

  return qnaBooks;
}
