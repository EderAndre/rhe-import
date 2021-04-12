package br.rs.gov.defensoria.rhe.utils

import br.rs.gov.defensoria.rhe.utils.Type
import org.springframework.stereotype.Component

@Component
class TypesDefinition {
    public static Type AFASTAMENTO=new Type(
    name:'Afastamentos',
    expressionFromDetection:'_AFAST-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[
        1,
        16,
        32,
        33,
        43,
        53,
        113,
        123
    ],
    columnLabels:[
        "OPERACAO",
        "CHAVE",
        "NUMFUNC",
        "NUMVINC",
        "DTINI",
        "DTFIM",
        "MOTIVO",
        "DTPREVFIM"]
    )
    
    public static Type EVENT_DELETED=new Type(
        name:'Eventos Exluídos',
        expressionFromDetection:'EXPDADOS_EVENTOS-.\\d{6}.[_|.]',
        extension:'TXT',
        columnLimits:[
            1,
            16,
            17,
            26,
            34,
            40,
            50,
            60,
            80,
            90
        ],
        columnLabels:[
            "OPERACAO",
            "NUMEV",
            "TIPO",
            "NUMFUNC",
            "NUMVINC",
            "CARGO",
            "DTINI",
            "DTFIM",
            "MOTIVO",
            "REFERENCIA"]
        )
    public static Type DIRETORES_REGIONAIS=new Type(
        name:'DiretoresRegionais',
        expressionFromDetection:'_COMPL_DIRETORES_REGIONAIS_E_SUBSTITUTOS-.\\d{6}.[_|.]',
        extension:'CSV'
        )


    public static Type CARGOS=new Type(
    name:'Cargos',
    expressionFromDetection:'_MESTRES_CARGOS-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[
        6,
        76,
        116,
        117,
        137,
        157,
        167
    ],
    columnLabels:[
        "COD_CARGO_FUNCAO",
        "NOME_CARGO_FUNCAO",
        "TIPO_CARGO",
        "CARGO_FUNCAO",
        "CATEGORIA",
        "SUBCATEGORIA",
        "DT_EXTINCAO"]
    )

    public static Type DADOS_PUBLICACAO=new Type(
    name:'DadosPublicacao',
    expressionFromDetection:'_COMPL_DADOS_PUBLICACAO-.\\d{6}.[_|.]',
    extension:'CSV'
    )

    public static Type EVENTO=new Type(
    name:'DadosEventos',
    expressionFromDetection:'_COMPL_EVENTO-.\\d{6}.[_|.]',
    extension:'CSV'
    )

    public static Type FUNCIONAL=new Type(
    name:'DadosFuncionais',
    expressionFromDetection:'_COMPL_FUNCIONAL-.\\d{6}.[_|.]',
    extension:'CSV'
    )

    public static Type MUNICIPIOS=new Type(
    name:'MestreCidades',
    expressionFromDetection:'_MESTRES_MUNICIPIOS-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[5, 35],
    columnLabels:[
        "ID_CIDADE",
        "NOME_CIDADE"]
    )

    public static Type PAIS=new Type(
    name:'MestrePais',
    expressionFromDetection:'_MESTRES_PAIS-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[5, 35],
    columnLabels:[
        "ID_PAIS",
        "NACIONALIDADE"]
    )

    public static Type SETOR=new Type(
    name: 'MestreSetor',
    expressionFromDetection:'COMPL_SETOR_OLD-.\\d{6}.[_|.]',
    extension:'CSV'
    )
    
    public static Type SETOR_HISTORICO=new Type(
        name: 'MestreSetorHistorico',
        expressionFromDetection:'COMPL_SETOR-.\\d{6}.[_|.]',
        extension:'CSV'
        )

    public static Type SUBCATEGORIA=new Type(
    name:'MestreSubcateg',
    expressionFromDetection:'_MESTRES_SUBCATEG-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[20, 40],
    columnLabels:["CATEGORIA", "SIGLA"])

    public static Type TIPOEVENTOS=new Type(
    name:'MestreTipoEventos',
    expressionFromDetection:'_MESTRES_TIPOEVENTOS-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[20, 80, 120, 140, 180],
    columnLabels:[
        "TIPOEVENTO",
        "NOME",
        "NATUREZA",
        "NATUREZA_PRINCIPAL",
        "TIPO_CARGO"]
    )

    public static Type TRINOMIOS=new Type(
    name:'MestreTrinomios',
    expressionFromDetection:'_MESTRES_TRINOMIO-.\\d{6}.[_|.]',
    extension:'TXT',
    columnLimits:[20, 40, 60],
    columnLabels:[
        "TIPOVINC",
        "CATEGORIA",
        "REGIMEJUR"]
    )
    public static Type VINCULO=new Type(
    name:'DadosVinculo',
    expressionFromDetection:'_COMPL_VINCULO-.\\d{6}.[_|.]',
    extension:'CSV'
    )
    public static Type FERIAS=new Type(
        name:'Ferias',
        expressionFromDetection:'_COMPL_FERIAS-.\\d{6}.[_|.]',
        extension:'CSV'
    )
    public static Type FERIAS_PENDENTES=new Type(
            name:'FeriasPendentes',
            expressionFromDetection:'_COMPL_FERIAS_PEND-.\\d{6}.[_|.]',
            extension:'CSV'
    )
    public static Type LICENCA_PREMIO=new Type(
        name:'LicencaPremio',
        expressionFromDetection:'_COMPL_LIC_PREMIO-.\\d{6}.[_|.]',
        extension:'CSV'
    )
    public static Type LICENCA_PREMIO_PENDENTE=new Type(
            name:'FeriasPendentes',
            expressionFromDetection:'_COMPL_LIC_PREMIO_PEND-.\\d{6}.[_|.]',
            extension:'CSV'
    )
    public static Type FERIAS_FULL=new Type(
        name:'FeriasFull',
        expressionFromDetection:'__FERIAS_FULL-.\\d{6}.[_|.]',
        extension:'CSV'
    )
    public static Type CAPACITACAO=new Type(
        name:'Capacitacao',
        expressionFromDetection:'_COMPL_CAPACITACOES-.\\d{6}.[_|.]',
        extension:'CSV'
    )
    public static Type FORMACOES=new Type(
        name:'Formacoes',
        expressionFromDetection:'_COMPL_FORMACOES-.\\d{6}.[_|.]',
        extension:'CSV'
    )
}
