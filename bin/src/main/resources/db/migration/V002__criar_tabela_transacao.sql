CREATE TABLE transacoes (
	id BIGINT NOT NULL AUTO_INCREMENT,
    cartao_id BIGINT NOT NULL,
    date DATETIME not null,
    tipo SMALLINT NOT NULL,
    valor NUMERIC(15,2) NOT NULL,

    FOREIGN KEY (cartao_id) REFERENCES cartoes(id),
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;