import { useNavigate } from 'react-router-dom';
import Button from '../../../components/UI/Button';
import Card from '../../../components/UI/Card';
import { MdEventBusy} from 'react-icons/md';

import './index.css';



const EmpityReserv = () => {
  const navigate = useNavigate();

  return (
    <div className="container">
    <div className='successfulAdm-page-container'>

    <Card className="successfulAdm-card-container">
      
      <div className="successfulAdm-card-content">
        <MdEventBusy fontSize={100} className="create-product-icon" />
  
      <h4>Aún no has efectuado ninguna reserva</h4>

      <Button onClick={() => navigate(`/`)} label="Volver" />
      </div>
    </Card>

    </div>
    </div>
  );
};

export default EmpityReserv;
