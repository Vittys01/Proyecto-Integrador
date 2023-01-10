import { useMemo, useState } from 'react';
import useFetch from '../../hooks/useFetch';
import Select from '../UI/Select';
import DatePicker from '../UI/DatePicker';
import Button from '../UI/Button';
import { Response } from '../../types/response';
import { City } from '../../types/city';
import {
  MdLocationOn as LocationFillIcon,
  MdOutlineLocationOn as LocationOutlineIcon
} from 'react-icons/md';
import './index.css';
import useMediaQuery from '../../hooks/useMediaQuery';

const SearchBox = ({ onSearch }: any) => {
  const showOnlyOneMonth = useMediaQuery('(max-width: 690px)');
  const [city, setCity] = useState('');
  const [country, setCountry] = useState('');
  const [values, setValues] = useState({
    city: '',
    dateFrom: '',
    dateTo: ''
  });

  const { data: citiesData } = useFetch<Response<City[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/city',
    initialParams: {
      page: 0,
      size: 10
    }
  });

  const options = useMemo(
    () =>
      (citiesData?.content || []).map(city => ({
        value: city.id,
        label: city.name,
        subLabel: `${city.province.name}, ${city.province.country.name}`
      })),
    [citiesData]
  );

  return (
    <div className="searchbox-container">
      <h1>Busca ofertas en hoteles, casas y mucho más</h1>
      <form
        className="searchbox-form"
        onSubmit={(e: any) => {
          e.preventDefault();
          onSearch(values);
        }}
      >
        <Select
          name="city"
          options={options}
          placeholder="¿A dónde vamos?"
          icon={
            <LocationFillIcon
              color={city ? 'var(--text-primary)' : 'var(--grey)'}
              fontSize={24}
            />
          }
          optionIcon={<LocationOutlineIcon fontSize={24} />}
          onChange={(e: any) => {
            setValues({ ...values, city: e?.value || '' });
            setCity(e?.label || '');
            setCountry(e?.subLabel || '');
          }}
          value={values.city && { label: `${city}, ${country}` }}
          className="searchbox-city-select"
        />
        <DatePicker
          onChange={(start: any, end: any) => {
            setValues({ ...values, dateFrom: start, dateTo: end });
          }}
          className="searchbox-date-picker"
          monthsShown={showOnlyOneMonth ? 1 : 2}
          withButton
        />
        <Button
          type="submit"
          label="Buscar"
          className="searchbox-search-button"
        />
      </form>
    </div>
  );
};

export default SearchBox;
