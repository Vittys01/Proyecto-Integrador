import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { UserContext } from '../../contexts/UserContext';
import Header from '.';
import { MOCKED_USER } from '../../utils/testsData';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('Header Component', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <Header />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(screen.getByText('Sentite como en tu hogar')).toBeInTheDocument();
    expect(
      screen.getByRole('button', { name: 'Crear cuenta' })
    ).toBeInTheDocument();
  });

  it('should navigate to home when clicking logo', () => {
    setup();

    const logo = screen.getByAltText('Digital Booking');
    userEvent.click(logo);
    expect(mockNavigate).toBeCalledWith('/');
  });

  it('should navigate to login when clicking login button', () => {
    setup();

    const loginButton = screen.getByRole('button', { name: 'Iniciar sesión' });
    userEvent.click(loginButton);
    expect(mockNavigate).toBeCalledWith('/login');
  });

  it('should navigate to register when clicking register button', () => {
    setup();

    const registerButton = screen.getByRole('button', { name: 'Crear cuenta' });
    userEvent.click(registerButton);
    expect(mockNavigate).toBeCalledWith('/register');
  });

  it('should show user name if user is logged in', () => {
    render(
      <UserContext.Provider
        value={{
          user: MOCKED_USER
        }}
      >
        <MemoryRouter>
          <Header />
        </MemoryRouter>
      </UserContext.Provider>
    );

    const userButton = screen.getByText('Mica Feller');
    expect(userButton).toBeInTheDocument();
  });
});
