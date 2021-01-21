insert into permission (name) values
('CREATE_AD'), ('VIEW_AD'), ('UPDATE_AD'), ('DELETE_AD'), ('POST_COMMENT'),
('READ_COMMENT'),  ('DENY_COMMENT'), ('APPROVE_COMMENT'), ('POST_RATE'), ('READ_RATE'),
('UPDATE_RATE'), ('CREATE_REQUEST'), ('LOGIN'), ('RECEIVE_MESSAGE'), ('REGISTER'),
('RENT_A_CAR'), ('SEARCH'), ('SEND_MESSAGE'), ('UPLOAD_PHOTO'), ('DELETE_RATE'),
('CREATE_AGENT'), ('READ_REQUEST'), ('APPROVE_REQUEST'), ('VIEW_IMAGE'), ('CRUD_CAR_BRAND'),
('CRUD_CAR_MODEL'), ('CRUD_CAR_CLASS'), ('CRUD_FUEL_TYPE'), ('CRUD_GEARSHIFT_TYPE'), ('APPROVE_AGENT'),
('DENY_AGENT'), ('CHANGE_PERMISSION');

insert into authority (name) values ('ROLE_ADMIN'), ('ROLE_AGENT'), ('ROLE_SIMPLE_USER'),
                                    ('ROLE_REVIEWER_USER'), ('ROLE_MESSAGE_USER'), ('ROLE_RENT_USER'), ('ROLE_COMMENT_USER'),
                                    ('ROLE_REQUEST'), ('ROLE_AD_USER');

insert into authorities_permissions (authority_id, permission_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14),
(1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 21), (1, 22), (1, 23), (1, 25), (1, 26), (1, 27), (1, 28), (1, 29), (1, 30), (1, 31), (1, 32),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 19), (2, 22), (2, 23), (2, 24), (2, 12),
(3, 13), (3, 15), (3, 17), (3, 2), (3, 6), (3, 10), (3, 24), (3, 14),
(4, 9), (4, 10), (4, 11), (4, 20), (4, 24),
(5, 14), (5, 18), (5, 24),
(6, 16), (6, 12), (6, 24),
(7, 5), (7, 6), (7, 7), (7, 8), (7, 24),
(8, 22), (8, 24),
(9, 1);

insert into user_entity (id, username, password, has_signed_in, last_password_reset_date, user_role) values
(1, 'admin@gmail.com', '$2a$10$UJEbOrAMWN/bh8tEPHt.Z.fD2RX.T0e0MXNuZEFCEFTNAjHkdAVju', false, '2020-01-01 10:10:11', 2),
(2, 'agent@gmail.com', '$2a$10$zQU7XEdDSMvxt13Xkjs3X.CCY64edvCS0ZXcgqPtU8FhSYVUhtnau', false, '2020-01-01 10:10:12', 1),
(3, 'agent1@gmail.com', '$2a$10$zQU7XEdDSMvxt13Xkjs3X.CCY64edvCS0ZXcgqPtU8FhSYVUhtnau', false, '2020-01-01 10:10:13', 1),
(4, 'customer@gmail.com', '$2a$10$UJEbOrAMWN/bh8tEPHt.Z.fD2RX.T0e0MXNuZEFCEFTNAjHkdAVju', false, '2020-01-01 10:10:14', 0),
(5, 'customer2@gmail.com', '$2a$10$UJEbOrAMWN/bh8tEPHt.Z.fD2RX.T0e0MXNuZEFCEFTNAjHkdAVju', false, '2020-01-01 10:10:15', 0);

insert into user_authority (user_id, authority_id) values
(1, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 7),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 7),
(4, 3),
(4, 5),
(4, 6),
(4, 7);

insert into admin (id, first_name, last_name, user_id) values
(1, 'Ms', 'Misoni', 1);

insert into agent (id, bank_account_number, date_founded, name, pib, user_id, address) values
(1, 'DE72 3702 0500 0009 7097 00', '2020-02-25', 'Marko Kraljevic',  '123-45-6789', 2, 'Svetislava Kasapinovica 3'),
(2, '0500 0009 3702 FE22 7097 00', '2020-02-25', 'Dragan Topalovic', '321-54-9876', 3, 'Knez Mihailova 15');

insert into simple_user (id, address, first_name, last_name, request_status, ssn, user_id, deleted, num_of_ads) values
(1, 'Pionirska 26', 'Somi', 'Misoni', 'CONFIRMED', '1547854896523', 4, false, 0),
(2, 'Njegoseva 55', 'Didi', 'Mimica-Kostovic', 'CONFIRMED', '1547858576523', 5, true, 0);

insert into fuel_type(id, type, tank_capacity) values
(1, 'Diesel', '30L'),
(2, 'Diesel', '40L'),
(3, 'Diesel', '50L'),
(4, 'Diesel', '60L'),
(5, 'Diesel', '70L'),
(6, 'Benzine', '30L'),
(7, 'Benzine', '40L'),
(8, 'Benzine', '50L'),
(9, 'Benzine', '60L'),
(10, 'Benzine', '70L');

insert into gearshift_type(id, type, number_of_gears) values
(1, 'Manuel', 4),
(2, 'Manuel', 5),
(3, 'Manuel', 6),
(4, 'Automatic', 8),
(5, 'Automatic', 10);

insert into car_brand(id, name, country) values
(1, 'BMW', 'Germany'),
(2, 'Audi', 'Germany'),
(3, 'Mercedes', 'Germany'),
(4, 'Volkswagen', 'Germany'),
(5, 'Opel', 'Germany'),
(6, 'Porsche ', 'Germany'),
(7, 'Fiat', 'Italy'),
(8, 'Ferrari', 'Italy'),
(9, 'Volvo', 'Sweden'),
(10, 'Reno', 'France'),
(11, 'Peugeot', 'France'),
(12, 'Citroen', 'France'),
(13, 'Toyota', 'Japan'),
(14, 'Nissan', 'Japan'),
(15, 'Honda', 'Japan');

