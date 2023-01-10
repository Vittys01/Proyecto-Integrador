import { useState } from 'react';

export function useForm<TValues>(
  initialState: TValues
): [TValues, (name: string, value: string) => void, () => void] {
  const [values, setValues] = useState(initialState);

  const reset = () => {
    setValues(initialState);
  };

  const handleInputChange = (name: string, value: string) => {
    setValues({
      ...values,
      [name]: value
    });
  };

  return [values, handleInputChange, reset];
}
