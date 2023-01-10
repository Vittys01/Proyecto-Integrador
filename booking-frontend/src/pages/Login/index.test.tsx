import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import Login from '.';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('Login Page', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <Login />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(screen.getByText('Iniciar Sesión')).toBeInTheDocument();
    expect(
      screen.getByRole('button', { name: 'Ingresar' })
    ).toBeInTheDocument();
  });
});
