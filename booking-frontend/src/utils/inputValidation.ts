export const validateInput = (validity: ValidityState) => {
  if (validity.valueMissing) {
    return 'Este campo es obligatorio';
  } else if (validity.typeMismatch) {
    return 'Ingrese un email válido';
  } else if (validity.tooShort) {
    return 'La contraseña debe tener al menos 6 caractéres';
  } else {
    return '';
  }
};
