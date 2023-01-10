/* eslint-disable @typescript-eslint/no-unused-vars */
import { FormEvent, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../contexts/UserContext';
import useFetch from '../../hooks/useFetch';
import Button from '../../components/UI/Button';
import Input from '../../components/UI/Input';
import ErrorMessage from '../../components/UI/ErrorMessage';
import { getErrorMessage } from '../../utils/getErrorMessage';
import './index.css';
import { Link } from 'react-router-dom';

const RegisterPage = () => {
  const navigate = useNavigate();
  const { setUser } = useContext<any>(UserContext);
  const [formValues, setFormValues] = useState<FormData>();
  const [registeredUser, setRegisteredUser] = useState<any>();
  const [passwordMatch, setPasswordMatch] = useState(true);

  const { isLoading, errorMessage, refetch } = useFetch<any>({
    initialUrl: 'http://3.21.170.194:8080/api/user/register',
    skip: !formValues,
    method: 'post',
    body: JSON.stringify({
      email: formValues?.get('email'),
      name: formValues?.get('name'),
      last_name: formValues?.get('lastName'),
      password: formValues?.get('password')
    }),
    onSuccess: data => {
      setUser({ name: data.name, lastName: data.last_name });
      setRegisteredUser({
        email: formValues?.get('email'),
        password: formValues?.get('password')
      });
    }
  });

  const { data: loginData } = useFetch<any>({
    initialUrl: 'http://3.21.170.194:8080/api/user/login',
    skip: !registeredUser,
    method: 'post',
    body: JSON.stringify({
      email: formValues?.get('email'),
      password: formValues?.get('password')
    }),
    onSuccess: data => {
      localStorage.setItem('token', data.token);
      navigate('/');
    }
  });

  const onSubmit = (data: any) => {
    setFormValues(data);
    refetch();
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formElement = e.target as HTMLFormElement;
    const password = document.getElementById('password') as HTMLInputElement;
    const confirmPassword = document.getElementById(
      'confirmPassword'
    ) as HTMLInputElement;

    const isValid =
      formElement.checkValidity() && password.value === confirmPassword.value;

    const firstInvalidField = formElement.querySelector(
      ':invalid'
    ) as HTMLInputElement;

    firstInvalidField?.focus();

    if (isValid) {
      confirmPassword.classList.remove('invalid');
      setPasswordMatch(true);
      const dataObject = new FormData(formElement);
      onSubmit(dataObject);
    } else {
      setPasswordMatch(false);
      confirmPassword.classList.add('invalid');
    }
  };

  return (
    <div className="register-container">
      {!isLoading && errorMessage && (
        <ErrorMessage
          text={getErrorMessage(errorMessage)}
          className="register-error"
        />
      )}
      <div className="register-wrapper">
        <h1 className="register-title">Crear Cuenta</h1>
        <form className="register-form" onSubmit={handleSubmit} noValidate>
          <div className="register-name">
            <Input id="name" label="Nombre" type="text" name="name" required />
            <Input
              id="lastName"
              label="Apellido"
              type="text"
              name="lastName"
              required
            />
          </div>
          <Input
            id="email"
            label="Correo electrónico"
            type="email"
            name="email"
            required
          />
          <Input
            id="password"
            label="Contraseña"
            name="password"
            required
            isPassword
          />
          <Input
            id="confirmPassword"
            label="Confirmar Contraseña"
            name="confirmPassword"
            required
            isPassword
            errorText={passwordMatch ? undefined : 'La contraseña no coincide'}
          />
          <div className="register-footer">
            <Button
              type="submit"
              onClick={() => 'SendForm'}
              label="Crear cuenta"
              className="register-button"
            />
            <p className="register-login-message">
              ¿Ya tienes una cuenta?{' '}
              <Link to="/login" className="text-primary">
                Iniciar sesión
              </Link>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
