import { useNavigate } from 'react-router-dom';
import useMediaQuery from '../../../hooks/useMediaQuery';
import { MdOutlineArrowBackIos as BackIcon } from 'react-icons/md';
import './index.css';
import { ReactNode } from 'react';

interface Props {
  backTo?: string;
  children: ReactNode;
}

const NavigationHeader = ({ backTo, children }: Props) => {
  const navigate = useNavigate();
  const isMobile = useMediaQuery('(max-width: 576px)');

  return (
    <>
      <div className="nav-header-container">
        <div className="nav-header-content container">
          <div>{children}</div>
          <div
            className="flex align-center nav-header-back"
            onClick={() => navigate(backTo || '/')}
          >
            <BackIcon fontSize={isMobile ? 36 : 20} data-testid="back-icon" />
            {!isMobile && <p className="bold">volver</p>}
          </div>
        </div>
      </div>
    </>
  );
};

export default NavigationHeader;
