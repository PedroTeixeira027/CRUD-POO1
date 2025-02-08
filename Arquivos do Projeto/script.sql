CREATE DATABASE cadastro;

USE cadastro;

CREATE TABLE cadastro.cadastro_pessoa (
 id       INT auto_increment NOT NULL,
 nome     varchar(100)       NOT NULL,
 endereco varchar(100)       NOT NULL,
 email    varchar(100)       NULL    ,
 telefone varchar(100)       NULL    ,
 CONSTRAINT cadastro_pessoa_pk PRIMARY KEY (id)
);

select * from cadastro_pessoa;

CREATE TABLE cadastro.uf (
 id    INT auto_increment NOT NULL,
 nome  varchar(100)       NOT NULL,
 sigla varchar(2)         NOT NULL,
 CONSTRAINT cadastro_uf_pk PRIMARY KEY (id)
);

describe uf;
select * from uf;

CREATE TABLE cadastro.fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    endereco VARCHAR(200),
    uf_id int NOT NULL,
    telefone VARCHAR(20),
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    inscricao_estadual VARCHAR(20),
    nome_fantasia VARCHAR(100),
    categoria VARCHAR(50),
    FOREIGN KEY (uf_id) REFERENCES uf(id)
);

select * from fornecedor;
