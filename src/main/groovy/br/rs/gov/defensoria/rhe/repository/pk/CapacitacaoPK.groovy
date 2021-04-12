package br.rs.gov.defensoria.rhe.repository.pk

import java.io.Serializable

class CapacitacaoPK implements Serializable {
    private int NUMFUNC
    private int EVENTO

    public FuncionalPK(){}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CapacitacaoPK){
            CapacitacaoPK funcPk = (CapacitacaoPK) obj

            if(!funcPk.NUMFUNC().equals(NUMFUNC)){
                return false
            }

            if(!funcPk.EVENTO().equals(EVENTO)){
                return false
            }

            return true
        }

        return false
    }

    @Override
    public int hashCode() {
        return NUMFUNC.hashCode() + EVENTO.hashCode()
    }
}
