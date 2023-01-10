import { render, screen } from '@testing-library/react';
import Score from '.';

describe('ErrorMessage Component', () => {
  it('should render correctly with low score', () => {
    render(<Score value={1} />);

    expect(screen.getByText('Regular')).toBeInTheDocument();
  });

  it('should render correctly with high score', () => {
    render(<Score value={5} />);

    expect(screen.getByText('Bueno')).toBeInTheDocument();
  });
});
