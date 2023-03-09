select *
from produto
order by nome asc;

select *
from categoria;

delete from produto 
where id >= 7 and id <= 21;

insert into produto (nome, sku, quantidade_estoque, valor_unitario, categoria_id) values ('Notebook Acer 14 HD 500', 'NB0001', 4, 1400.00, 4);
insert into produto (nome, sku, quantidade_estoque, valor_unitario, categoria_id) values ('Geladeira Brastemp ASD2342', 'GE2343', 14, 2300.00, 5);
insert into produto (nome, sku, quantidade_estoque, valor_unitario, categoria_id) values ('Tablet Sansumg', 'AA0003', 80, 1300.00, 6);
insert into produto (nome, sku, quantidade_estoque, valor_unitario, categoria_id) values ('Monitor LG', 'AA0004', 70, 475.00, 7);


select *
from
	Produto produto 
inner join
	Categoria categoriaPai 
on produto.categoria_id = categoriaPai.id 
left outer join
	Categoria categoriaFilha 
	on categoriaPai.categoria_pai_id = categoriaFilha.id 
  --  where
       -- produto.sku= '%%' 
    order by
        produto.nome asc;

