import { DOMAttributes, ReactNode } from 'react';
import './index.css';

interface Props extends DOMAttributes<HTMLDivElement> {
  children: ReactNode;
  className?: string;
}

const Card = ({ children, className = '', ...props }: Props) => {
  return (
    <div {...props} className={`card-container ${className}`}>
      {children}
    </div>
  );
};

export default Card;
