import { FormEvent, useContext, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import useFetch from '../../hooks/useFetch';
import Button from '../../components/UI/Button';
import Input from '../../components/UI/Input';
import { getErrorMessage } from '../../utils/getErrorMessage';
import ErrorMessage from '../../components/UI/ErrorMessage';
import './index.css';
import { UserContext } from '../../contexts/UserContext';

const LoginPage = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const [formValues, setFormValues] = useState<FormData>();
  const [isLogged, setIsLogged] = useState(false);
  const { setUser } = useContext<any>(UserContext);

  const { isLoading, errorMessage, refetch } = useFetch<any>({
    initialUrl: 'http://3.21.170.194:8080/api/user/login',
    skip: !formValues,
    method: 'post',
    body: JSON.stringify({
      email: formValues?.get('email'),
      password: formValues?.get('password')
    }),
    onSuccess: data => {
      localStorage.setItem('token', data.token);
      setIsLogged(true);
    }
  });

  const { data } = useFetch<any>({
    skip: !isLogged,
    initialUrl: `http://3.21.170.194:8080/api/user/account`,
    withAuth: true,
    onSuccess: data => {
      setUser({
        id: data.id,
        name: data.name,
        lastName: data.last_name,
        email: data.email,
        role: data.authorities[0]
      });
      if (!state) navigate('/');
      if (state.fromSpecificPage) {
        navigate(`/product/${state.productId}/reservation`);
      }
    }
  });

  const onSubmit = (data: any) => {
    setFormValues(data);
    refetch();
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formElement = e.target as HTMLFormElement;
    const isValid = formElement.checkValidity();

    const firstInvalidField = formElement.querySelector(
      ':invalid'
    ) as HTMLInputElement;

    firstInvalidField?.focus();

    if (isValid) {
      const dataObject = new FormData(formElement);
      onSubmit(dataObject);
    }
  };

  return (
    <div className="login-container">
      {!isLoading && errorMessage && (
        <ErrorMessage
          text={getErrorMessage(errorMessage)}
          className="login-error"
        />
      )}
      <div className="login-wrapper">
        <h1 className="login-title">Iniciar Sesión</h1>
        <form className="login-form" onSubmit={handleSubmit} noValidate>
          <Input
            id="email"
            label="Correo Electrónico"
            type="email"
            name="email"
            placeholder="Ingrese su Email"
            required
          />
          <Input
            id="password"
            label="Contraseña"
            name="password"
            placeholder="Ingrese su contraseña "
            required
            minLength={7}
            isPassword
          />
          <Button type="submit" label="Ingresar" className="login-button" />
        </form>
        <div className="login-register-message">
          <span>
            ¿Aún no tenes cuenta?{' '}
            <Link to="/register" className="text-primary">
              Registrate
            </Link>
          </span>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
