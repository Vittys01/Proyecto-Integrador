import { Category } from '../types/category';
import { Product } from '../types/product';
import { User } from '../types/user';

export const MOCKED_USER: User = {
  id: 1,
  name: 'Mica',
  lastName: 'Feller',
  role: 'USER',
  email: 'mica.feller@hotmail.com'
};

export const MOCKED_ADMIN: User = {
  id: 1,
  name: 'Mica',
  lastName: 'Feller',
  role: 'ADMIN',
  email: 'mica.feller@hotmail.com'
};

export const EXAMPLE_CATEGORY: Category = {
  id: 1,
  name: 'Departamentos',
  description: 'Lorem Ipsum',
  image: '',
  status: true,
  quantity: 1
};

export const EXAMPLE_PRODUCT: Product = {
  id: 1,
  name: 'Departamento completo',
  description: 'Lorem Ipsum',
  category: {
    id: 1,
    name: 'Departamentos',
    description: '',
    image: '',
    status: true,
    quantity: 3
  },
  city: {
    id: 1,
    name: 'Buenos Aires',
    province: {
      id: 1,
      name: 'Buenos Aires',
      country: {
        id: 1,
        name: 'Argentina'
      }
    }
  },
  distance: 100,
  image_list: [
    {
      id: 1,
      url: 'string',
      title: ''
    }
  ],
  characteristics: [
    {
      id: 1,
      title: 'Wifi'
    }
  ]
};

export const EXAMPLE_CITIES = [
  {
    value: 1,
    label: 'Buenos Aires',
    subLabel: 'Argentina'
  }
];
