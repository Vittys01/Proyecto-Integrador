import { FormEvent } from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Input from '.';

describe('Input Component', () => {
  it('should render correctly', () => {
    render(
      <Input
        label="Input label"
        name="test-input"
        id="test-input"
        placeholder="Input placeholder"
      />
    );

    expect(screen.getByLabelText('Input label')).toBeInTheDocument();
    expect(
      screen.getByPlaceholderText('Input placeholder')
    ).toBeInTheDocument();
  });

  it('should show error text', async () => {
    render(
      <form
        noValidate
        onSubmit={(e: FormEvent<HTMLFormElement>) => {
          e.preventDefault();

          const formElement = e.target as HTMLFormElement;
          formElement.checkValidity();
        }}
      >
        <Input
          label="Input label"
          name="test-input"
          id="test-input"
          type="email"
          placeholder="Input placeholder"
        />
        <button>Submit</button>
      </form>
    );

    const submitButton = screen.getByRole('button');
    userEvent.type(screen.getByPlaceholderText('Input placeholder'), 'mail');
    userEvent.click(submitButton);
    await waitFor(() => {
      expect(screen.getByText('Ingrese un email válido')).toBeInTheDocument();
    });
  });

  it('should hide error text if user types correct value', async () => {
    render(
      <form
        noValidate
        onSubmit={(e: FormEvent<HTMLFormElement>) => {
          e.preventDefault();

          const formElement = e.target as HTMLFormElement;
          formElement.checkValidity();
        }}
      >
        <Input
          label="Input label"
          name="test-input"
          id="test-input"
          type="email"
          placeholder="Input placeholder"
        />
        <button>Submit</button>
      </form>
    );

    const submitButton = screen.getByRole('button');
    userEvent.type(screen.getByPlaceholderText('Input placeholder'), 'mail');
    userEvent.click(submitButton);
    await waitFor(() => {
      expect(screen.getByText('Ingrese un email válido')).toBeInTheDocument();
    });

    userEvent.type(
      screen.getByPlaceholderText('Input placeholder'),
      'mail@mail.com'
    );
    expect(
      screen.queryByText('Ingrese un email válido')
    ).not.toBeInTheDocument();
  });

  it('should show eye icons if type is password', () => {
    render(
      <Input
        label="Input label"
        name="test-input"
        id="test-input"
        placeholder="Input placeholder"
        isPassword
      />
    );

    const showPassword = screen.getByTestId('show-password-icon');
    expect(showPassword).toBeInTheDocument();

    userEvent.click(showPassword);
    const hidePassword = screen.getByTestId('hide-password-icon');
    expect(hidePassword).toBeInTheDocument();

    userEvent.click(hidePassword);
    expect(screen.getByTestId('show-password-icon')).toBeInTheDocument();
  });

  it('should show error message if provided', () => {
    render(
      <Input
        label="Input label"
        name="test-input"
        id="test-input"
        placeholder="Input placeholder"
        errorText="Error message"
      />
    );

    expect(screen.getByText('Error message')).toBeInTheDocument();
  });
});
