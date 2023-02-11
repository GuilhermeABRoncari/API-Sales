create table servicos (
    id bigint not null auto_increment,
    descricao varchar(255) not null,
    valor decimal(12, 2) not null,

    primary key(id)
);