import { useContext } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { UserContext } from '../../contexts/UserContext';
import Button from '../UI/Button';
import UserName from '../User';
import Logo from '../../assets/images/digital_booking_logo.svg';
import MenuLine from '../../assets/icons/menu.svg';
import './index.css';

interface Props {
  onOpenMenu?: () => void;
}

const Header = ({ onOpenMenu }: Props) => {
  const navigate = useNavigate();
  const location = useLocation();
  const { user } = useContext<any>(UserContext);

  return (
    <div className="header-container">
      <div className="header-content container">
        <div className="header-content-left" onClick={() => navigate('/')}>
          <img src={Logo} alt="Digital Booking" />
          <p className="header-slogan">Sentite como en tu hogar</p>
        </div>
        <div className="menu-mobile">
          <img src={MenuLine} alt="Open Menu" onClick={onOpenMenu} />
        </div>
        <div className="header-content-right">
          {user?.name ? (
            <UserName />
          ) : (
            <div>
              {location.pathname !== '/register' && (
                <Button
                  type="submit"
                  variant="outline"
                  label="Crear cuenta"
                  onClick={() => navigate('/register')}
                />
              )}
              {location.pathname !== '/login' && (
                <Button
                  type="submit"
                  variant="outline"
                  label="Iniciar sesión"
                  onClick={() => navigate('/login')}
                  className="header-button-right"
                />
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Header;
