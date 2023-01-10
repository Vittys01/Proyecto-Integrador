import { useNavigate } from 'react-router-dom';
import useMediaQuery from '../../../hooks/useMediaQuery';
import { MdOutlineArrowBackIos as BackIcon } from 'react-icons/md';
import './index.css';

const MyReservHeader = () => {
  const navigate = useNavigate();
  const isMobile = useMediaQuery('(max-width: 576px)');

  return (
    <>
      <div className="product-header-container">
        <div className="product-header-content container">
          <div>
            <h3 className="product-header-name">Mis reservas</h3>
          </div>
          <div
            className="flex align-center product-header-back"
            onClick={() => navigate(`/`)}
          >
            <BackIcon fontSize={isMobile ? 36 : 20} />
            {!isMobile && <p className="bold">volver</p>}
          </div>
        </div>
      </div>
    </>
  );
};

export default MyReservHeader;
