--/////////////////////////////////////////////////////////////////////////////////////////////////////ROLES
INSERT INTO authorities  (id) SELECT * FROM (SELECT 'USER' AS id) AS tmp
WHERE NOT EXISTS ( SELECT id FROM authorities WHERE id = 'USER' ) LIMIT 1;

INSERT INTO authorities  (id) SELECT * FROM (SELECT 'ADMIN' AS id) AS tmp
WHERE NOT EXISTS ( SELECT id FROM authorities WHERE id = 'ADMIN' ) LIMIT 1;
--/////////////////////////////////////////////////////////////////////////////////////////////////////ROLES

--/////////////////////////////////////////////////////////////////////////////////////////////////////CATEGORIES
INSERT INTO categories (id, created_at, description, image, name, status) SELECT * FROM (SELECT 1 AS id, CURRENT_TIMESTAMP AS created_at, 'Alojamiento de pequeñas dimensiones, generalmente construido en madera o piedra y situado en entornos naturales alejados de núcleos de población' AS description, 'https://images.unsplash.com/photo-1510798831971-661eb04b3739?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80' AS image, 'Cabañas' AS name, FALSE AS status) AS tmp
WHERE NOT EXISTS ( SELECT id FROM categories WHERE id = 1 ) LIMIT 1;

INSERT INTO categories (id, created_at, description, image, name, status) SELECT * FROM (SELECT 2 AS id, CURRENT_TIMESTAMP AS created_at, 'Vivienda de material, madera, premoldeada, de barro, de piedra o de cualquier otro material constructivo que está emplazada al borde de una playa marítima o a pocos metros de ella.' AS description, 'https://images.unsplash.com/photo-1499793983690-e29da59ef1c2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80' AS image, 'Casas de playa' AS name, FALSE AS status) AS tmp
WHERE NOT EXISTS ( SELECT id FROM categories WHERE id = 2 ) LIMIT 1;

INSERT INTO categories (id, created_at, description, image, name, status) SELECT * FROM (SELECT 3 AS id, CURRENT_TIMESTAMP AS created_at,
 'También denominado apartamento o piso, es una unidad de vivienda que comprende una o más habitaciones diseñadas para proporcionar instalaciones completas para un individuo o una pequeña familia.' AS description, 'https://images.unsplash.com/photo-1523192193543-6e7296d960e4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80' AS image, 'Departamentos' AS name, FALSE AS status) AS tmp
WHERE NOT EXISTS ( SELECT id FROM categories WHERE id = 3 ) LIMIT 1;

INSERT INTO categories (id, created_at, description, image, name, status) SELECT * FROM (SELECT 4 AS id, CURRENT_TIMESTAMP AS created_at, 'Edificación destinada para ser habitada. Puede organizarse en una o varias plantas, y normalmente, aunque no exclusivamente, se refiere a un edificio destinado a vivienda unifamiliar.' AS description, 'https://images.unsplash.com/photo-1605276374104-dee2a0ed3cd6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80' AS image, 'Casas' AS name, FALSE AS status) AS tmp
WHERE NOT EXISTS ( SELECT id FROM categories WHERE id = 4 ) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////CATEGORIES


--/////////////////////////////////////////////////////////////////////////////////////////////////////USERS AND AUTHORITIES
INSERT INTO users (created_at, name, last_name, email, password, status, updated_at)
SELECT *
FROM ( SELECT CURRENT_TIMESTAMP AS created_at, 'Micaela' AS name, 'Balza' AS last_name, 'mica.balza@gmail.com' AS email, '$2a$10$Nhwvhx.b..RDYLPSPSfxxuwxSLM1LhEOkVCjbbHzX5VZGsGk0iii6' AS password, TRUE AS status, CURRENT_DATE AS updated_at) AS tmp
WHERE NOT EXISTS ( SELECT email FROM users WHERE email = 'mica.balza@gmail.com' ) LIMIT 1;

