/* eslint-disable @typescript-eslint/no-unused-vars */
import { FormEvent, useContext, useState } from 'react';
import { UserContext } from '../../../contexts/UserContext';
import useFetch from '../../../hooks/useFetch';
import Card from '../../UI/Card';
import Input from '../../UI/Input';
import './index.css';

const ReservationForm = ({ className }: { className: string }) => {
  const { user } = useContext<any>(UserContext);

  /* const onSubmit = (data: any) => {
    setFormValues(data);
    refetch();
  }; */

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
      // onSubmit(dataObject);
    }
  };

  return (
    <div className={className}>
      <h3>Completá tus datos</h3>
      <Card className="reservation-form-card">
        <form className="reservation-form" onSubmit={handleSubmit} noValidate>
          <Input
            id="name"
            value={user?.name || ''}
            label="Nombre"
            type="text"
            name="name"
            disabled
          />
          <Input
            id="lastName"
            value={user?.lastName || ''}
            label="Apellido"
            type="text"
            name="lastName"
            disabled
          />
          <Input
            id="email"
            value={user?.email || ''}
            label="Email"
            type="email"
            name="email"
            disabled
          />
          <Input
            id="ciudad"
            label="Ciudad"
            type="text"
            name="ciudad"
            required
          />
        </form>
      </Card>
    </div>
  );
};

export default ReservationForm;
