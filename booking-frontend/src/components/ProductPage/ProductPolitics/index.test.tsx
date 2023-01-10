import { render, screen } from '@testing-library/react';
import ProductPolitics from '.';

describe('ProductPolitics Component', () => {
  it('should render correctly', () => {
    render(<ProductPolitics />);

    expect(screen.getByText('Qué tenés que saber')).toBeInTheDocument();
  });
});
