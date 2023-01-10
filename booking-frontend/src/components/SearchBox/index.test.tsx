import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SearchBox from '.';

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

const mockedOnSearch = jest.fn();

const mockConfig = {
  content: [
    {
      id: 1,
      name: 'Cucuta',
      province: {
        id: 23,
        name: 'Norte de Santander',
        country: {
          id: 2,
          name: 'Colombia'
        }
      }
    }
  ]
};

jest.mock('../../hooks/useFetch', () => ({
  __esModule: true,
  default: () => ({
    data: mockConfig
  })
}));

describe('ProductDescription Component', () => {
  it('should render correctly', () => {
    render(<SearchBox />);

    expect(
      screen.getByText('Busca ofertas en hoteles, casas y mucho más')
    ).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Buscar' })).toBeInTheDocument();
  });

  it('should submit on button click', () => {
    render(<SearchBox onSearch={mockedOnSearch} />);

    const searchButton = screen.getByRole('button', { name: 'Buscar' });
    userEvent.click(searchButton);
    expect(mockedOnSearch).toHaveBeenCalled();
  });

  it('should submit on button click with correct values', async () => {
    render(<SearchBox onSearch={mockedOnSearch} />);

    const selectCity = screen.getByText('¿A dónde vamos?');
    userEvent.click(selectCity);

    const option = screen.getByText('Cucuta');
    expect(option).toBeInTheDocument();
    userEvent.click(option);

    const selectDate = screen.getByPlaceholderText('Check in - Check out');
    userEvent.click(selectDate);

    const dayOne = screen.getAllByText('1')[1];
    const dayTwo = screen.getAllByText('2')[1];
    const confirmButton = screen.getByRole('button', { name: 'Aplicar' });
    userEvent.click(dayOne);
    userEvent.click(dayTwo);
    userEvent.click(confirmButton);
    //expect(screen.getByText('01/01/2023')).toBeInTheDocument();

    const searchButton = screen.getByRole('button', { name: 'Buscar' });
    userEvent.click(searchButton);
    await waitFor(() => {
      expect(mockedOnSearch).toHaveBeenCalledWith({
        city: 1,
        dateFrom: '',
        dateTo: ''
      });
    });
  });
});
