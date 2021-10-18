CREATE TABLE IF NOT EXISTS usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@kristonmoney.com', '$2a$10$3L5cf8YtJgBYX.vtLB6SmOXwLYQwuHq6DqqGkAfvXXKX4RQrzethu');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'Jeferson Meira', 'jeferson.meira@kristonmoney.com', '$2a$10$DYbvWUcGRucZWNRC2kIFQOL9Yx3MHdtxK4SjfdfqrZ685G1onViEi');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CATEGORIA_PESQUISAR');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_CATEGORIA_CADASTRAR');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CATEGORIA_ALTERAR');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_CATEGORIA_EXCLUIR');

INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_PESSOA_PESQUISAR');
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_PESSOA_CADASTRAR');
INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_PESSOA_ALTERAR');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_PESSOA_EXCLUIR');

INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_LANCAMENTO_PESQUISAR');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_LANCAMENTO_CADASTRAR');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_LANCAMENTO_ALTERAR');
INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_LANCAMENTO_EXCLUIR');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);

-- jeferson
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 9);