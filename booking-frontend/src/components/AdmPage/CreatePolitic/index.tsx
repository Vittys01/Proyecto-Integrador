import Input from '../../UI/Input';
import './index.css';

const CreatePolitic = () => {
  return (
    <>
      <h4 className="title-politic-create">Politicas del producto</h4>

      <div className="politic-create-card">
        <div className="politic-create-content">
          <h5>Normas de la casa</h5>
          <Input
            id="normas"
            label="Descripción"
            type="textarea"
            name="normas"
          />
        </div>
        <div className="politic-create-content">
          <h5>Salud y Seguridad</h5>
          <Input
            id="seguridad"
            label="Descripción"
            type="textarea"
            name="seguridad"
          />
        </div>
        <div className="politic-create-content">
          <h5>Política de cancelación</h5>
          <Input
            id="cancelacion"
            label="Descripción"
            type="textarea"
            name="cancelacion"
          />
        </div>
      </div>
    </>
  );
};

export default CreatePolitic;
