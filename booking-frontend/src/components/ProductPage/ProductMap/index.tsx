import { Product } from '../../../types/product';
import './index.css';

const ProductMap = (productData: Product) => {
  return (
    <div className="product-iframe-map-content container">
      <h3>Dónde vas a estar</h3>

      <hr className="product-map-hr" />
      <p className="product-city-map-content">{productData.city.name}</p>
      <iframe
        title="Map"
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d210146.68168877432!2d-58.573383207134555!3d-34.615743688997895!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bcca3b4ef90cbd%3A0xa0b3812e88e88e87!2sBuenos%20Aires%2C%20Cidade%20Aut%C3%B4noma%20de%20Buenos%20Aires%2C%20Argentina!5e0!3m2!1spt-BR!2sbr!4v1669256692405!5m2!1spt-BR!2sbr"
        width="100%"
        height="450"
        loading="lazy"
        referrerPolicy="no-referrer-when-downgrade"
      ></iframe>
    </div>
  );
};

export default ProductMap;
