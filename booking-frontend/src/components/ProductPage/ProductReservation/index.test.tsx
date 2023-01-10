import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import ProductReservation from '.';
import { UserContext } from '../../../contexts/UserContext';
import { EXAMPLE_PRODUCT, MOCKED_USER } from '../../../utils/testsData';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

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

describe('ProductReservation Component', () => {
  it('should render correctly', () => {
    render(
      <MemoryRouter>
        <ProductReservation data={EXAMPLE_PRODUCT} />
      </MemoryRouter>
    );

    expect(screen.getByText('Fechas disponibles')).toBeInTheDocument();
  });

  it('should navigate to home on back button click', () => {
    render(
      <MemoryRouter>
        <ProductReservation data={EXAMPLE_PRODUCT} />
      </MemoryRouter>
    );

    const reservationButton = screen.getByRole('button', {
      name: 'Iniciar reserva'
    });
    userEvent.click(reservationButton);
    expect(mockNavigate).toBeCalledWith('/login', {
      state: { fromSpecificPage: true, productId: EXAMPLE_PRODUCT.id }
    });
  });

  it('should navigate to reservation page if user is logged in', () => {
    render(
      <UserContext.Provider
        value={{
          user: MOCKED_USER
        }}
      >
        <MemoryRouter>
          <ProductReservation data={EXAMPLE_PRODUCT} />
        </MemoryRouter>
      </UserContext.Provider>
    );

    const reservationButton = screen.getByRole('button', {
      name: 'Iniciar reserva'
    });
    userEvent.click(reservationButton);
    expect(mockNavigate).toBeCalledWith(
      `/product/${EXAMPLE_PRODUCT.id}/reservation`
    );
  });
});
