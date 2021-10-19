CREATE TABLE IF NOT EXISTS pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(200) NULL,
    numero VARCHAR(10) NULL,
    complemento VARCHAR(200) NULL,
    bairro VARCHAR(100) NULL,
    cep VARCHAR(20) NULL,
    cidade VARCHAR(100) NULL,
    estado VARCHAR(100) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Jeferson Meira', true, 'Quadra 805 Bloco A', '101', 'Apartamento', 'Cruzeiro Novo', '70655-158', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Jayanne Almeida', true, 'Quadra 101 Bloco D', '405', 'Apartamento', 'Cruzeiro Novo', '70642-487', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Sophia Kríston', true, 'Rua da Bateria', '23', 'Casa', 'Centro', '75982-069', 'Porto Alegre', 'RS');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Eduardo Bailoni', true, 'Avenida Jacarandá Rua Barbosa', '459', null, 'Vila Paulista', '69248-089', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Erivelton Fagner', true, 'Rua da Terra', '52', null, 'Vigilato', '65208-610', 'Manaus', 'AM');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('João Silva', true, 'Rua do Abacaxi', '10', null, 'Centro', '38400-128', 'Uberlândia', 'MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Maria Rita', true, 'Rua do Sabiá', '321', null, 'Jardins', '67236-543', 'Natal', 'RN');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Isabela Martins', true, 'Av Rio Branco', '566', null, 'Segismundo Pereira', '53678-345', 'Uberlândia', 'MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Carlos Santana', true, 'Rua da Manga', '12', null, 'Morumbi', '63789-009', 'Goiânia', 'GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Fabíola Duarte', true, 'Rua da Morenas', '66', null, 'Taguatinga', '18725-298', 'Brasília', 'DF');
