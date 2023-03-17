select *
from pedido;

select *
from item_pedido;

delete
from pedido
where id = 12;

select localtime();

insert into pedido 
	(data_criacao, data_entrega, entrega_cep, entrega_cidade, entrega_logradouro, 
	entrega_numero, entrega_uf, forma_pagamento, status, valor_desconto, valor_frete, valor_total, cliente_id, vendedor_id)
values (localtime(), '2023-03-30', 00000000, 'Paulista-teste', 'Rua teste', '001', 'PE', 'CARTAO_CREDITO', 'ORCAMENTO', 0.00, 0.00, 50.00, 1, 1);

-- '2023-03-09 11:29:30'