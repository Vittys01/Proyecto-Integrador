import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import DatePicker from '.';

const handleChange = jest.fn();

describe('Select Component', () => {
  it('should render correctly', async () => {
    render(<DatePicker onChange={() => handleChange()} withButton />);

    const datePicker = screen.getByPlaceholderText('Check in - Check out');
    expect(datePicker).toBeInTheDocument();

    userEvent.click(datePicker);
    const dayOne = screen.getAllByText('1')[0];
    expect(dayOne).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Aplicar' })).toBeInTheDocument();
  });
});
