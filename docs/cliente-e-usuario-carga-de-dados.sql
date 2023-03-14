select *
from usuario;

select *
from cliente;

delete from cliente where id = 2;

insert into cliente (doc_receita_federal, email, nome, tipo)
values ('10769148450', 'rafael@gmail.com', 'Rafael', 'FISICA');

insert into usuario (email, nome, senha)
values ('doodohrafael@gmail.com', 'Douglas Rafael', 123456);
