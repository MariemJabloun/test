/****  Customers insert  ****/
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (1,'Thomas', 'Hubert', 'thomas.hubert@free.fr', '0630253255', true, '2021-06-06 14:30:45' );
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (2,'Emmanuel', 'Thomson', 'emmanuel.thom@gmail.fr', '0740254555', true, '2020-09-06 14:30:45' );
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (3,'Mathieu', 'Duglas', 'mathieu.duglas@gmail.com', '0685095232', true, '2023-05-06 14:30:45' );
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (4,'Asma', 'Daoud', 'asma.daoud@yahoo.fr', '0688602341', true, '2019-06-06 14:30:45' );
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (5,'Hajer', 'Roiter', 'hajer.duclay@yahoo.fr', '0611025623', true, '2022-07-06 14:30:45' );
INSERT INTO Customer (id, firstName, lastName, email, phone, active, creationDate) VALUES (6,'Yazel', 'Bernardo', 'yazel.bernardo@gmail.com', '0609022423', true, '2022-07-06 14:30:45' );

/****  Subscriptions insert  ****/
INSERT INTO SUBSCRIPTION (id, name, active, startdate, price, currency, fk_customer, expirydate, recurrency) VALUES (1, 'canalPlus sport', true, '2023-06-06 14:30:45', '25.99', 'euro', 1 , '2024-06-06 14:30:45','monthly');
INSERT INTO SUBSCRIPTION (id, name, active, startdate, price, currency, fk_customer, expirydate, recurrency) VALUES (2, 'canalPlus Series', true, '2023-06-06 14:30:45', '300' , 'euro', 1, '2024-06-06 14:30:45', 'yearly');
INSERT INTO SUBSCRIPTION (id, name, active, startdate, price, currency, fk_customer, expirydate, recurrency) VALUES (3, 'CANAL+ FRIENDS & FAMILY', true, '2023-07-26 14:30:45', '49.99' , 'euro', 3, '2024-06-06 14:30:45', 'monthly');
INSERT INTO SUBSCRIPTION (id, name, active, startdate, price, currency, fk_customer, expirydate, recurrency) VALUES (4, 'canalPlus', true, '2023-07-26 14:30:45', '19.99' , 'euro', 4, '2024-06-06 14:30:45', 'monthly');
INSERT INTO SUBSCRIPTION (id, name, active, startdate, price, currency, fk_customer, expirydate, recurrency) VALUES (5, 'canalPlus', true, '2023-07-26 14:30:45', '19.99' , 'euro', 5, '2024-02-03 14:30:45', 'monthly');