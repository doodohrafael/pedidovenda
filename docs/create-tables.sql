    create table Categoria (
       id bigint not null auto_increment,
        descricao varchar(60) not null,
        categoria_pai_id bigint,
        primary key (id)
    ) engine=InnoDB;
    
    create table Cliente (
       id bigint not null auto_increment,
        doc_receita_federal varchar(14) not null,
        email varchar(255) not null,
        nome varchar(100) not null,
        tipo varchar(10) not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table Endereco (
       id bigint not null auto_increment,
        cep varchar(9) not null,
        cidade varchar(60) not null,
        complemento varchar(150),
        logradouro varchar(150) not null,
        numero varchar(20) not null,
        uf varchar(60) not null,
        cliente_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table Grupo (
       id bigint not null auto_increment,
        descricao varchar(80) not null,
        nome varchar(40) not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table item_pedido (
       id bigint not null auto_increment,
        quantidade integer not null,
        valor_unitario decimal(10,2) not null,
        pedido_id bigint not null,
        produto_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table Pedido (
       id bigint not null auto_increment,
        data_criacao datetime(6) not null,
        data_entrega date not null,
        entrega_cep varchar(9) not null,
        entrega_cidade varchar(60) not null,
        entrega_complemento varchar(150),
        entrega_logradouro varchar(150) not null,
        entrega_numero varchar(20) not null,
        entrega_uf varchar(60) not null,
        forma_pagamento varchar(20) not null,
        observacao text,
        status varchar(20) not null,
        valor_desconto decimal(10,2) not null,
        valor_frete decimal(10,2) not null,
        valor_total decimal(10,2) not null,
        cliente_id bigint not null,
        vendedor_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table Produto (
       id bigint not null auto_increment,
        nome varchar(80) not null,
        quantidade_estoque integer not null,
        sku varchar(20) not null,
        valor_unitario decimal(10,2) not null,
        categoria_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table Usuario (
       id bigint not null auto_increment,
        email varchar(255) not null,
        nome varchar(80) not null,
        senha varchar(20) not null,
        primary key (id)
    ) engine=InnoDB;

    
    create table usuario_grupo (
       usuario_id bigint not null,
        grupo_id bigint not null
    ) engine=InnoDB;

    
    alter table Produto 
       drop index UK_y4fhqmugk63t34iim2opfvko;

    
    alter table Produto 
       add constraint UK_y4fhqmugk63t34iim2opfvko unique (sku);

    
    alter table Usuario 
       drop index UK_4tdehxj7dh8ghfc68kbwbsbll;

    
    alter table Usuario 
       add constraint UK_4tdehxj7dh8ghfc68kbwbsbll unique (email);

    
    alter table Categoria 
       add constraint FKl2g2dhhj4me3hpbjd65ytopcj 
       foreign key (categoria_pai_id) 
       references Categoria (id);

    
    alter table Endereco 
       add constraint FK5xgtdcjgh43ld8p50nrnkibku 
       foreign key (cliente_id) 
       references Cliente (id);

    
    alter table item_pedido 
       add constraint FKdyeab92vsr5wlt0vvk68ri2dv 
       foreign key (pedido_id) 
       references Pedido (id);

    
    alter table item_pedido 
       add constraint FKrxvtsysof29a5etjfoemng1ut 
       foreign key (produto_id) 
       references Produto (id);

    
    alter table Pedido 
       add constraint FK1jq79bpskcvo2krkbee2qdqpr 
       foreign key (cliente_id) 
       references Cliente (id);

    
    alter table Pedido 
       add constraint FKtedob1vx7182g6gyoe8bsvk59 
       foreign key (vendedor_id) 
       references Usuario (id);
 
    
    alter table Produto 
       add constraint FKka9jjk42p5jtly14gius2b202 
       foreign key (categoria_id) 
       references Categoria (id);
 
    
    alter table usuario_grupo 
       add constraint FKsbk7vgd004477ti7mht0u7n9d 
       foreign key (grupo_id) 
       references Grupo (id);
 
    
    alter table usuario_grupo 
       add constraint FKewqprkppq0nvuu9ms8l5mlt3h 
       foreign key (usuario_id) 
       references Usuario (id);