INSERT INTO users (created_at, name, last_name, email, password, status, updated_at)
SELECT *
FROM ( SELECT CURRENT_TIMESTAMP AS created_at, 'user1' AS name, 'user1' AS last_name, 'user1@user1.com' AS email, '$2a$10$Nhwvhx.b..RDYLPSPSfxxuwxSLM1LhEOkVCjbbHzX5VZGsGk0iii6' AS password, TRUE AS status, CURRENT_DATE AS updated_at) AS tmp
WHERE NOT EXISTS ( SELECT email FROM users WHERE email = 'user1@user1.com' ) LIMIT 1;

INSERT INTO users (created_at, name, last_name, email, password, status, updated_at)
SELECT *
FROM ( SELECT CURRENT_TIMESTAMP AS created_at, 'user2' AS name, 'user2' AS last_name, 'user2@user2.com' AS email, '$2a$10$Nhwvhx.b..RDYLPSPSfxxuwxSLM1LhEOkVCjbbHzX5VZGsGk0iii6' AS password, TRUE AS status, CURRENT_DATE AS updated_at) AS tmp
WHERE NOT EXISTS ( SELECT email FROM users WHERE email = 'user2@user2.com' ) LIMIT 1;

INSERT INTO users (created_at, name, last_name, email, password, status, updated_at)
SELECT *
FROM ( SELECT CURRENT_TIMESTAMP AS created_at, 'admin' AS name, 'admin' AS last_name, 'admin@admin.com' AS email, '$2a$10$Nhwvhx.b..RDYLPSPSfxxuwxSLM1LhEOkVCjbbHzX5VZGsGk0iii6' AS password, TRUE AS status, CURRENT_DATE AS updated_at) AS tmp
WHERE NOT EXISTS ( SELECT email FROM users WHERE email = 'admin@admin.com' ) LIMIT 1;


INSERT INTO user_has_authorities (user_id, authority_id)
SELECT * FROM (SELECT users.id AS user_id, authorities.id as authority_id FROM users INNER JOIN authorities
WHERE users.email = 'mica.balza@gmail.com' AND authorities.id = 'USER') AS tmp
WHERE NOT EXISTS (SELECT user_id FROM user_has_authorities INNER JOIN users WHERE user_id = (SELECT users.id FROM users WHERE users.email='mica.balza@gmail.com')) LIMIT 1;

INSERT INTO user_has_authorities (user_id, authority_id)
SELECT * FROM (SELECT users.id AS user_id, authorities.id as authority_id FROM users INNER JOIN authorities
WHERE users.email = 'user1@user1.com' AND authorities.id = 'USER') AS tmp
WHERE NOT EXISTS (SELECT user_id FROM user_has_authorities INNER JOIN users WHERE user_id = (SELECT users.id FROM users WHERE users.email='user1@user1.com')) LIMIT 1;

INSERT INTO user_has_authorities (user_id, authority_id)
SELECT * FROM (SELECT users.id AS user_id, authorities.id as authority_id FROM users INNER JOIN authorities
WHERE users.email = 'user2@user2.com' AND authorities.id = 'USER') AS tmp
WHERE NOT EXISTS (SELECT user_id FROM user_has_authorities INNER JOIN users WHERE user_id = (SELECT users.id FROM users WHERE users.email='user2@user2.com')) LIMIT 1;

INSERT INTO user_has_authorities (user_id, authority_id)
SELECT * FROM (SELECT users.id AS user_id, authorities.id as authority_id FROM users INNER JOIN authorities
WHERE users.email = 'admin@admin.com' AND authorities.id = 'ADMIN') AS tmp
WHERE NOT EXISTS (SELECT user_id FROM user_has_authorities INNER JOIN users WHERE user_id = (SELECT users.id FROM users WHERE users.email='admin@admin.com')) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////USERS AND AUTHORITIES

--/////////////////////////////////////////////////////////////////////////////////////////////////////CHARACTERISTICS
INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Cocina' AS title, '<MdFoodBank />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Cocina' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Wifi' AS title, '<MdOutlineWifi />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Wifi' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Piscina' AS title, '<MdPool />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Pileta' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Televisor' AS title, '<MdTv />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Televisor' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Estacionamiento gratuito' AS title, '<MdDirectionsCarFilled />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Estacionamiento gratuito' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Aire acondicionado' AS title, '<GiSnowflake2 />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Aire acondicionado' ) LIMIT 1;

