import { useMemo } from 'react';
import useMediaQuery from '../../../hooks/useMediaQuery';
import DatePicker from '../../UI/DatePicker';
import './index.css';

const ReservationCalendar = ({ className, onDatesSelect, bookings }: any) => {
  const showOnlyOneMonth = useMediaQuery('(max-width: 790px)');

  const excludedDates = useMemo(
    () =>
      (bookings || []).map(
        (booking: { check_in: any[]; check_out: any[] }) => ({
          start: new Date(booking.check_in.join('-')),
          end: new Date(booking.check_out.join('-'))
        })
      ),
    [bookings]
  );

  return (
    <div className={className}>
      <h3>Seleccioná tu fecha de reserva</h3>
      <div className="calendar-reservation-content">
        <DatePicker
          inline
          onChange={(start: any, end: any) => onDatesSelect(start, end)}
          monthsShown={showOnlyOneMonth ? 1 : 2}
          className="reservation-calendar-picker"
          excludeDateIntervals={excludedDates}
        />
      </div>
    </div>
  );
};

export default ReservationCalendar;
