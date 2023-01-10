import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SuccessfulProduct from '.';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('SuccessfulProduct Component', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <SuccessfulProduct />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(
      screen.getByText('Tu propiedad se ha creado con éxito.')
    ).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Volver' })).toBeInTheDocument();
  });

  it('should navigate to home when clicking button', () => {
    setup();

    const button = screen.getByRole('button', { name: 'Volver' });
    userEvent.click(button);
    expect(mockNavigate).toBeCalledWith('/');
  });
});