INSERT INTO `characteristics` (title, icon, create_timestamp, soft_delete)
SELECT *
FROM ( SELECT 'Apto mascotas' AS title, '<MdPets />' AS icon, CURRENT_TIMESTAMP AS create_timestamp, FALSE AS soft_delete) AS tmp
WHERE NOT EXISTS ( SELECT title FROM `characteristics`  WHERE title = 'Apto mascotas' ) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////CHARACTERISTICS

--/////////////////////////////////////////////////////////////////////////////////////////////////////COUNTRIES
INSERT INTO countries (name)
SELECT *
FROM ( SELECT 'Argentina' AS name) AS tmp
WHERE NOT EXISTS ( SELECT name FROM countries  WHERE name  = 'Argentina' ) LIMIT 1;

INSERT INTO countries (name)
SELECT *
FROM ( SELECT 'Colombia' AS name) AS tmp
WHERE NOT EXISTS ( SELECT name FROM countries  WHERE name  = 'Colombia' ) LIMIT 1;
--/////////////////////////////////////////////////////////////////////////////////////////////////////COUNTRIES

--/////////////////////////////////////////////////////////////////////////////////////////////////////PROVINCES
INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Amazonas' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Amazonas' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Antioquia' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Antioquia' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Arauca' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Arauca' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Atlántico' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Atlántico' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Bolívar' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Bolívar' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Boyacá' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Boyacá' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Caldas' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Caldas' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Caquetá' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Caquetá' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Casanare' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Casanare' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Cauca' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Cauca' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Cesar' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Cesar' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Chocó' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Chocó' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Córdoba' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Córdoba' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Cundinamarca' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Cundinamarca' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Guainía' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Guainía' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Guaviare' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Guaviare' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Huila' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Huila' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'La Guajira' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'La Guajira' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Magdalena' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Magdalena' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Meta' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Meta' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Nariño' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Nariño' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Norte' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Norte' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Norte de Santander' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Norte de Santander' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Putumayo' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Putumayo' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Quindío' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Quindío' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Risaralda' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Risaralda' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'San Andrés y Providencia' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'San Andrés y Providencia' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Santander' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Santander' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Sucre' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Sucre' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Tolima' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Tolima' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Valle del Cauca' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Valle del Cauca' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Vaupés' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Vaupés' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Vichada' AS name, id FROM countries WHERE countries.name = 'Colombia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Vichada' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Buenos Aires' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Buenos Aires' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Ciudad Autónoma de Buenos Aires' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Ciudad Autónoma de Buenos Aires' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Catamarca' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Catamarca' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Chaco' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Chaco' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Chubut' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Chubut' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Córdoba' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Córdoba' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Corrientes' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Corrientes' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Entre Ríos' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Entre Ríos' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Formosa' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Formosa' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Jujuy' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Jujuy' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'La Pampa' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'La Pampa' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'La Rioja' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'La Rioja' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Mendoza' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Mendoza' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Misiones' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Misiones' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Neuquén' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Neuquén' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Río Negro' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Río Negro' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Salta' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Salta' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'San Juan' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'San Juan' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'San Luis' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'San Luis' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Santa Cruz' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Santa Cruz' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Santa Fe' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Santa Fe' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Santiago del Estero' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Santiago del Estero' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Tierra del Fuego, Antártida e Islas del Atlántico Sur' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Tierra del Fuego, Antártida e Islas del Atlántico Sur' ) LIMIT 1;

INSERT INTO provinces (name, country_id)
SELECT *
FROM ( SELECT 'Tucumán' AS name, id FROM countries WHERE countries.name = 'Argentina') AS tmp
WHERE NOT EXISTS ( SELECT name FROM provinces WHERE name  = 'Tucumán' ) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////PROVINCES

