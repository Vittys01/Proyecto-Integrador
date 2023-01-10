import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import ReservationCheckin from '.';
import { CHECK_IN_TIMES } from '../../../constants/checkInTimes';

const mockedOnSelect = jest.fn();

describe('ReservationCheckin Component', () => {
  it('should render correctly', () => {
    render(<ReservationCheckin />);

    expect(screen.getByText('Tu horário de llegada')).toBeInTheDocument();
  });

  it('should set the value selected', async () => {
    render(<ReservationCheckin onSelect={mockedOnSelect} />);

    const select = screen.getByText('Selecciona el horario');
    userEvent.click(select);

    const time = screen.getByText(CHECK_IN_TIMES[0].label);
    userEvent.click(time);

    const selectedType = await screen.findByText(CHECK_IN_TIMES[0].label);
    expect(selectedType).toBeInTheDocument();
  });
});
