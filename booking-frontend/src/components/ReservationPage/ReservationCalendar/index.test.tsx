import { render, screen } from '@testing-library/react';
import ReservationCalendar from '.';

Object.defineProperty(window, 'matchMedia', {
  writable: true,
  value: jest.fn().mockImplementation(query => ({
    matches: false,
    media: '(min-width: 769px)',
    onchange: null,
    addListener: jest.fn(), // deprecated
    removeListener: jest.fn(), // deprecated
    addEventListener: jest.fn(),
    removeEventListener: jest.fn(),
    dispatchEvent: jest.fn()
  }))
});

describe('ReservationCalendar Component', () => {
  it('should render correctly', () => {
    render(<ReservationCalendar />);

    expect(
      screen.getByText('Seleccioná tu fecha de reserva')
    ).toBeInTheDocument();
  });
});
