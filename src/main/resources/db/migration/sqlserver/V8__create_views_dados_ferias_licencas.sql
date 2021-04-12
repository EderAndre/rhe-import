CREATE VIEW VIEW_FERIAS_ATUAL AS SELECT * FROM rhe_dados_ferias_historico WHERE CAST(CREATED as date)=(SELECT MAX(CAST(CREATED as date))  FROM rhe_dados_ferias_historico);
GO
CREATE VIEW VIEW_FERIAS_PEND_ATUAL AS SELECT * FROM rhe_dados_ferias_pend_historico WHERE CAST(CREATED as date)=(SELECT MAX(CAST(CREATED as date))  FROM rhe_dados_ferias_pend_historico);
GO
CREATE VIEW VIEW_LICENCA_PR_ATUAL AS SELECT * FROM rhe_dados_lic_premio_historico WHERE CAST(CREATED as date)=(SELECT MAX(CAST(CREATED as date))  FROM rhe_dados_lic_premio_historico);
GO
CREATE VIEW VIEW_LICENCA_PR_PEND_ATUAL AS SELECT * FROM rhe_dados_lic_premio_pend_historico WHERE CAST(CREATED as date)=(SELECT MAX(CAST(CREATED as date))  FROM rhe_dados_lic_premio_pend_historico);