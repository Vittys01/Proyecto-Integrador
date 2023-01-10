import { createContext } from 'react';

const UserContext = createContext<any>({
  user: null,
  setUser: () => {}
});

export { UserContext };