--/////////////////////////////////////////////////////////////////////////////////////////////////////CITIES

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Cucuta' AS name, id FROM provinces WHERE provinces.name = 'Norte de Santander') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Cucuta' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Cali' AS name, id FROM provinces WHERE provinces.name = 'Valle del Cauca') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Cali' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Bogotá' AS name, id FROM provinces WHERE provinces.name = 'Cundinamarca') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Bogotá' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Pereira' AS name, id FROM provinces WHERE provinces.name = 'Risaralda') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Pereira' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Medellín' AS name, id FROM provinces WHERE provinces.name = 'Antioquia') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Medellín' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'La Plata' AS name, id FROM provinces WHERE provinces.name = 'Buenos Aires') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'La Plata' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Córdoba' AS name, id FROM provinces WHERE provinces.name = 'Córdoba') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Córdoba' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Corrientes' AS name, id FROM provinces WHERE provinces.name = 'Corrientes') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Corrientes' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Rosario' AS name, id FROM provinces WHERE provinces.name = 'Santa Fe') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Rosario' ) LIMIT 1;

INSERT INTO cities (name, province_id)
SELECT *
FROM ( SELECT 'Salta' AS name, id FROM provinces WHERE provinces.name = 'Salta') AS tmp
WHERE NOT EXISTS ( SELECT name FROM cities  WHERE name  = 'Salta' ) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////CITIES


--/////////////////////////////////////////////////////////////////////////////////////////////////////RENTALS
INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 1 AS id, 'Departamento de lujo' AS name,
'Excelente departamento en el centro de la ciudad con muy buenas prestaciones y pocos requisitos' AS description,
1000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Departamentos' AND cities.name = 'La Plata') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 1);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 2 AS id, 'Departamento cómodo' AS name,
'Comodo departamento en las afueras de la ciudad con muy buenas prestaciones' AS description,
2000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Departamentos' AND cities.name = 'La Plata') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 2);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 3 AS id, 'Departamento sencillo' AS name,
'Departamento básico en el centro de la ciudad, pocos requisitos' AS description,
100 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Departamentos' AND cities.name = 'Córdoba') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 3);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 4 AS id, 'Casa de lujo' AS name,
'Excelente Casa en el centro de la ciudad con muy buenas prestaciones y pocos requisitos' AS description,
1000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas' AND cities.name = 'Corrientes') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 4);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 5 AS id, 'Casa cómoda' AS name,
'Comodo Casa en las afueras de la ciudad con muy buenas prestaciones' AS description,
2000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas' AND cities.name = 'Rosario') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 5);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 6 AS id, 'Casa sencilla' AS name,
'Casa básica en el centro de la ciudad, pocos requisitos' AS description,
100 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas' AND cities.name = 'Salta') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 6);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 7 AS id, 'Casa de playa de lujo' AS name,
'Excelente Casa de playa con muy buenas prestaciones y pocos requisitos' AS description,
1000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas de playa' AND cities.name = 'Cucuta') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 7);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 8 AS id, 'Casa de playa cómoda' AS name,
'Comoda Casa de playa en las afueras de la ciudad con muy buenas prestaciones' AS description,
2000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas de playa' AND cities.name = 'Cali') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 8);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 9 AS id, 'Casas de playa sencilla' AS name,
'Casa de playa básica, pocos requisitos' AS description,
100 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Casas de playa' AND cities.name = 'Bogotá') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 9);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 10 AS id, 'Cabaña de lujo' AS name,
'Excelente Cabaña con muy buenas prestaciones y pocos requisitos' AS description,
1000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Cabañas' AND cities.name = 'Bogotá') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 10);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 11 AS id, 'Cabaña cómoda' AS name,
'Comoda Cabaña en las afueras de la ciudad con muy buenas prestaciones' AS description,
2000 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Cabañas' AND cities.name = 'Pereira') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 11);

INSERT INTO rentals (id, name, description, distance, category_id, city_id)
SELECT * FROM
(SELECT 12 AS id, 'Cabaña sencilla' AS name,
'Cabaña básica, pocos requisitos' AS description,
100 AS distance,
categories.id AS category_id,
cities.id AS city_id
FROM categories INNER JOIN cities WHERE categories.name = 'Cabañas' AND cities.name = 'Medellín') AS tmp
WHERE NOT EXISTS (SELECT id FROM rentals WHERE id = 12);

--/////////////////////////////////////////////////////////////////////////////////////////////////////RENTALS


