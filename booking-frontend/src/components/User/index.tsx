import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../contexts/UserContext';
import './index.css';

const UserName = () => {
  const { user, setUser } = useContext<any>(UserContext);
  const navigate = useNavigate();

  return (
    <>
      <div className="flex align-center">
        <div className="user-link-container">
          <p
            onClick={
              user?.role === 'ADMIN'
                ? () => navigate(`/administration`)
                : () => navigate(`/myreservations`)
            }
          >
            {user?.role === 'ADMIN' ? 'Administración' : 'Mis Reservas'}
          </p>
        </div>
        <div className="flex align-center">
          <div className="user-container">
            <div
              className="logout-button"
              onClick={() => {
                localStorage.removeItem('token');
                setUser({ name: '', lastName: '' });
                navigate('/');
              }}
            >
              <p>X</p>
            </div>
            <div className="user-content-right">
              <div className="icon-name-container">
                <p className="icon-name">
                  {user.name.charAt(0)}
                  {user.lastName.charAt(0)}
                </p>
              </div>
              <div className="user-content-left">
                <p>Hola,</p>
                <p className="user-name">
                  {user.name} {user.lastName}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserName;
