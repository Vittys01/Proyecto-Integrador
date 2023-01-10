import { Category } from './category';
import { City } from './city';
import { Image } from './image';

export enum CharacteristicName {
  Cocina = 'Cocina',
  Wifi = 'Wifi',
  Piscina = 'Piscina',
  Televisor = 'Televisor',
  'Estacionamiento gratuito' = 'Estacionamiento gratuito',
  'Aire acondicionado' = 'Aire acondicionado',
  'Apto mascotas' = 'Apto mascotas'
}

export interface Characteristic {
  id: number;
  title: keyof typeof CharacteristicName;
}

export interface Product {
  id: number;
  name: string;
  description: string;
  category: Category;
  city: City;
  distance: number;
  image_list?: Image[];
  characteristics?: Characteristic[];
  booking_set?: any;
}
