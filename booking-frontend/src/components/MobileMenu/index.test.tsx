import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import MobileMenu from '.';
import { UserContext } from '../../contexts/UserContext';
import { MOCKED_USER } from '../../utils/testsData';
import userEvent from '@testing-library/user-event';

const mockedSetUser = jest.fn();

describe('MobileMenu Component', () => {
  it('should render correctly', () => {
    render(
      <MemoryRouter>
        <MobileMenu onClose={() => null} isOpen />
      </MemoryRouter>
    );

    expect(screen.getByText('MENÚ')).toBeInTheDocument();
  });

  it('should logout on click', () => {
    jest.spyOn(Storage.prototype, 'removeItem');
    Storage.prototype.removeItem = jest.fn();

    render(
      <UserContext.Provider
        value={{
          user: MOCKED_USER,
          setUser: mockedSetUser
        }}
      >
        <MemoryRouter>
          <MobileMenu onClose={() => null} isOpen />
        </MemoryRouter>
      </UserContext.Provider>
    );

    const logoutLink = screen.getByRole('link', { name: 'cerrar sesión' });
    userEvent.click(logoutLink);
    expect(localStorage.removeItem).toHaveBeenCalled();
  });
});
