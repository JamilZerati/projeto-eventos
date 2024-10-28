INSERT INTO instituicoes (id, nome, tipo) VALUES (1, 'Instituicao 1', 'TIPO1');

INSERT INTO eventos (evento_id, nome, data_inicial, data_final, ativo, instituicao_id )
VALUES (1, 'Evento 1', '2024-10-01', '2024-10-30',true, 1);

INSERT INTO eventos (evento_id, nome, data_inicial, data_final, ativo, instituicao_id )
VALUES (2, 'Evento 2', '2024-10-01', '2024-10-26',false, 1);