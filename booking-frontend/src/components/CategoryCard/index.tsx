import Card from '../UI/Card';
import { Category } from '../../types/category';
import './index.css';
import { DOMAttributes } from 'react';

interface Props extends DOMAttributes<HTMLDivElement> {
  data: Category;
  isActive?: boolean;
}

const CategoryCard = ({ data, isActive, ...props }: Props) => {
  const { image, name, quantity } = data;

  return (
    <Card
      className={`category-card-container ${
        isActive ? 'category-card-container-active' : ''
      }`}
      {...props}
    >
      <div className="category-card-img">
        <img src={image} alt={name} />
      </div>
      <div className="category-card-content">
        <h3 className="category-card-content-title">{name}</h3>
        <p className="category-card-content-amount">
          {quantity} {name.toLowerCase()}
        </p>
      </div>
    </Card>
  );
};

export default CategoryCard;
