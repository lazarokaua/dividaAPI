CREATE TABLE dividas (
    id SERIAL PRIMARY KEY,
    cpf_devedor VARCHAR(11) NOT NULL,
    valor_pego NUMERIC(15,2) NOT NULL,
    valor_com_juros NUMERIC(15, 2),
    valor_com_desconto NUMERIC(15, 2)
);