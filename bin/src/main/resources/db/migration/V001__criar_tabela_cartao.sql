CREATE TABLE cartoes (
	id BIGINT NOT NULL AUTO_INCREMENT,
    numero VARCHAR(60) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;