--/////////////////////////////////////////////////////////////////////////////////////////////////////IMAGES
INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 1 AS id, 'https://images.unsplash.com/photo-1567684014761-b65e2e59b9eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8YXBhcnRtZW50c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 1) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 1);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 2 AS id, 'https://images.unsplash.com/photo-1551361415-69c87624334f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 1) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 2);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 3 AS id, 'https://images.unsplash.com/photo-1619542402915-dcaf30e4e2a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 1) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 3);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 4 AS id, 'https://images.unsplash.com/photo-1567684014761-b65e2e59b9eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8YXBhcnRtZW50c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 2) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 4);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 5 AS id, 'https://images.unsplash.com/photo-1551361415-69c87624334f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 2) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 5);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 6 AS id, 'https://images.unsplash.com/photo-1619542402915-dcaf30e4e2a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 2) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 6);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 7 AS id, 'https://images.unsplash.com/photo-1567684014761-b65e2e59b9eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8YXBhcnRtZW50c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 3) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 7);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 8 AS id, 'https://images.unsplash.com/photo-1551361415-69c87624334f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 3) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 8);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 9 AS id, 'https://images.unsplash.com/photo-1619542402915-dcaf30e4e2a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 3) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 9);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 10 AS id, 'https://images.unsplash.com/photo-1505691723518-36a5ac3be353?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 4) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 10);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 11 AS id, 'https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 4) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 11);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 12 AS id, 'https://images.unsplash.com/photo-1512917774080-9991f1c4c750?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvdXNlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 4) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 12);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 13 AS id, 'https://images.unsplash.com/photo-1505691723518-36a5ac3be353?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 5) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 13);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 14 AS id, 'https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 5) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 14);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 15 AS id, 'https://images.unsplash.com/photo-1512917774080-9991f1c4c750?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvdXNlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 5) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 15);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 16 AS id, 'https://images.unsplash.com/photo-1505691723518-36a5ac3be353?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 6) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 16);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 17 AS id, 'https://images.unsplash.com/photo-1480074568708-e7b720bb3f09?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8aG91c2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 6) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 17);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 18 AS id, 'https://images.unsplash.com/photo-1512917774080-9991f1c4c750?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvdXNlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 6) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 18);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 19 AS id, 'https://images.unsplash.com/photo-1511840636560-acee95b3a83f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 7) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 19);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 20 AS id, 'https://images.unsplash.com/photo-1517541866997-ea18e32ea9e9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 7) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 20);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 21 AS id, 'https://images.unsplash.com/photo-1551524164-687a55dd1126?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 7) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 21);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 22 AS id, 'https://images.unsplash.com/photo-1511840636560-acee95b3a83f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 8) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 22);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 23 AS id, 'https://images.unsplash.com/photo-1517541866997-ea18e32ea9e9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 8) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 23);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 24 AS id, 'https://images.unsplash.com/photo-1551524164-687a55dd1126?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 8) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 24);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 25 AS id, 'https://images.unsplash.com/photo-1511840636560-acee95b3a83f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 9) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 25);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 26 AS id, 'https://images.unsplash.com/photo-1517541866997-ea18e32ea9e9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 9) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 26);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 27 AS id, 'https://images.unsplash.com/photo-1551524164-687a55dd1126?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG91c2UlMjBiZWFjaHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 9) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 27);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 28 AS id, 'https://images.unsplash.com/photo-1449158743715-0a90ebb6d2d8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 10) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 28);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 29 AS id, 'https://images.unsplash.com/photo-1587061949409-02df41d5e562?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 10) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 29);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 30 AS id, 'https://images.unsplash.com/photo-1575403071235-5dcd06cbf169?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 10) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 30);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 31 AS id, 'https://images.unsplash.com/photo-1449158743715-0a90ebb6d2d8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 11) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 31);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 32 AS id, 'https://images.unsplash.com/photo-1587061949409-02df41d5e562?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 11) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 32);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 33 AS id, 'https://images.unsplash.com/photo-1575403071235-5dcd06cbf169?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 11) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 33);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 34 AS id, 'https://images.unsplash.com/photo-1449158743715-0a90ebb6d2d8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 12) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 34);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 35 AS id, 'https://images.unsplash.com/photo-1587061949409-02df41d5e562?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 12) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 35);

