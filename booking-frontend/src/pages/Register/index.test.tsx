import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import Register from '.';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('Register Page', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <Register />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(screen.getByText('Crear Cuenta')).toBeInTheDocument();
    expect(
      screen.getByRole('button', { name: 'Crear cuenta' })
    ).toBeInTheDocument();
  });
});
