import { render, screen } from '@testing-library/react';
import ProductDescription from '.';
import { EXAMPLE_PRODUCT } from '../../../utils/testsData';

describe('ProductDescription Component', () => {
  it('should render correctly', () => {
    render(
      <ProductDescription
        city={EXAMPLE_PRODUCT.city}
        description={EXAMPLE_PRODUCT.description}
        distance={EXAMPLE_PRODUCT.distance}
        characteristics={EXAMPLE_PRODUCT.characteristics}
      />
    );

    expect(screen.getByText('Lorem Ipsum')).toBeInTheDocument();
    expect(
      screen.getByText(`Alójate en el corazón de ${EXAMPLE_PRODUCT.city.name}`)
    ).toBeInTheDocument();
    expect(screen.getByText('Wifi')).toBeInTheDocument();
  });

  it('should render alternative text if distance is too high', () => {
    render(
      <ProductDescription
        city={EXAMPLE_PRODUCT.city}
        description={EXAMPLE_PRODUCT.description}
        distance={2000}
      />
    );

    expect(screen.getByText('Lorem Ipsum')).toBeInTheDocument();
    expect(
      screen.getByText(
        `Descubre ${EXAMPLE_PRODUCT.city.name} de una manera diferente`
      )
    ).toBeInTheDocument();
  });
});
