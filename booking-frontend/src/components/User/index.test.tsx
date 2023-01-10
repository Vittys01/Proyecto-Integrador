import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import User from '.';
import { UserContext } from '../../contexts/UserContext';
import { MOCKED_ADMIN, MOCKED_USER } from '../../utils/testsData';

const mockSetUser = jest.fn();

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('User Component', () => {
  it('should render correctly and remove token from localStorage on close button click', () => {
    jest.spyOn(Storage.prototype, 'removeItem');
    Storage.prototype.removeItem = jest.fn();

    render(
      <UserContext.Provider
        value={{
          user: MOCKED_USER,
          setUser: mockSetUser
        }}
      >
        <MemoryRouter>
          <User />
        </MemoryRouter>
      </UserContext.Provider>
    );

    expect(screen.getByText('Mica Feller')).toBeInTheDocument();

    const bookingLink = screen.getByText('Mis Reservas');
    expect(bookingLink).toBeInTheDocument();

    userEvent.click(bookingLink);
    expect(mockNavigate).toBeCalledWith('/myreservations');

    const close = screen.getByText('X');
    userEvent.click(close);

    expect(localStorage.removeItem).toHaveBeenCalled();
  });

  it('should render correctly if user is Admin', () => {
    jest.spyOn(Storage.prototype, 'removeItem');
    Storage.prototype.removeItem = jest.fn();

    render(
      <UserContext.Provider
        value={{
          user: MOCKED_ADMIN,
          setUser: mockSetUser
        }}
      >
        <MemoryRouter>
          <User />
        </MemoryRouter>
      </UserContext.Provider>
    );

    const adminLink = screen.getByText('Administración');
    expect(adminLink).toBeInTheDocument();

    userEvent.click(adminLink);
    expect(mockNavigate).toBeCalledWith('/administration');
  });
});
