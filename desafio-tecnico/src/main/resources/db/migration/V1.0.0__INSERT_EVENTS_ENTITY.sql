CREATE TABLE instituicoes (
                              instituicao_id SERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              tipo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS eventos (
    evento_id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicial DATE NOT NULL,
    data_final DATE NOT NULL,
    ativo BOOLEAN NOT NULL,
    instituicao_id BIGINT NOT NULL,
    FOREIGN KEY (instituicao_id) REFERENCES instituicoes(instituicao_id) ON DELETE CASCADE
);

