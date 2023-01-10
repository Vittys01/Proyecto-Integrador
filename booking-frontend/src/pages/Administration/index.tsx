import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CreateFeature from '../../components/AdmPage/CreateFeature';
import CreateImage from '../../components/AdmPage/CreateImage';
import CreatePolitic from '../../components/AdmPage/CreatePolitic';
import CreateProduct from '../../components/AdmPage/CreateProduct';
import Button from '../../components/UI/Button';
import Card from '../../components/UI/Card';
import NavigationHeader from '../../components/UI/NavigationHeader';
import useFetch from '../../hooks/useFetch';
import './index.css';

const AdministrationPage = ({ onSearch }: any) => {
  const navigate = useNavigate();

  const [values, setValues] = useState({
    characteristics: [],
    description: '',
    images: [],
    name: '',
    city: '',
    category: ''
  });

  const { refetch } = useFetch<any>({
    withAuth: true,
    initialUrl: `http://3.21.170.194:8080/api/rental`,
    method: 'post',
    body: JSON.stringify({
      category_id: values.category,
      characteristics: [],
      city_id: values.city,
      description: values.description,
      distance: 100,
      images: values.images,
      name: values.name
    }),
    onSuccess: () => navigate('/successful-product'),
    onError: () =>
      document
        .querySelector('.layout-container')
        ?.scrollTo({ top: 0, left: 0, behavior: 'smooth' })
  });

  const handleCreateProductChange = (e: any) => {
    setValues({
      ...values,
      city: e.city,
      category: e.category,
      name: e.name,
      description: e.description
    });
  };

  const handleCreateFeatureChange = (e: any) => {
    setValues({ ...values, characteristics: e.characteristic });
  };

  const handleCreateImageChange = (e: any) => {
    setValues({ ...values, images: e });
  };

  const handleConfirmProduct = () => {
    refetch();
  };

  console.log(values);

  return (
    <div className="adm-page-container">
      <NavigationHeader>
        <h2 className="adm-page-header-name">Administración</h2>
      </NavigationHeader>
      <div className="container">
        <h3 className="adm-page-title">Crear Propiedad</h3>
        <Card className="adm-page-card">
          <form
            className="flex adm-page-form"
            onSubmit={(e: any) => {
              e.preventDefault();
              // onSearch(values);
            }}
          >
            <CreateProduct onChange={handleCreateProductChange} />
            <CreateFeature onChange={handleCreateFeatureChange} />
            <CreatePolitic />
            <CreateImage onChange={handleCreateImageChange} />
            <Button
              onClick={handleConfirmProduct}
              type="submit"
              label="Crear"
              className="adm-page-button"
            />
          </form>
        </Card>
      </div>
    </div>
  );
};

export default AdministrationPage;
