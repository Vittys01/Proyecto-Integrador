import useFetch from '../../hooks/useFetch';
import { useContext, useMemo } from 'react';
import { UserContext } from '../../contexts/UserContext';
import EmpityReserv from '../../components/MyReservationsPage/EmptyReserv';
import ReservationDetail from '../../components/ReservationPage/ReservationDetail';
import NavigationHeader from '../../components/UI/NavigationHeader';
import './index.css';

const MyReservations = () => {
  const { user } = useContext(UserContext);

  const { data: bookingsData } = useFetch<any>({
    skip: !user?.id,
    initialUrl: `http://3.21.170.194:8080/api/booking/user`,
    withAuth: true,
    initialParams: {
      page: 0,
      size: 10
    }
  });

  const bookings = useMemo(() => {
    return (bookingsData?.content || []).map((booking: any) => ({
      name: booking.rental.name,
      image_list: booking.rental.image_list,
      category: booking.rental.category,
      city: booking.rental.city,
      check_in: [...booking.check_in].reverse(),
      check_out: [...booking.check_out].reverse()
    }));
  }, [bookingsData]);

  return (
    <div className="my-reservations-container">
      <NavigationHeader>
        <h2 style={{ margin: 0 }}>Mis Reservas</h2>
      </NavigationHeader>
      <div className="container">
        {bookingsData?.content && bookingsData?.content?.length > 0 ? (
          <div className="bookings-grid">
            {bookings.map((booking: any) => (
              <ReservationDetail
                data={booking}
                selectedDates={{
                  dateFrom: booking.check_in.join('/'),
                  dateTo: booking.check_out.join('/')
                }}
                readOnly
                key={booking.id}
              ></ReservationDetail>
            ))}
          </div>
        ) : (
          <EmpityReserv />
        )}
      </div>
    </div>
  );
};

export default MyReservations;
