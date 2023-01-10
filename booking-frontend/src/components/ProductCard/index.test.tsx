import { MemoryRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import ProductCard from '.';
import { EXAMPLE_PRODUCT } from '../../utils/testsData';

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate
}));

describe('ProductCard Component', () => {
  const setup = () =>
    render(
      <MemoryRouter>
        <ProductCard data={EXAMPLE_PRODUCT} />
      </MemoryRouter>
    );

  it('should render correctly', () => {
    setup();

    expect(screen.getByAltText(EXAMPLE_PRODUCT.name)).toBeInTheDocument();
    expect(
      screen.getByText(`A ${EXAMPLE_PRODUCT.distance} m del centro`)
    ).toBeInTheDocument();
  });

  it('should navigate to product page when clicking on more', () => {
    setup();

    const seeMoreButton = screen.getByRole('button', { name: 'Ver más' });
    userEvent.click(seeMoreButton);
    expect(mockNavigate).toBeCalledWith(`/product/${EXAMPLE_PRODUCT.id}`);
  });

  it('should show NoPhotoImg if no image is provided', () => {
    delete EXAMPLE_PRODUCT.image_list;
    render(
      <MemoryRouter>
        <ProductCard data={EXAMPLE_PRODUCT} />
      </MemoryRouter>
    );

    const noPhoto = screen.getByRole('img', { name: 'Propiedad sin fotos' });

    expect(noPhoto).toBeInTheDocument();
  });
});
