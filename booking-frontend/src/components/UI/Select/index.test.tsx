import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Select from '.';
import { EXAMPLE_CITIES } from '../../../utils/testsData';
import { MdLocationOn as LocationFillIcon } from 'react-icons/md';

describe('Select Component', () => {
  it('should render correctly', () => {
    render(
      <Select
        options={EXAMPLE_CITIES}
        placeholder="Select city"
        optionIcon={<LocationFillIcon />}
      />
    );

    const select = screen.getByText('Select city');
    expect(select).toBeInTheDocument();

    userEvent.click(select);
    const option = screen.getByText('Buenos Aires');
    expect(option).toBeInTheDocument();

    expect(screen.getByTestId('option-icon')).toBeInTheDocument();
  });
});
