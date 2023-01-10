import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import Layout from '.';

describe('Layout Component', () => {
  it('should render correctly', () => {
    render(
      <MemoryRouter>
        <Layout />
      </MemoryRouter>
    );

    const container = screen.getByTestId('layout-container');
    expect(container).toBeInTheDocument();
  });

  it('should change style when menu is open', () => {
    render(
      <MemoryRouter>
        <Layout />
      </MemoryRouter>
    );

    const openMenuButton = screen.getByAltText('Open Menu');
    userEvent.click(openMenuButton);

    const container = screen.getByTestId('layout-container');
    expect(container).toHaveStyle('overflow: hidden');
  });

  it('should change style on close menu', () => {
    render(
      <MemoryRouter>
        <Layout />
      </MemoryRouter>
    );

    const openMenuButton = screen.getByAltText('Open Menu');
    userEvent.click(openMenuButton);

    const mobileMenuBackground = screen.getByTestId('mobile-menu-background');
    userEvent.click(mobileMenuBackground);

    const container = screen.getByTestId('layout-container');
    expect(container).toHaveStyle('overflow: auto');
  });
});
