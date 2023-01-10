import { useNavigate } from 'react-router-dom';
import Card from '../../UI/Card';
import Score from '../../UI/Score';
import Button from '../../UI/Button';
import { Product } from '../../../types/product';
import NoPhotoImg from '../../../assets/images/no-photo.png';
import { MdLocationOn as LocationIcon } from 'react-icons/md';
import { CHARACTERISTIC_ICONS } from '../../../constants/characteristicIcons';
import './index.css';

interface Props {
  data: Product;
}

const MyReservList = ({ data }: Props) => {
  const { id, name, category, distance, description } = data;
  const navigate = useNavigate();

  return (
    <Card className="product-card-container">
      <div className="product-img">
        <img
          src={data.image_list ? data.image_list[0]?.url : NoPhotoImg}
          alt={name}
        />
      </div>
      <div className="product-content">
        <div className="product-content-header">
          <div>
            <p className="product-content-category">
              {category.name.split(' ')[0].slice(0, -1) +
                ' ' +
                category.name.split(' ').slice(1).join(' ')}
            </p>
            <h4 className="product-content-name">{name}</h4>
          </div>
          <Score value={8} />
        </div>
        <div className="product-content-detail">
          <div className="flex">
            <LocationIcon fontSize={18} />
            <p>A {distance} m del centro</p>
          </div>
          <p className="product-content-link">MOSTRAR EN EL MAPA</p>
        </div>
        <div className="product-content-characteristics">
          {data.characteristics &&
            data.characteristics.map(char => CHARACTERISTIC_ICONS[char.title])}
        </div>
        <p className="product-content-description">
          {description.slice(0, 50)}...
        </p>
        <Button
          className="product-content-button"
          onClick={() => navigate(`/product/${id}`)}
          label="Ver más"
        />
      </div>
    </Card>
  );
};

export default MyReservList;
