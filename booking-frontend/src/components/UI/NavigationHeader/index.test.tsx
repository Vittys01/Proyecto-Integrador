import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import NavigationHeader from '.';

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

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('NavigationHeader Component', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <NavigationHeader>Hola</NavigationHeader>
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(screen.getByText('Hola')).toBeInTheDocument();
    expect(screen.getByTestId('back-icon')).toBeInTheDocument();
  });

  it('should navigate to home if prop not provided', () => {
    setup();

    const backButton = screen.getByTestId('back-icon');
    userEvent.click(backButton);
    expect(mockNavigate).toBeCalledWith('/');
  });

  it('should navigate to provided path', () => {
    render(
      <MemoryRouter>
        <NavigationHeader backTo="/register">Hola</NavigationHeader>
      </MemoryRouter>
    );

    const backButton = screen.getByTestId('back-icon');
    userEvent.click(backButton);
    expect(mockNavigate).toBeCalledWith('/register');
  });
});
