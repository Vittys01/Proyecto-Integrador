import Card from '../../UI/Card';
import './index.css';
import { MdOutlineCheckCircleOutline as CheckIcon } from 'react-icons/md';
import { CHECK_IN_TIMES } from '../../../constants/checkInTimes';
import Select from '../../UI/Select';

const ReservationCheckin = ({ className, onSelect }: any) => {
  return (
    <div className={className}>
      <h3>Tu horário de llegada</h3>

      <Card className="reservation-checkin-card">
        <div className="reservation-checkin-content-header">
          <CheckIcon fontSize={24} />
          <p>
            Tu habitacion va a estar lista para el check-in entre las 10:00 AM y
            las 03:00 PM
          </p>
        </div>
        <p className="text-select">Indicá tu horario estimado de llegada</p>
        <div className="reservation-checkin-select">
          <Select
            name="checkin-time"
            options={CHECK_IN_TIMES}
            placeholder="Selecciona el horario"
            onChange={(e: any) => onSelect(e.value)}
          />
        </div>
      </Card>
    </div>
  );
};

export default ReservationCheckin;
