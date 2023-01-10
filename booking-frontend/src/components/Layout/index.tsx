import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Footer from '../Footer';
import Header from '../Header';
import MobileMenu from '../MobileMenu';
import './index.css';

const Layout = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleOpenMenu = () => setIsMenuOpen(true);

  return (
    <div
      className="layout-container"
      style={{ overflow: isMenuOpen ? 'hidden' : 'auto' }}
      data-testid="layout-container"
    >
      <Header onOpenMenu={handleOpenMenu} />
      <div className="layout-body">
        <div className="layout-body-content">
          <Outlet />
        </div>
      </div>
      <Footer />

      <MobileMenu onClose={() => setIsMenuOpen(false)} isOpen={isMenuOpen} />
    </div>
  );
};

export default Layout;
