import { render, screen } from '@testing-library/react';
import ProductInfoHeader from '.';
import { EXAMPLE_PRODUCT } from '../../../utils/testsData';

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

const { city } = EXAMPLE_PRODUCT;

describe('ProductInfoHeader Component', () => {
  it('should render correctly', () => {
    render(<ProductInfoHeader city={city} />);

    expect(
      screen.getByText(
        `${city.name}, ${city.province.name}, ${city.province.country.name}`
      )
    ).toBeInTheDocument();
  });
});
