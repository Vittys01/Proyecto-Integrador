import { useMemo, useState } from 'react';
import useFetch from '../../../hooks/useFetch';
import { Category } from '../../../types/category';
import Input from '../../UI/Input';
import Select from '../../UI/Select';
import { Response } from '../../../types/response';
import { City } from '../../../types/city';
import './index.css';

const CreateProduct = ({ onChange }: any) => {
  const [productValue, setProductValue] = useState({
    name: '',
    category: '',
    city: '',
    description: ''
  });

  const { data: citiesData } = useFetch<Response<City[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/city',
    initialParams: {
      page: 0,
      size: 5
    }
  });

  const { data: categoriesData } = useFetch<Response<Category[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/category',
    initialParams: {
      page: 0,
      size: 5
    }
  });

  const optionsCity = useMemo(
    () =>
      (citiesData?.content || []).map(city => ({
        value: city.id,
        label: city.name
      })),
    [citiesData]
  );

  const optionsCategory = useMemo(
    () =>
      (categoriesData?.content || []).map(category => ({
        value: category.id,
        label: category.name
      })),
    [categoriesData]
  );

  return (
    <div>
      <div className="create-product-form-grid">
        <Input
          id="name"
          label="Nombre de la propriedad"
          type="text"
          name="name"
          onChange={(e: any) => {
            setProductValue({ ...productValue, name: e.target.value });
            onChange(productValue);
          }}
          required
        />
        <Select
          options={optionsCategory}
          name="category"
          placeholder="Categoría"
          className="box-category-select"
          inputLabel="Categoría"
          onChange={(e: any) => {
            setProductValue({ ...productValue, category: e?.value || '' });
            onChange(productValue);
          }}
        />
        <Input
          id="direccion"
          label="Dirección"
          type="text"
          name="direccion"
          required
        />
        <Select
          options={optionsCity}
          name="city"
          placeholder="Ciudad"
          className="box-city-select"
          inputLabel="Ciudad"
          onChange={(e: any) => {
            setProductValue({ ...productValue, city: e?.value || '' });
            onChange(productValue);
          }}
        />
      </div>
      <Input
        className="create-product-description"
        id="description"
        label="Descripción"
        type="textarea"
        name="discripcion"
        required
        onChange={(e: any) => {
          setProductValue({ ...productValue, description: e.target.value });
          onChange(productValue);
        }}
      />
    </div>
  );
};

export default CreateProduct;
