create database nextleveldb;

use nextleveldb;

create table tb_clientes(
	id integer auto_increment,
	nome varchar(100),
	email varchar(200),
	cpf varchar(14),
	telefone varchar(20),
    primary key(id)
);

create table tb_enderecos(
	id integer auto_increment,
	cep varchar(9),
	rua varchar(50),
	numero integer(10),
	complemento varchar(1),
	bairro varchar(200),
	cidade varchar(200),
	uf varchar(2),
    id_cliente integer,
    foreign key(id_cliente) references tb_clientes(id) on delete cascade,
	primary key(id)
);

create table tb_categorias(
	id integer auto_increment,
	nome varchar(50),
	primary key(id)
);

create table tb_produtos(
	id integer auto_increment,
	nome varchar(100),
	descricao varchar(400),
	valor float(10),
	peso float(10),
	quantidade integer(10),
	idCategoriaProduto integer,
	primary key(id),
	foreign key(idCategoriaProduto) references tb_categorias(id)
);

create table tb_formapagamento(
	id integer auto_increment,
    nome varchar(255),
    primary key(id)
);

create table tb_pedidos(
	id integer auto_increment,
	id_cliente integer,
	id_formapagamento integer,
    id_produto integer,
    id_endereco integer,
    quantidade integer(10),
	valor_pedido integer(10),
	primary key(id),
    foreign key(id_cliente) references tb_clientes(id),
    foreign key(id_formapagamento) references tb_formapagamento(id),
	foreign key(id_produto) references tb_produtos(id),
    foreign key(id_endereco) references tb_enderecos(id)
);

use nextleveldb;
insert into tb_clientes(cpf, email, nome, telefone) values (1,"e","2","2");

CREATE USER 'teste'@'localhost' IDENTIFIED BY '123456789';
GRANT INSERT ON *.* TO 'teste'@'localhost';
GRANT SELECT ON *.* TO 'teste'@'localhost';
GRANT UPDATE ON *.* TO 'teste'@'localhost';
GRANT DELETE ON *.* TO 'teste'@'localhost';
GRANT ALTER ON *.* TO 'teste'@'localhost';
flush privileges;