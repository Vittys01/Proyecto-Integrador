import { FormEvent, memo, useState } from 'react';
import './index.css';
import { validateInput } from '../../../utils/inputValidation';
import {
  MdVisibility as ShowIcon,
  MdVisibilityOff as HideIcon
} from 'react-icons/md';

interface Props extends React.HTMLProps<HTMLInputElement> {
  id: string;
  name: string;
  className?: string;
  label?: string;
  errorText?: string;
  isPassword?: boolean;
}

const Input = memo(
  ({ className, label, errorText, id, isPassword, ...rest }: Props) => {
    const [validationMessage, setValidationMessage] = useState('');
    const [passwordType, setPasswordType] = useState('password');

    const onInvalid = (e: FormEvent) => {
      const target = e.target as HTMLInputElement;
      setValidationMessage(validateInput(target.validity));
    };

    const onChange = (e: FormEvent) => {
      const target = e.target as HTMLInputElement;

      if (!!validationMessage) {
        setValidationMessage(validateInput(target.validity));
      }
    };

    return (
      <div className={`input-wrapper ${className}`}>
        {label && (
          <label htmlFor={id} className="input-label">
            {label}
          </label>
        )}
        <input
          id={id}
          className={`input-field ${validationMessage ? 'invalid' : ''}`}
          onInvalid={onInvalid}
          onChange={onChange}
          type={isPassword ? passwordType : rest.type}
          {...rest}
        />
        {isPassword && passwordType === 'password' && (
          <ShowIcon
            className="input-password-icon"
            fontSize={20}
            color="var(--dark-grey)"
            onClick={() => setPasswordType('text')}
            data-testid="show-password-icon"
          />
        )}
        {isPassword && passwordType === 'text' && (
          <HideIcon
            className="input-password-icon"
            fontSize={20}
            color="var(--dark-grey)"
            onClick={() => setPasswordType('password')}
            data-testid="hide-password-icon"
          />
        )}
        {(!!validationMessage || !!errorText) && (
          <p className="input-error-message">
            {validationMessage || errorText}
          </p>
        )}
      </div>
    );
  }
);

Input.displayName = 'TextInput';

export default Input;
