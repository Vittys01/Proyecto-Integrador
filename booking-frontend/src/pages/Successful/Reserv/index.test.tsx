import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Successful from '.';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('Successful Component', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <Successful />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(
      screen.getByText('Su reserva se ha realizado con éxito')
    ).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Ok' })).toBeInTheDocument();
  });

  it('should navigate to home when clicking button', () => {
    setup();

    const button = screen.getByRole('button', { name: 'Ok' });
    userEvent.click(button);
    expect(mockNavigate).toBeCalledWith('/');
  });
});
