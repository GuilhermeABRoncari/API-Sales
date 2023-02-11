create table clientes (
        id bigint not null auto_increment,
        nome varchar(255) not null,
        endereco varchar(2000),
        fone varchar(20),

        primary key(id)
);