INSERT INTO images (id, url, rental_id)
SELECT * FROM
(SELECT 36 AS id, 'https://images.unsplash.com/photo-1575403071235-5dcd06cbf169?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2FiYSVDMyVCMWF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60' AS url, rentals.id AS rental_id FROM rentals WHERE rentals.id = 12) AS tmp
WHERE NOT EXISTS (SELECT id FROM images WHERE id = 36);

--/////////////////////////////////////////////////////////////////////////////////////////////////////IMAGES


--/////////////////////////////////////////////////////////////////////////////////////////////////////RENTAL_CHARACTERISTIC

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 1 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 1 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 2 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 2 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 3 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 3 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 4 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 4 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 5 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 5 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 6 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 6 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 7 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 7 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 8 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 8 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 9 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 9 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 10 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 10 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 11 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 11 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Cocina') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Cocina')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Wifi') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Wifi')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Pileta') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Pileta')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Televisor') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Televisor')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Estacionamiento gratuito') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Estacionamiento gratuito')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Aire acondicionado') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Aire acondicionado')) LIMIT 1;
INSERT INTO rental_characteristic (rental_id, characteristic_id)
SELECT * FROM (SELECT rentals.id AS rental_id, characteristics.id AS characteristic_id FROM rentals INNER JOIN characteristics
WHERE rentals.id = 12 AND characteristics.title  = 'Apto mascotas') AS tmp
WHERE NOT EXISTS (SELECT rental_id, characteristic_id FROM rental_characteristic WHERE
rental_id = 12 AND characteristic_id = (SELECT characteristics.id FROM characteristics WHERE characteristics.title = 'Apto mascotas')) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////RENTAL_CHARACTERISTIC

--/////////////////////////////////////////////////////////////////////////////////////////////////////BOOKING


INSERT INTO bookings (id, check_in, check_in_time, check_out, created_at, status, updated_at, rental_id, user_id) SELECT * FROM (SELECT 1 AS id, '2022-12-01' AS check_in, '10:30:00' AS check_in_time, '2022-12-05' AS check_out, '2022-12-08' AS created_at, 1 AS status, '2022-12-08' AS updated_at, 1 AS rental_id, 1 AS user_id) AS tmp WHERE NOT EXISTS (SELECT * FROM bookings WHERE bookings.id = 1) LIMIT 1;
INSERT INTO bookings (id, check_in, check_in_time, check_out, created_at, status, updated_at, rental_id, user_id) SELECT * FROM (SELECT 2 AS id, '2022-12-06' AS check_in, '10:30:00' AS check_in_time, '2022-12-10' AS check_out, '2022-12-08' AS created_at, 1 AS status, '2022-12-08' AS updated_at, 4 AS rental_id, 1 AS user_id) AS tmp WHERE NOT EXISTS (SELECT * FROM bookings WHERE bookings.id = 2) LIMIT 1;
INSERT INTO bookings (id, check_in, check_in_time, check_out, created_at, status, updated_at, rental_id, user_id) SELECT * FROM (SELECT 3 AS id, '2022-12-11' AS check_in, '10:30:00' AS check_in_time, '2022-12-15' AS check_out, '2022-12-08' AS created_at, 1 AS status, '2022-12-08' AS updated_at, 7 AS rental_id, 1 AS user_id) AS tmp WHERE NOT EXISTS (SELECT * FROM bookings WHERE bookings.id = 3) LIMIT 1;
INSERT INTO bookings (id, check_in, check_in_time, check_out, created_at, status, updated_at, rental_id, user_id) SELECT * FROM (SELECT 4 AS id, '2022-12-16' AS check_in, '10:30:00' AS check_in_time, '2022-12-20' AS check_out, '2022-12-08' AS created_at, 1 AS status, '2022-12-08' AS updated_at, 10 AS rental_id, 1 AS user_id) AS tmp WHERE NOT EXISTS (SELECT * FROM bookings WHERE bookings.id = 4) LIMIT 1;

--/////////////////////////////////////////////////////////////////////////////////////////////////////BOOKING
