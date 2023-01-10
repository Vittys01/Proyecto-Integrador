import { forwardRef, useState } from 'react';
import ReactDatePicker, { registerLocale } from 'react-datepicker';
import es from 'date-fns/locale/es';
import 'react-datepicker/dist/react-datepicker.css';
import './index.css';
import { RiCalendarEventFill as CalendarIcon } from 'react-icons/ri';
import Button from '../Button';

registerLocale('es', es);

const DatePicker = ({ onChange, className, withButton, ...props }: any) => {
  const [isOpen, setIsOpen] = useState(false);
  const [areDatesSetted, setAreDatesSetted] = useState(false);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  const handleChange = (dates: any) => {
    setAreDatesSetted(false);
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
    if (!withButton) {
      onChange(start, end);
    } else {
      if (start === null) onChange(null, null);
    }
  };

  const handleClickOutside = () => {
    if (!areDatesSetted && withButton) {
      setStartDate(null);
      setEndDate(null);
    }
    setIsOpen(false);
  };

  const handleClick = () => {
    setAreDatesSetted(true);
    onChange(startDate, endDate);
    setIsOpen(false);
  };

  const DatePickerContainer = ({ className, children }: any) => {
    return (
      <div className={className}>
        <div style={{ position: 'relative' }}>{children}</div>
        <Button label="Aplicar" onClick={handleClick} disabled={!endDate} />
      </div>
    );
  };

  const CustomInput = forwardRef<any, any>(
    ({ onClick, value, placeholder, onChange }, ref) => {
      return (
        <div onClick={onClick}>
          <CalendarIcon
            fontSize={24}
            color={startDate ? 'var(--text-primary)' : 'var(--grey)'}
          />
          <input
            type="text"
            value={value}
            placeholder={placeholder}
            onChange={handleChange}
          />
        </div>
      );
    }
  );

  return (
    <div className={className}>
      <ReactDatePicker
        selected={startDate}
        onChange={handleChange}
        startDate={startDate}
        endDate={endDate}
        selectsRange
        customInput={<CustomInput />}
        placeholderText="Check in - Check out"
        dateFormatCalendar="LLLL"
        formatWeekDay={dayName => dayName.slice(0, 1).toUpperCase()}
        locale="es"
        calendarStartDay={0}
        isClearable
        minDate={new Date()}
        shouldCloseOnSelect={false}
        calendarContainer={withButton ? DatePickerContainer : null}
        onClickOutside={handleClickOutside}
        onInputClick={() => setIsOpen(true)}
        open={isOpen}
        {...props}
      />
    </div>
  );
};

export default DatePicker;
