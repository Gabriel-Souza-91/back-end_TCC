create table users(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    cpf char(11) not null unique,
    data_nascimento char(8) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    cidade varchar(100) not null,
    uf char(2) not null,
    numero varchar(20),
    complemento varchar(50),

    primary key(id)
);