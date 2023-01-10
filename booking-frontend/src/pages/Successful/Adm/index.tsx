import { useNavigate } from 'react-router-dom';
import Button from '../../../components/UI/Button';
import Card from '../../../components/UI/Card';
import SuccessfullIcon from '../../assets/icons/icon-successful.svg';
import { MdVerified } from 'react-icons/md';

import './index.css';



const SuccessfulProduct = () => {
  const navigate = useNavigate();

  return (
    <div className="container">
    <div className='successfulAdm-page-container'>

    <Card className="successfulAdm-card-container">
      
      <div className="successfulAdm-card-content">
        <MdVerified fontSize={100} className="create-product-icon" />
  
      <h4>Tu propiedad se ha creado con éxito.</h4>

      <Button onClick={() => navigate(`/`)} label="Volver" />
      </div>
    </Card>

    </div>
    </div>
  );
};

export default SuccessfulProduct;
