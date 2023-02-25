select *
from categoria;

select *
from categoria
where categoria_pai_id is null;

select *
from categoria
where categoria_pai_id = 2;


select
	categoria.id as id,
	categoria.categoria_pai_id as categori3_0_0_,
	categoria.descricao as descrica2_0_0_,
	subcategoria.id as id1_0_1_,
	subcategoria.categoria_pai_id as categori3_0_1_,
	subcategoria.descricao as descrica2_0_1_ 
from
	Categoria categoria 
left outer join
	Categoria subcategoria 
	on categoria.categoria_pai_id=subcategoria.id 
where
	categoria.id= 2;