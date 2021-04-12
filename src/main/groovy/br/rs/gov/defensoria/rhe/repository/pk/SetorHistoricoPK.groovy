package br.rs.gov.defensoria.rhe.repository.pk

import java.io.Serializable

class SetorHistoricoPK implements Serializable {
    private String emp_codigo
    private String setor

    public SetorHistoricoPK(){}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SetorHistoricoPK){
            SetorHistoricoPK funcPk = (SetorHistoricoPK) obj

            if(!funcPk.emp_codigo().equals(emp_codigo)){
                return false
            }

            if(!funcPk.setor().equals(setor)){
                return false
            }

            return true
        }

        return false
    }

    @Override
    public int hashCode() {
        return emp_codigo.hashCode() + setor.hashCode()
    }
}
