insert into role(id,nome) values (1, 'ROLE_USER');
insert into role(id,nome) values (2, 'ROLE_ADMIN');

insert into user(id,nome,email,login,senha) values (1,'Ricardo Lecheta','rlecheta@gmail.com','rlecheta','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');
insert into user(id,nome,email,login,senha) values (2,'Admin','admin@gmail.com','admin','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');
insert into user(id,nome,email,login,senha) values (3,'User','user@gmail.com','user','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');


insert into user_roles(user_id,role_id) values(1, 1);
insert into user_roles(user_id,role_id) values(2, 2);
insert into user_roles(user_id,role_id) values(3, 1);

insert into carro (nome,tipo) VALUES('Tucker 1948', 'classicos');
insert into carro (nome, tipo) VALUES('Chevrolet Corvette', 'classicos');
insert into carro (nome,tipo) VALUES('Chevrolet Impala Coupe','classicos');

insert into carro (nome, tipo) VALUES('Ferrari FF', 'esportivos');
insert into carro (nome, tipo) VALUES('AUDI GT Spyder', 'esportivos');
insert into carro (nome, tipo) VALUES('Porsche Panamera', 'esportivos');

insert into carro (nome, tipo) VALUES('Bugatti Veyron', 'luxo');
insert into carro (nome, tipo) VALUES('Ferrari Enzo', 'luxo');
insert into carro (nome, tipo) VALUES('Lamborghini Reventon', 'luxo');
