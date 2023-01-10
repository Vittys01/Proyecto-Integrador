import './index.css';

const ProductPolitics = ({ fullWidth }: { fullWidth?: boolean }) => {
  return (
    <div className={`${fullWidth ? 'politics-container' : 'container'}`}>
      <div className={`${fullWidth ? 'politics-container container' : ''}`}>
        <h3>Qué tenés que saber</h3>

        <hr className="product-politics-hr"/>

        <div className="product-politics-grid">
          <div className="normas">
            <h4>Normas</h4>
            <p>Check-out: 10:00</p>
            <p>No se permiten fiestas</p>
            <p>No fumar</p>
          </div>
          <div className="seguridad">
            <h4>Salud y Seguridad</h4>
            <p>
              Se aplican las pautas de distanciamiento social y otras normas
              relacionadas con el coronavirus
            </p>
            <p>Detector de humo</p>
            <p>Depósito de seguridad</p>
          </div>
          <div className="cancelacion">
            <h4>Política de Cancelacion</h4>
            <p>
              Agregá las fechas de tu viaje para obtener los detalles de
              cancelación de esta estadía.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductPolitics;
