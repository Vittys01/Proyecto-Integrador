import { useContext, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../../contexts/UserContext';
import useMediaQuery from '../../../hooks/useMediaQuery';
import { Product } from '../../../types/product';
import Button from '../../UI/Button';
import Card from '../../UI/Card';
import DatePicker from '../../UI/DatePicker';
import './index.css';
interface Props {
  data: Product;
}

const ProductReservation = ({ data }: Props) => {
  const { user } = useContext(UserContext);
  const showOnlyOneMonth = useMediaQuery('(max-width: 768px)');
  const { id } = data;
  const navigate = useNavigate();

  const excludedDates = useMemo(
    () =>
      (data.booking_set || []).map(
        (booking: { check_in: any[]; check_out: any[] }) => ({
          start: new Date(booking.check_in.join('-')),
          end: new Date(booking.check_out.join('-'))
        })
      ),
    [data]
  );

  return (
    <div className="product-reservation-container">
      <div className="container">
        <h3>Fechas disponibles</h3>
        <div className="product-reservation-content">
          <DatePicker
            inline
            monthsShown={showOnlyOneMonth ? 1 : 2}
            className="product-reservation-calendar"
            excludeDateIntervals={excludedDates}
          />
          <Card className="product-reservation-button-container">
            <p>Agrega tus fechas de viaje para obtener precios exactos</p>
            <Button
              onClick={() =>
                user?.name
                  ? navigate(`/product/${id}/reservation`)
                  : navigate(`/login`, {
                      state: { fromSpecificPage: true, productId: id }
                    })
              }
              className="product-reservation-button"
              label="Iniciar reserva"
            />
          </Card>
        </div>
      </div>
    </div>
  );
};

export default ProductReservation;
