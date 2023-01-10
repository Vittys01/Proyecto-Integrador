import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import ProductDetail from '.';
import { EXAMPLE_PRODUCT } from '../../../utils/testsData';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

const mockedSelectedDates = {
  dateFrom: '',
  dateto: ''
};

describe('ProductDetail Component', () => {
  it('should render correctly', () => {
    render(
      <MemoryRouter>
        <ProductDetail
          data={EXAMPLE_PRODUCT}
          selectedDates={mockedSelectedDates}
        />
      </MemoryRouter>
    );

    expect(screen.getByText('Detalle de la reserva')).toBeInTheDocument();
  });

  /* it('should navigate to home on back button click', () => {
    render(
      <MemoryRouter>
        <ProductDetail
          data={EXAMPLE_PRODUCT}
          selectedDates={mockedSelectedDates}
        />
      </MemoryRouter>
    );

    const confirmButton = screen.getByRole('button', {
      name: 'Confirmar reserva'
    });
    userEvent.click(confirmButton);
    expect(mockNavigate).toBeCalledWith(`/reservation-success`);
  }); */
});
