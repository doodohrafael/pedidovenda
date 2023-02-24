delete from categoria where categoria_pai_id is not null;
delete from categoria;

alter table categoria auto_increment = 1;

insert into categoria (descricao) values ('Informática');
insert into categoria (descricao) values ('Eletrodomésticos');
insert into categoria (descricao) values ('Móveis');

insert into categoria (id, descricao, categoria_pai_id) values (4, 'Computadores', 1);
insert into categoria (id, descricao, categoria_pai_id) values (5, 'Notebooks', 1);
insert into categoria (id, descricao, categoria_pai_id) values (6, 'Tablets', 1);
insert into categoria (id, descricao, categoria_pai_id) values (7, 'Monitores', 1);
insert into categoria (id, descricao, categoria_pai_id) values (8, 'Impressoras', 1);
insert into categoria (id, descricao, categoria_pai_id) values (9, 'Acessórios', 1);

insert into categoria (id, descricao, categoria_pai_id) values (10, 'Ar condicionados', 2);
insert into categoria (id, descricao, categoria_pai_id) values (11, 'Fogões', 2);
insert into categoria (id, descricao, categoria_pai_id) values (12, 'Fornos elétricos', 2);
insert into categoria (id, descricao, categoria_pai_id) values (13, 'Microondas', 2);
insert into categoria (id, descricao, categoria_pai_id) values (14, 'Refrigeradores', 2);

insert into categoria (id, descricao, categoria_pai_id) values (15, 'Cadeiras', 3);
insert into categoria (id, descricao, categoria_pai_id) values (16, 'Mesas', 3);
insert into categoria (id, descricao, categoria_pai_id) values (17, 'Racks', 3);
insert into categoria (id, descricao, categoria_pai_id) values (18, 'Sofás', 3);

insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (1, 'Computador Dell', 'AA0001', 40, 2500.00, 4);
insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (2, 'Notebook Acer', 'AA0002', 50, 2250.00, 5);
insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (3, 'Tablet Sansumg', 'AA0003', 80, 1300.00, 6);
insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (4, 'Monitor LG', 'AA0004', 70, 475.00, 7);
insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (5, 'Impressora HP', 'AA0005', 90, 400.00, 8);
insert into produto (id, nome, sku, quantidade_estoque, valor_unitario, categoria_id) values (6, 'Mouse Touch', 'AA0006', 150, 50.00, 9);

insert into cliente (id, nome, email, tipo, doc_receita_federal) values (1, 'Mercado Bem Barato', 'bembarato@gmail.com', 'JURIDICA', '10123123000101');
insert into cliente (id, nome, email, tipo, doc_receita_federal) values (2, 'Lojas Top', 'lojastop@yahoo.com.br', 'JURIDICA', '10123123000102');
insert into cliente (id, nome, email, tipo, doc_receita_federal) values (3, 'José Augusto de Oliveira', 'jose@gmail.com', 'FISICA', '12356478952');
insert into cliente (id, nome, email, tipo, doc_receita_federal) values (4, 'Maria das Neves', 'maria@yahoo.com.br', 'FISICA', '12356478954');

insert into grupo (id, nome, descricao) values (1, 'ADMINISTRADORES', 'Administradores');
insert into grupo (id, nome, descricao) values (2, 'AUXILIARES', 'Auxiliares');
insert into grupo (id, nome, descricao) values (3, 'VENDEDORES', 'Vendedores');

insert into usuario (id, nome, email, senha) values (1, 'Admin', 'admin@gmail.com', '123');
insert into usuario (id, nome, email, senha) values (2, 'Pedro', 'pedro@gmail.com', '123');
insert into usuario (id, nome, email, senha) values (3, 'João', 'joao@gmail.com', '123');
insert into usuario (id, nome, email, senha) values (4, 'Manoel', 'manoel@gmail.com', '123');
insert into usuario (id, nome, email, senha) values (5, 'Ana', 'ana@gmail.com', '123');

insert into usuario_grupo(usuario_id, grupo_id) value (1, 1);
insert into usuario_grupo(usuario_id, grupo_id) value (2, 2);
insert into usuario_grupo(usuario_id, grupo_id) value (3, 3);
insert into usuario_grupo(usuario_id, grupo_id) value (4, 3);
insert into usuario_grupo(usuario_id, grupo_id) value (5, 3);


