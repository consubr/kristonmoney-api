CREATE TABLE IF NOT EXISTS lancamento (
	codigo BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR (200) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE NULL,
    valor DECIMAL (10,2) NOT NULL,
    observacao VARCHAR (500) NULL,
    tipo VARCHAR (50) NOT NULL,
    codigo_categoria BIGINT (20) NOT NULL,
    codigo_pessoa BIGINT (20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria (codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Salário Mensal', '2021-06-10', null, 6000.00, 'Salário líquido do mês', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Extra', '2021-03-10', '2021-03-10', 1500.00, 'Freelance', 'RECEITA', 3, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Eletrônicos', '2021-05-14', '2021-05-14', 350.50, null, 'DESPESA', 5, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Lanche', '2021-05-21', '2021-05-21', 12.95, null, 'DESPESA', 4, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Cursos', '2021-06-11', null, 359.99, 'Curso de JAVA', 'DESPESA', 5, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Clube', '2021-03-19', null, 100.00, null, 'DESPESA', 3, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Mesada', '2021-06-01', '2021-06-01', 500.00, null, 'RECEITA', 5, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Vendas', '2021-06-17', '2021-06-15', 3000.00, null, 'RECEITA', 4, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Aposta', '2021-04-05', '2021-04-05', 250.50, null, 'RECEITA', 2, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
			VALUES ('Instrumento', '2021-05-28', null, 850.00, 'Compra de um violão', 'DESPESA', 5, 1);