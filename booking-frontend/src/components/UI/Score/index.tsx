import './index.css';

interface Props {
  value: number;
  isHorizontal?: boolean;
}

const Score = ({ value, isHorizontal }: Props) => {
  const getDescription = (score: number) => {
    if (score < 5) {
      return 'Regular';
    } else if (score < 8) {
      return 'Bueno';
    } else {
      return 'Muy Bueno';
    }
  };

  return (
    <div
      className={`score-container ${
        isHorizontal ? 'score-container-horizontal' : ''
      }`}
    >
      <p className="score-value">{value}</p>
      <p className="score-description">{getDescription(value)}</p>
    </div>
  );
};

export default Score;
