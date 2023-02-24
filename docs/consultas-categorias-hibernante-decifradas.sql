select * from categoria;

select
	categoria0_.id as id,
	categoria0_.categoria_pai_id as categoria_pai,
	categoria0_.descricao as descricao
from
	Categoria as categoria0_;
    
select 
	id, 
    categoria_pai_id,
    descricao
from categoria;


----------

insert into categoria (descricao) 
values ("teste");

select
	subcategor0_.categoria_pai_id as categori3_0_0_,
	subcategor0_.id as id1_0_0_,
	subcategor0_.id as id1_0_1_,
	subcategor0_.categoria_pai_id as categori3_0_1_,
	subcategor0_.descricao as descrica2_0_1_ 
from
	Categoria subcategor0_ 
where
	subcategor0_.categoria_pai_id = 1;

delete from categoria
where id = 9;
        
        


