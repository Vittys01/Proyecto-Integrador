import { ReactNode } from 'react';
import ReactSelect, {
  StylesConfig,
  Props as SelectProps,
  components,
  OptionProps,
  ControlProps
} from 'react-select';
import './index.css';

interface Props extends SelectProps {
  icon?: ReactNode;
  optionIcon?: ReactNode;
  subLabel?: string;
  inputLabel?: string;
}

const customStyles: StylesConfig = {
  option: (provided, state) => ({
    ...provided,
    display: 'flex',
    borderBottom: '1px solid var(--primary)',
    color: 'var(--text-primary)',
    backgroundColor: 'transparent',
    padding: '16px 0',
    fontSize: 16,
    fontWeight: 700,
    zIndex: '1',
    textAlign: 'left',
    ':last-child': {
      borderBottom: 'none'
    }
  }),
  menu: (provided, state) => ({
    ...provided,

    '&::before': {
      content: '" "',
      position: 'absolute',
      bottom: 0,
      left: 0,
      width: '100%',
      height: '40px',
      background:
        'linear-gradient(0deg, rgba(255,255,255,1) 0%, rgba(255,255,255,0) 100%)',
      borderRadius: '0 0 4px 4px',
      zIndex: '1'
    }
  }),
  menuList: provided => ({
    ...provided,
    maxHeight: 254,
    padding: '8px 12px'
  }),
  control: (provided, state) => ({
    ...provided,
    width: '100%',
    minHeight: '40px',
    padding: '0 0 2px 12px',
    fontSize: 14,
    textAlign: 'left',
    border: '1px solid var(--text-primary)',
    borderRadius: '5px',
    boxShadow: 'none',
    '&:hover': {
      border: 'none'
    }
  }),
  dropdownIndicator: (provided, state) => ({
    ...provided,
    transition: 'all .2s ease',
    transform: state.selectProps.menuIsOpen ? 'rotate(180deg)' : ''
  }),
  placeholder: provided => ({
    ...provided,
    whiteSpace: 'nowrap',
    textOverflow: 'ellipsis',
    overflow: 'hidden'
  })
};

const Select = ({
  options,
  name,
  placeholder,
  icon,
  optionIcon,
  inputLabel,
  ...props
}: Props) => {
  const Control = ({ children, ...props }: ControlProps) => (
    <components.Control {...props}>
      {icon}
      {children}
    </components.Control>
  );

  const Option = ({ data, isSelected, label, ...props }: OptionProps<any>) => (
    <components.Option
      data={data}
      isSelected={isSelected}
      label={label}
      {...props}
    >
      {optionIcon && (
        <div className="select-option-icon" data-testid="option-icon">
          {optionIcon}
        </div>
      )}
      <div>
        <p className="select-option-label">{label}</p>
        {data.subLabel && (
          <p className="select-option-sublabel">{data.subLabel}</p>
        )}
      </div>
    </components.Option>
  );

  return (
    <div>
      {inputLabel && (
        <label htmlFor={name} className="input-label">
          {inputLabel}
        </label>
      )}
      <ReactSelect
        name={name}
        options={options}
        styles={customStyles}
        placeholder={placeholder}
        components={{ Control, Option }}
        isClearable
        {...props}
      />
    </div>
  );
};

export default Select;
