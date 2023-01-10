import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import ProductGallery from '.';

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

const mockedImages = [
  {
    id: 1,
    url: '1'
  },
  {
    id: 2,
    url: '2'
  },
  {
    id: 3,
    url: '3'
  },
  {
    id: 4,
    url: '4'
  },
  {
    id: 5,
    url: '5'
  },
  {
    id: 6,
    url: '6'
  }
];

describe('ProductGallery Component', () => {
  it('should render correctly', () => {
    render(<ProductGallery productImages={mockedImages} />);

    expect(screen.getByTestId('share-icon')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Ver más' })).toBeInTheDocument();
  });

  it('should open lightbox gallery on photo click', () => {
    render(<ProductGallery />);

    const photo = screen.getAllByAltText('Sin foto')[0];

    userEvent.click(photo);
    expect(screen.getByLabelText('Rental gallery')).toBeInTheDocument();
  });

  it('should open lightbox gallery on see more button click', () => {
    render(<ProductGallery productImages={mockedImages} />);

    const seeMore = screen.getByRole('button', { name: 'Ver más' });

    userEvent.click(seeMore);
    expect(screen.getByLabelText('Rental gallery')).toBeInTheDocument();
  });

  it('should close lightbox gallery on escape key', () => {
    render(<ProductGallery />);

    const photo = screen.getAllByAltText('Sin foto')[0];

    userEvent.click(photo);
    userEvent.keyboard('{Escape}');

    expect(screen.queryByText('1')).not.toBeInTheDocument();
  });
});
