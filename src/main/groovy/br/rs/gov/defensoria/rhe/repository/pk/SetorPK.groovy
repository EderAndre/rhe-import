package br.rs.gov.defensoria.rhe.repository.pk

import java.io.Serializable

class SetorPK implements Serializable {
    private String emp_codigo
    private String setor

    public SetorPK(){}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SetorPK){
            SetorPK funcPk = (SetorPK) obj

            if(!setorPk.emp_codigo().equals(emp_codigo)){
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
