import { MdError as ErrorIcon } from 'react-icons/md';
import './index.css';

const ErrorMessage = ({
  text,
  className
}: {
  text: string;
  className?: string;
}) => {
  return (
    <div
      className={`error-message animate__animated animate__shakeX ${className}`}
    >
      <ErrorIcon />
      <p>{text}</p>
    </div>
  );
};

export default ErrorMessage;