insert into car_class(id, name) values
(1, 'Hatchback'),
(2, 'Crossover'),
(3, 'Coupe'),
(4, 'SUV'),
(5, 'Pickup'),
(6, 'Van'),
(7, 'City'),
(8, 'Sedan');

insert into car_model(id, name, car_brand_id, car_class_id) values
(1, 'X1', 1, 2), (2, 'Q5', 2, 2), (3, 'Civic', 15, 8),
(4, 'A3', 2, 7), (5, 'Yaris', 13, 7), (6, 'M3', 1, 3),
(7, 'R8', 2, 3), (8, 'GT-R', 14, 3), (9, '911', 6, 3),
(10, 'Juke', 14, 2), (11, 'Macan', 6, 2), (12, 'Q7', 2, 4),
(13, 'X6', 1, 4), (14, 'G-class', 3, 4), (15, 'Series 5', 1, 8),
(16, 'Series 7', 1, 8), (17, 'S-class', 3, 8), (18, 'E-class', 3, 8),
(19, 'Panamera', 6, 8), (20, 'M5', 1, 8), (21, 'Titan', 14, 5),
(22, 'Tacoma', 13, 5), (23, 'Odyssey', 15, 6), (24, 'V-class', 3, 6),
(25, 'NV-3500', 15, 6), (26, 'A1', 2, 1), (27, 'Series 1', 1, 1),
(28, 'Civic', 15, 1), (29, 'Clio', 10, 1), (30, '207', 11, 7);

insert into car(id, km_traveled, car_model_id, fuel_type_id, gearshift_type_id) values
(1, 15000, 1, 8, 4),
(2, 7000, 5, 5, 3),
(3, 150000, 15, 8, 4),
(4, 40000, 12, 9, 3),
(5, 13000, 3, 1, 5),
(6, 10000, 7, 5, 2),
(7, 120000, 29, 4, 2),
(8, 75000, 23, 3, 3),
(9, 3500, 22, 10, 1),
(10, 30000, 14, 8, 1);

insert into discount(id, discount) values
(1, 0),(2, 10),(3, 20), (4, 30), (5, 40), (6, 50), (7, 60), (8, 70), (9, 80), (10, 90);

insert into pricelist(id, price_per_day, price_per_kilometer, price_cdw, discount_id) values
(1, 10, 1, 0, 1),
(2, 15, 2, 10, 3);

insert into ad(id, publisher_id ,car_id, limited_distance, limited_km, cdw, seats, creation_date, simple_user, name, pricelist_id) values
(1, 1, 1, false, 0, false, 4, '2020-07-08', true, 'BMW X1 Crossover',1 ),
(2, 1, 3, false, 0, true, 2, '2020-01-08', false, 'BMW Series5 Sedan', 1),
(3, 1, 5, false, 0, true, 3, '2020-11-23', false, 'Honda Civic Sedan', 1),
(4, 1, 4, true, 40, false, 7, '2020-07-13', false, 'Audi Q7 SUV', 1),
(5, 1, 10, true, 20, false, 8, '2020-02-15', true, 'Mercedes G-Class SUV', 1),
(6, 2, 8, true, 15, true, 4, '2020-03-01', true, 'Honda Odyssey Van', 1),
(7, 2, 9, false, 0, true, 4, '2020-07-03', true, 'Toyota Tacoma Pickup', 1),
(8, 2, 7, false, 0, false, 4, '2020-04-21', true, 'Reno Clio Hatchback', 1),
(9, 2, 2, true, 150, true, 2, '2020-04-22', false, 'Toyota Yaris City', 1),
(10, 2, 6, true, 30, false, 4, '2020-12-01', false, 'Audi R8 Coupe', 1);

insert into bundle(id, publisher_id) values
(1, 1), (2, 2);

insert into reservation (id, from_date, to_date, from_time, to_time, customer_id, ad_id, status, simple_user, bundle_id) values
(1, '2021-01-15', '2021-01-18', '15:32', '12:33', 1, 1, 'PENDING', true, 1),
(2, '2021-01-13', '2021-01-15', '20:00', '20:00', 1, 2, 'PENDING', true, 1),
(3, '2021-02-13', '2021-02-15', '20:00', '20:00', 1, 3, 'PENDING', true, 1),
(4, '2021-02-16', '2021-02-19', '20:00', '20:00', 1, 4, 'PENDING', true, null),
(5, '2021-02-21', '2021-02-21', '08:00', '20:00', 2, 1, 'PENDING', false, 2),
(6, '2021-03-01', '2021-03-15', '20:00', '20:00', 2, 2, 'PENDING', false, 2),
(7, '2021-03-13', '2021-03-21', '20:00', '20:00', 2, 3, 'PENDING', false, null),
(8, '2021-03-19', '2021-03-21', '20:00', '20:00', 2, 6, 'PENDING', true, null),
(9, '2021-03-22', '2021-03-23', '20:00', '20:00', 2, 7, 'PENDING', true, null),
(10, '2021-04-13', '2021-05-15', '20:00', '20:00', 2, 4, 'PENDING', false, null);

insert into comment(id, content, customer_id, simple_user, ad_id, status) values
(1, "Prvi komentar", 2, true, 1, 'PENDING'),
(2, "Drugi komentar", 1, true, 1, 'PENDING'),
(3, "Treci komentar", 4, false, 1, 'DENIED');
