import { render, screen } from '@testing-library/react';
import CategoryCard from '.';
import { EXAMPLE_CATEGORY } from '../../utils/testsData';

describe('CategoryCard Component', () => {
  it('should render correctly', () => {
    render(<CategoryCard data={EXAMPLE_CATEGORY} />);

    expect(screen.getByAltText(EXAMPLE_CATEGORY.name)).toBeInTheDocument();
    expect(
      screen.getByText(
        `${EXAMPLE_CATEGORY.quantity} ${EXAMPLE_CATEGORY.name.toLowerCase()}`
      )
    ).toBeInTheDocument();
  });
});
