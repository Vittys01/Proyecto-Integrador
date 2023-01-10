import { CHARACTERISTIC_ICONS } from '../../../constants/characteristicIcons';
import { Product } from '../../../types/product';
import './index.css';

const ProductDescription = ({
  city,
  description,
  distance,
  characteristics
}: Partial<Product>) => {
  return (
    <div className="container">
      <div className="product-description-description">
        <h3>
          {distance && distance < 1000
            ? `Alójate en el corazón de ${city?.name}`
            : `Descubre ${city?.name} de una manera diferente`}
        </h3>
        <p className="product-description-text">{description}</p>
      </div>
      {characteristics && (
        <div className="product-description-features">
          <h3>¿Qué ofrece este lugar?</h3>
          <div className="product-description-features-grid">
            {characteristics.map(char => (
              <div
                className="flex align-center product-description-feature"
                key={char.title}
              >
                {CHARACTERISTIC_ICONS[char.title]}
                <p>{char.title}</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default ProductDescription;
