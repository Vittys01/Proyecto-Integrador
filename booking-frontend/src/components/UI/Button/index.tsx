import React from 'react';
import './index.css';

interface Props {
  type?: 'button' | 'submit' | 'reset';
  variant?: 'fill' | 'outline' | 'text';
  border?: string;
  borderColor?: string;
  color?: string;
  backgroundColor?: string;
  label?: string;
  children?: React.ReactNode;
  height?: string;
  onClick?: () => void;
  radius?: string;
  width?: string;
  className?: string;
  disabled?: boolean;
}

const Button: React.FC<Props> = ({
  type = 'button',
  variant = 'fill',
  border,
  borderColor,
  color,
  backgroundColor,
  label,
  children,
  height,
  onClick,
  radius,
  width,
  className = '',
  disabled
}) => {
  return (
    <button
      className={`button-wrapper button-${variant} ${className}`}
      type={type}
      onClick={onClick}
      style={{
        border,
        borderColor: borderColor,
        color: color,
        backgroundColor: backgroundColor,
        borderRadius: radius,
        height,
        width
      }}
      disabled={disabled}
    >
      <b>{label || children}</b>
    </button>
  );
};

export default Button;
