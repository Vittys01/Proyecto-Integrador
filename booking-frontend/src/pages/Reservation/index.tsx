import { useContext, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Product } from '../../types/product';
import useFetch from '../../hooks/useFetch';
import ReservationDetail from '../../components/ReservationPage/ReservationDetail';
import ProductPolitics from '../../components/ProductPage/ProductPolitics';
import ReservationCalendar from '../../components/ReservationPage/ReservationCalendar';
import ReservationCheckin from '../../components/ReservationPage/ReservationCheckin';
import ReservationForm from '../../components/ReservationPage/ReservationForm';
import { UserContext } from '../../contexts/UserContext';
import ErrorMessage from '../../components/UI/ErrorMessage';
import { getDateFormatted } from '../../utils/date';
import './index.css';
import NavigationHeader from '../../components/UI/NavigationHeader';

const ReservationPage = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext);
  const { productId } = useParams();
  const [time, setTime] = useState('');
  const [dates, setDates] = useState({
    dateFrom: '',
    dateTo: ''
  });

  const { data: productData } = useFetch<Product>({
    initialUrl: `http://3.21.170.194:8080/api/rental/${productId}`
  });

  const {
    data: bookingData,
    refetch,
    hasError
  } = useFetch<Product>({
    withAuth: true,
    initialUrl: `http://3.21.170.194:8080/api/booking`,
    method: 'post',
    body: JSON.stringify({
      user_id: user?.id,
      rental_id: +(productId as string),
      check_in: dates.dateFrom,
      check_out: dates.dateTo,
      check_in_time: time
    }),
    onSuccess: () => navigate('/reservation-success'),
    onError: () =>
      document
        .querySelector('.layout-container')
        ?.scrollTo({ top: 0, left: 0, behavior: 'smooth' })
  });

  const handleDatesSelect = (start: any, end: any) => {
    if (end) {
      setDates({
        dateFrom: getDateFormatted(start),
        dateTo: getDateFormatted(end)
      });
    }
  };

  const handleConfirmReservation = () => {
    refetch();
  };

  return (
    <div className="reservation-page-container">
      {productData && (
        <>
          <NavigationHeader backTo={`/product/${productId}`}>
            <p className="product-header-category">
              {productData.category?.name}
            </p>
            <h3 className="product-header-name">{productData.name}</h3>
          </NavigationHeader>
          {hasError && (
            <div className="reservation-page-error container">
              <ErrorMessage text="Lamentablemente la reserva no ha podido realizarse. Por favor, intente más tarde" />
            </div>
          )}
          <div className="reservation-page-grid container">
            <ReservationForm className="reservation-page-form" />
            <ReservationCalendar
              className="reservation-page-calendar"
              onDatesSelect={handleDatesSelect}
              bookings={productData.booking_set}
            />
            <ReservationCheckin
              className="reservation-page-checkin"
              onSelect={(e: string) => setTime(e)}
            />
            <ReservationDetail
              className="reservation-page-detail"
              data={productData}
              onConfirmReservation={handleConfirmReservation}
              selectedDates={dates}
            />
          </div>
          <ProductPolitics fullWidth />
        </>
      )}
    </div>
  );
};

export default ReservationPage;
