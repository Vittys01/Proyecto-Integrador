import { useState, useEffect } from 'react';

interface RequestConfig extends RequestInit {
  initialUrl: string;
  initialParams?: Record<string, string | number>;
  skip?: boolean;
  onSuccess?: (data: any) => void;
  onError?: () => void;
  withAuth?: boolean;
  method?: 'get' | 'post' | 'patch';
}

const useFetch = <T>({
  initialUrl,
  initialParams = {},
  skip = false,
  method = 'get',
  body,
  withAuth = false,
  onSuccess,
  onError
}: RequestConfig) => {
  const [url, updateUrl] = useState(initialUrl);
  const [params, updateParams] = useState(initialParams);
  const [data, setData] = useState<T | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [hasError, setHasError] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [refetchIndex, setRefetchIndex] = useState(0);

  const queryString = Object.keys(params)
    .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
    .join('&');

  const refetch = () =>
    setRefetchIndex(prevRefetchIndex => prevRefetchIndex + 1);

  useEffect(() => {
    const fetchData = async () => {
      if ((method === 'post' && refetchIndex === 0) || skip) return;
      setHasError(false);
      setIsLoading(true);
      try {
        const response = await fetch(`${url}?${queryString}`, {
          headers: new Headers({
            'Content-Type': 'application/json',
            Authorization: withAuth
              ? `Bearer ${localStorage.getItem('token') || ''}`
              : ''
          }),
          method,
          body
        });
        const result = await response.json();
        if (response.ok) {
          setHasError(false);
          setData(result.body);
          onSuccess && onSuccess(result.body);
        } else {
          setHasError(true);
          setErrorMessage(result.body.error_code);
        }
      } catch (err: any) {
        setHasError(true);
        setErrorMessage(err.message);
        onError && onError();
      } finally {
        setIsLoading(false);
      }
    };
    fetchData();
  }, [url, params, refetchIndex, skip, queryString, method, body]);

  return {
    data,
    isLoading,
    hasError,
    errorMessage,
    updateUrl,
    updateParams,
    refetch
  };
};

export default useFetch;
