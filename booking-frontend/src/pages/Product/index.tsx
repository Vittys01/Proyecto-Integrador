import { useParams } from 'react-router-dom';
import useFetch from '../../hooks/useFetch';
import ProductInfoHeader from '../../components/ProductPage/ProductInfoHeader';
import ProductGallery from '../../components/ProductPage/ProductGallery';
import ProductDescription from '../../components/ProductPage/ProductDescription';
import ProductPolitics from '../../components/ProductPage/ProductPolitics';
import ProductReservation from '../../components/ProductPage/ProductReservation';
import ProductMap from '../../components/ProductPage/ProductMap';
import { Product } from '../../types/product';
import './index.css';
import NavigationHeader from '../../components/UI/NavigationHeader';

const ProductPage = () => {
  const { productId } = useParams();

  const { data: productData } = useFetch<Product>({
    initialUrl: `http://3.21.170.194:8080/api/rental/${productId}`
  });

  return (
    <div className="product-page-container">
      {productData && (
        <>
          <NavigationHeader>
            <p className="product-header-category">
              {productData.category?.name}
            </p>
            <h3 className="product-header-name">{productData.name}</h3>
          </NavigationHeader>
          <ProductInfoHeader
            city={productData.city}
            distance={productData.distance}
          />
          <div className="product-page-content">
            <ProductGallery
              productImages={
                productData.image_list ? productData.image_list : null
              }
            />
            <ProductDescription
              city={productData.city}
              description={productData.description}
              distance={productData.distance}
              characteristics={productData.characteristics}
            />
            <ProductReservation data={productData} />
            <ProductMap {...productData} />
            <ProductPolitics />
          </div>
        </>
      )}
    </div>
  );
};

export default ProductPage;
