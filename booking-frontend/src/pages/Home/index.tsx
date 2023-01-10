import CategoryCard from '../../components/CategoryCard';
import ProductCard from '../../components/ProductCard';
import SearchBox from '../../components/SearchBox';
import useFetch from '../../hooks/useFetch';
import { Response } from '../../types/response';
import { Category } from '../../types/category';
import { Product } from '../../types/product';

import './index.css';
import { useContext, useState } from 'react';
import { UserContext } from '../../contexts/UserContext';
import { getDateFormatted } from '../../utils/date';

const HomePage = () => {
  const [selectedCity, setSelectedCity] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('');
  const [selectedDates, setSelectedDates] = useState({
    dateFrom: '',
    dateTo: ''
  });
  const { user } = useContext(UserContext);

  const { data: categoriesData } = useFetch<Response<Category[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/category',
    initialParams: {
      page: 0,
      size: 10
    }
  });

  const {
    data: productsData,
    updateParams,
    isLoading
  } = useFetch<Response<Product[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/rental',
    withAuth: user?.name
  });

  const handleSearch = (data: any) => {
    let checkIn;
    let checkOut;

    if (data.dateFrom) {
      checkIn = getDateFormatted(data.dateFrom);
      checkOut = getDateFormatted(data.dateTo);
      setSelectedDates({ dateFrom: checkIn, dateTo: checkOut });
    } else {
      checkIn = '';
      checkOut = '';
    }

    setSelectedCity(data.city);
    updateParams({
      city: data.city,
      category: selectedCategory,
      check_in: checkIn || '',
      check_out: checkOut || ''
    });
  };

  const handleCategorySelect = (categoryId: string) => {
    setSelectedCategory(categoryId);
    updateParams({ category: categoryId, city: selectedCity });
  };

  const isResults =
    !!selectedCity || !!selectedCategory || !!selectedDates.dateTo;

  return (
    <>
      <SearchBox onSearch={handleSearch} />
      <div className="home-categories-container">
        <div className="container">
          <h2>Buscar por tipo de alojamiento</h2>
          <div className="home-categories-grid">
            {categoriesData &&
              categoriesData.content?.map(category => (
                <CategoryCard
                  key={category.id}
                  data={category}
                  isActive={category.id === +selectedCategory}
                  onClick={() =>
                    handleCategorySelect(
                      +selectedCategory === category.id
                        ? ''
                        : category.id.toString()
                    )
                  }
                />
              ))}
          </div>
        </div>
      </div>
      <div className="home-recomendations-container">
        <div className="container">
          <h2>Recomendaciones</h2>
          <div className="home-recomendations-grid">
            {!isLoading &&
              productsData &&
              productsData.content?.map(product => (
                <div key={product.id}>
                  <ProductCard data={product} />
                </div>
              ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default HomePage;
