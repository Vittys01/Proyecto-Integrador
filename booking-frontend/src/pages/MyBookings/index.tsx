import React from 'react';
import useFetch from '../../hooks/useFetch';

const MyBookingsPage = () => {
  const { data } = useFetch<any>({
    initialUrl: `http://3.21.170.194:8080/api/booking/user`,
    withAuth: true,
    initialParams: {
      page: 0,
      size: 10
    }
  });

  return <div>MyBookingsPage</div>;
};

export default MyBookingsPage;
