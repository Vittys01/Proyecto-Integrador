import {
  MdFoodBank,
  MdOutlineWifi,
  MdPool,
  MdTv,
  MdDirectionsCarFilled,
  MdPets
} from 'react-icons/md';
import { GiSnowflake2 } from 'react-icons/gi';

export const CHARACTERISTIC_ICONS = {
  Cocina: <MdFoodBank key="Cocina" />,
  Wifi: <MdOutlineWifi key="Wifi" />,
  Piscina: <MdPool key="Piscina" />,
  Televisor: <MdTv key="Televisor" />,
  'Aire acondicionado': <GiSnowflake2 key="Aire" />,
  'Estacionamiento gratuito': <MdDirectionsCarFilled key="Estacionamiento" />,
  'Apto mascotas': <MdPets key="Mascotas" />
};
