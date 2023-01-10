import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Register from './pages/Register';
import Home from './pages/Home';
import Login from './pages/Login';
import Product from './pages/Product';
import SuccessfulReservation from './pages/Successful/Reserv';
import Reservation from './pages/Reservation';
import { User } from './types/user';
import useFetch from './hooks/useFetch';
import { UserContext } from './contexts/UserContext';
import Administration from './pages/Administration';
import SuccessfulProduct from './pages/Successful/Adm';
import MyReservations from './pages/MyReservations';

function App() {
  const [user, setUser] = useState<User | null>(null);

  const { data } = useFetch<any>({
    initialUrl: `http://3.21.170.194:8080/api/user/account`,
    skip: !!user?.name || !localStorage.getItem('token'),
    withAuth: true,
    onSuccess: data => {
      setUser({
        id: data.id,
        name: data.name,
        lastName: data.last_name,
        email: data.email,
        role: data.authorities[0]
      });
    }
  });

  return (
    <div className="App">
      <UserContext.Provider
        value={{
          user,
          setUser
        }}
      >
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="product/:productId" element={<Product />} />
            <Route
              path="reservation-success"
              element={<SuccessfulReservation />}
            />
            <Route
              path="product/:productId/reservation"
              element={<Reservation />}
            />
            <Route path="myreservations" element={<MyReservations />} />
            <Route path="administration" element={<Administration />} />
            <Route path="successful-product" element={<SuccessfulProduct />} />
            <Route path="*" element={<p>404</p>} />
          </Route>
        </Routes>
      </UserContext.Provider>
    </div>
  );
}

export default App;
