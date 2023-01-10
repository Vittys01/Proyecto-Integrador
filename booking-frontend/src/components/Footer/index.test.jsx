import { render, screen } from '@testing-library/react';
import Footer from '.';

describe('Footer Component', () => {
  it('should render correctly', () => {
    render(<Footer />);

    expect(screen.getByAltText('Go to Facebook')).toBeInTheDocument();
    expect(screen.getByAltText('Go to Linkedin')).toBeInTheDocument();
    expect(screen.getByAltText('Go to Twitter')).toBeInTheDocument();
    expect(screen.getByAltText('Go to Instagram')).toBeInTheDocument();
  });
});
