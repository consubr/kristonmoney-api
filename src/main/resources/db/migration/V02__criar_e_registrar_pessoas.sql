CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(200) NULL,
    numero VARCHAR(10) NULL,
    complemento VARCHAR(200) NULL,
    bairro VARCHAR(100) NULL,
    cep VARCHAR(10) NULL,
    cidade VARCHAR(100) NULL,
    estado VARCHAR(100) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values ('Jeferson Meira', true, 'Quadra 805 Bloco A', '101', 'Apartamento', 'Cruzeiro Novo', '70655-158', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values ('Jayanne Almeida', true, 'Quadra 101 Bloco D', '405', 'Apartamento', 'Cruzeiro Novo', '70642-487', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values ('Sophia Kríston', true, 'Quadra 10 Rua N', '23', 'Casa', 'Jardins Mangueiral', '75982-069', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values ('Eduardo Bailoni', false, 'Avenida Jacarandá Rua Barbosa', '4589', null, 'Vila Paulista', '69248-089', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values ('Erivelton Fagner', false, null, null, null, 'Sudoeste', null, 'Brasília', 'DF');