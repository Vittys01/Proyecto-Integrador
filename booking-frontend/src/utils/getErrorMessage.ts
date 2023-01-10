import { ERROR_MESSAGES } from '../constants/errorMessages';

export const getErrorMessage = (errorCode: string) => {
  return ERROR_MESSAGES[errorCode] || 'Ha ocurrido un error';
};
