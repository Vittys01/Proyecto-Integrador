/* eslint-disable @typescript-eslint/no-unused-vars */
import { useState } from 'react';
import { MdAddBox } from 'react-icons/md';
import Input from '../../UI/Input';
import './index.css';

const CreateImage = ({ onChange }: any) => {
  const [imagenesInput, setImagenesInput] = useState([1]);
  const [imageValue, setImageValue] = useState<String[]>([]);

  return (
    <>
      <h4 className="add-image-title">Cargar imágenes</h4>
      <div className="add-image-container">
        <div className="flex add-image-inputs">
          {imagenesInput.map((image, i) => (
            <div key={image} className="flex add-image-input">
              <Input
                id="url"
                type="text"
                name="url"
                placeholder="Url"
                required
                onChange={(e: any) => {
                  setImageValue(imageValue.concat(e.target.value));
                  onChange(imageValue.concat(e.target.value));
                }}
              />
              <MdAddBox
                onClick={() => setImagenesInput([...imagenesInput, i + 1])}
                fontSize={47}
                color="var(--primary)"
              />
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default CreateImage;
