export interface Country {
  id: number;
  name: string;
}

export interface Province {
  id: number;
  name: string;
  country: Country;
}

export interface City {
  id: number;
  name: string;
  province: Province;
}
