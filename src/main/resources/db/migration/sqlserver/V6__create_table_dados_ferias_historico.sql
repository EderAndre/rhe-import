CREATE TABLE rhe_dados_ferias_historico(
    ROWID bigint IDENTITY,
    NUMFUNC int NULL,
    NUMVINC int NULL,
    DTINI date NULL,
    DTFIM date NULL,
    MESANOPAGTO varchar(50) NULL,
    NRODIASPAGTO varchar(50) NULL,
    DTINIPA date NULL,
    DTFIMPA date NULL,
    TOTALDIAS int NULL,
    DIASVENDIDOS int NULL,
    DIASGOZADOS int NULL,
    SALDO int NULL,
    OBS varchar(510) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);
CREATE TABLE rhe_dados_ferias_pend_historico(
    ROWID bigint IDENTITY,
    NUMFUNC int NULL,
    NUMVINC int NULL,
    DTINI date NULL,
    DTFIM date NULL,
    TOTALDIAS int NULL,
    DIAS_TIRADOS int NULL,
    DIAS_VENDIDOS int NULL,
    DIAS_CONVERTIDOS int NULL,
    SALDO int NULL,
    DT_PRESCRICAO date NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);
