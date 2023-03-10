select *
from pedido;

select *
from hibernate_sequence;

delete
from pedido
where id = 3;

select localtime();

insert into pedido 
	(data_criacao, data_entrega, entrega_cep, entrega_cidade, entrega_logradouro, 
	entrega_numero, entrega_uf, forma_pagamento, status, valor_desconto, valor_frete, valor_total, cliente_id, vendedor_id)
values (localtime(), '2023-03-15', 53435070, 'Paulista', 'Rua quipapá', '294', 'PE', 'CARTAO_CREDITO', 'ORCAMENTO', 23.75, 4.49, 53.66, 1, 1);

-- '2023-03-09 11:29:30'