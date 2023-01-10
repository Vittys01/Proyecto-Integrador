import { useNavigate } from 'react-router-dom';
import Button from '../../../components/UI/Button';
import Card from '../../../components/UI/Card';
import { MdAssignmentTurnedIn as CheckIcon } from 'react-icons/md';
import './index.css';

const Successful = () => {
  const navigate = useNavigate();

  return (
    <div className="container">
      <div className="successful-page-container">
        <Card className="successful-card-container">
          <CheckIcon fontSize={50} color="var(--primary)" />
          <h3>¡Muchas gracias!</h3>
          <h4>Su reserva se ha realizado con éxito</h4>
          <Button onClick={() => navigate(`/`)} label="Ok" />
        </Card>
      </div>
    </div>
  );
};

export default Successful;
