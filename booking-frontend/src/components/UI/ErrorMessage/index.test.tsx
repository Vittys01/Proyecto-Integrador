import { render, screen } from '@testing-library/react';
import ErrorMessage from '.';

describe('ErrorMessage Component', () => {
  it('should render correctly', () => {
    render(<ErrorMessage text="Error" />);

    expect(screen.getByText('Error')).toBeInTheDocument();
  });
});
