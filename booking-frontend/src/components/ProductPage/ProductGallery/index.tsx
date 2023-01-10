import { useMemo, useState } from 'react';
import Modal from 'react-modal';
import ImageGallery from 'react-image-gallery';
import useMediaQuery from '../../../hooks/useMediaQuery';
import Button from '../../UI/Button';
import NoImage from '../../../assets/images/no-photo.png';

import {
  MdFavoriteBorder as FavIcon,
  MdOutlineShare as ShareIcon
} from 'react-icons/md';

import './index.css';
import './image-gallery.css';

const customStyles = {
  overlay: {
    zIndex: 1,
    backgroundColor: 'rgba(56, 59, 88, 0.85)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
  },
  content: {
    padding: 0,
    border: 0,
    borderRadius: '8px',
    inset: 'unset',
    width: '640px'
  }
};

interface Props {
  productImages?: any;
}

const ProductGallery = ({ productImages }: Props) => {
  const isMobile = useMediaQuery('(max-width: 576px)');
  const isTablet = useMediaQuery('(max-width: 768px)');
  const [showLightbox, setShowLightbox] = useState(false);

  const imageList = useMemo(
    () =>
      (productImages || [{ id: 1, url: NoImage }]).map((img: any) => ({
        original: img.url,
        thumbnail: img.url
      })),
    [productImages]
  );

  return (
    <div className="product-gallery-container">
      <div className="gallery-actions container">
        <ShareIcon
          fontSize={24}
          color={isMobile ? 'var(--white)' : 'inherit'}
          style={{
            filter: isMobile ? 'drop-shadow(1px 1px 2px var(--secondary))' : ''
          }}
          data-testid="share-icon"
        />
        <FavIcon
          fontSize={24}
          color={isMobile ? 'var(--white)' : 'inherit'}
          style={{
            filter: isMobile ? 'drop-shadow(1px 1px 2px var(--secondary))' : ''
          }}
        />
      </div>
      {isTablet ? (
        <ImageGallery
          items={imageList}
          showThumbnails={false}
          showNav={false}
          showPlayButton={false}
          showFullscreenButton={false}
          autoPlay={imageList.length > 1}
          showIndex={imageList.length > 1}
        />
      ) : (
        <div className={`gallery-container quantity-${imageList.length}`}>
          {imageList.map((img: { original: string | undefined }, i: any) => (
            <img
              key={img.original}
              src={img.original}
              alt={productImages ? '' : 'Sin foto'}
              className={i === 0 ? 'gallery-first-img' : ''}
              onClick={() => setShowLightbox(true)}
            />
          ))}
          {productImages && productImages.length > 5 && (
            <Button
              label="Ver más"
              variant="text"
              color="var(--white)"
              className="gallery-open-button"
              onClick={() => setShowLightbox(true)}
            />
          )}
        </div>
      )}
      {showLightbox && (
        <Modal
          isOpen={showLightbox}
          onRequestClose={() => setShowLightbox(false)}
          contentLabel="Rental gallery"
          style={customStyles}
          ariaHideApp={false}
        >
          <ImageGallery
            items={imageList}
            showPlayButton={false}
            showFullscreenButton={false}
            showIndex={imageList.length > 1}
            showThumbnails={imageList.length > 1}
          />
        </Modal>
      )}
    </div>
  );
};

export default ProductGallery;
