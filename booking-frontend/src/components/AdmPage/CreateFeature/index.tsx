/* eslint-disable @typescript-eslint/no-unused-vars */
import { useMemo, useState } from 'react';
import { MdAddBox } from 'react-icons/md';
import useFetch from '../../../hooks/useFetch';
import { Feature } from '../../../types/feature';
import { Response } from '../../../types/response';
import Select from '../../UI/Select';
import './index.css';

const CreateFeature = ({ onChange }: any) => {
  const [characteristics, setCharacteristics] = useState<number[]>([]);

  const { data: featuresData } = useFetch<Response<Feature[]>>({
    initialUrl: 'http://3.21.170.194:8080/api/characteristic',
    initialParams: {
      page: 0,
      size: 5
    }
  });

  const optionsFeature = useMemo(
    () =>
      (featuresData?.content || []).map(feature => ({
        value: feature.id,
        label: feature.title
      })),
    [featuresData]
  );

  const [featureSelect, setFeatureSelect] = useState([0]);

  return (
    <>
      <h4 className="add-features-title">Agregar Atributos</h4>
      <div className="add-features-container">
        <label htmlFor="feature" className="feature-select-label">
          Caracteristicas
        </label>
        <div className="add-features-inputs">
          {featureSelect.map((feature, i) => (
            <div className="flex align-center add-features-input" key={feature}>
              <Select
                name="feature"
                options={
                  optionsFeature /* .filter(
                    option =>
                      !featureValue.characteristics.includes(option.value)
                  ) */
                }
                placeholder="Seleccionar"
                onChange={(e: any) => {
                  setCharacteristics(characteristics.concat(e.value));
                  onChange(characteristics.concat(e.value));
                }}
              />
              <MdAddBox
                onClick={() => setFeatureSelect([...featureSelect, i + 1])}
                fontSize={45}
                color="var(--primary)"
              />
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default CreateFeature;
