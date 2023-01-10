import FacebookLogo from '../../assets/icons/social_facebook.svg';
import LinkedinLogo from '../../assets/icons/social_linkedin.svg';
import TwitterLogo from '../../assets/icons/social_twitter.svg';
import InstagramLogo from '../../assets/icons/social_instagram.svg';
import './index.css';

const Footer = () => {
  return (
    <div className="footer-container">
      <div className="footer-content container">
        <p>©2021 Digital Booking</p>
        <div className="footer-content-social">
          <img src={FacebookLogo} alt="Go to Facebook" />
          <img src={LinkedinLogo} alt="Go to Linkedin" />
          <img src={TwitterLogo} alt="Go to Twitter" />
          <img src={InstagramLogo} alt="Go to Instagram" />
        </div>
      </div>
    </div>
  );
};

export default Footer;
