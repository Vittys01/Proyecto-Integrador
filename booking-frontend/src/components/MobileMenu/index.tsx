import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { UserContext } from '../../contexts/UserContext';
import FacebookLogo from '../../assets/icons/iconfacebook_grey.svg';
import LinkedinLogo from '../../assets/icons/iconlinkedin_grey.svg';
import TwitterLogo from '../../assets/icons/icontweet_grey.svg';
import InstagramLogo from '../../assets/icons/iconig_grey.svg';
import './index.css';
import UserName from '../User';

interface Props {
  onClose: () => void;
  isOpen: boolean;
}

const MobileMenu = ({ onClose, isOpen }: Props) => {
  const { user, setUser } = useContext<any>(UserContext);

  return (
    <div
      className="mobile-menu-container"
      style={{
        visibility: !isOpen ? 'hidden' : 'visible'
      }}
    >
      <div
        className="mobile-menu-background"
        style={{
          opacity: !isOpen ? '0' : '1'
        }}
        onClick={onClose}
        data-testid="mobile-menu-background"
      />
      <div
        className="mobile-menu-content"
        style={{
          right: !isOpen ? '-350px' : '0'
        }}
      >
        <div className="mobile-menu-content-header">
          <p onClick={onClose} className="mobile-menu-close-button">
            X
          </p>
          {user?.name ? <UserName /> : <p>MENÚ</p>}
        </div>
        {user?.name ? (
          <>
            <div className="mobile-menu-content-buttons">
              <Link
                to={
                  user?.role === 'ADMIN' ? '/administration' : '/myreservations'
                }
                onClick={onClose}
              >
                {user?.role === 'ADMIN' ? 'Administración' : 'Mis Reservas'}
              </Link>
            </div>
            <div className="mobile-menu-content-logout">
              <p>
                ¿Deseas{' '}
                <Link
                  to="/"
                  onClick={() => {
                    localStorage.removeItem('token');
                    setUser({ name: '', lastName: '' });
                    onClose();
                  }}
                  className="text-primary"
                >
                  cerrar sesión
                </Link>
                ?
              </p>
              <hr />
            </div>
          </>
        ) : (
          <div className="mobile-menu-content-buttons">
            <Link to="/register" onClick={onClose}>
              Crear cuenta
            </Link>
            <hr />
            <Link to="/login" onClick={onClose}>
              Iniciar sesión
            </Link>
          </div>
        )}
        <div className="mobile-menu-content-social">
          <img src={FacebookLogo} alt="Go to Facebook" />
          <img src={LinkedinLogo} alt="Go to Linkedin" />
          <img src={TwitterLogo} alt="Go to Twitter" />
          <img src={InstagramLogo} alt="Go to Instagram" />
        </div>
      </div>
    </div>
  );
};

export default MobileMenu;
