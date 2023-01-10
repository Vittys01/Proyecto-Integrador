import Button from '../../UI/Button';
import Card from '../../UI/Card';
import { MdLocationOn as LocationIcon } from 'react-icons/md';
import { getSingularCategory } from '../../../utils/text';
import NoPhoto from '../../../assets/images/no-photo.png';
import './index.css';

const ReservationDetail = ({
  className,
  data,
  selectedDates,
  onConfirmReservation,
  readOnly
}: {
  className?: string;
  data: any;
  selectedDates?: any;
  onConfirmReservation?: () => void;
  readOnly?: boolean;
}) => {
  return (
    <div className={className}>
      {!readOnly && <h3>Detalle de la reserva </h3>}
      <Card className="reservation-detail-card">
        <div className="reservation-detail-image">
          <img
            src={data?.image_list ? data?.image_list[0]?.url : NoPhoto}
            alt={data?.name}
          />
        </div>
        <div className="reservation-detail-content">
          <p>{getSingularCategory(data?.category.name)}</p>
          <h3>{data?.name}</h3>
          <div className="reservation-detail-country">
            <LocationIcon fontSize={24} />
            <p>
              {data?.city.name}, {data?.city.province.country.name}
            </p>
          </div>
          <div className="detail-checkin-container">
            <hr />
            <div>
              <h4>Check-in</h4> <p>{selectedDates.dateFrom || ''}</p>
            </div>
            <hr />
            <div>
              <h4>Check-out</h4> <p>{selectedDates.dateTo || ''}</p>
            </div>
            <hr />
          </div>
          {!readOnly && (
            <Button
              onClick={onConfirmReservation}
              className="reservation-detail-button"
              label="Confirmar reserva"
              disabled={!selectedDates.dateTo}
            />
          )}
        </div>
      </Card>
    </div>
  );
};

export default ReservationDetail;
