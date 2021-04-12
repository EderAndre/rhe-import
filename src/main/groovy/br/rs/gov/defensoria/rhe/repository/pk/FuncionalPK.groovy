package br.rs.gov.defensoria.rhe.repository.pk

import java.io.Serializable

class FuncionalPK implements Serializable {
    private int numfunc
    private int numvinc

    public FuncionalPK(){}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FuncionalPK){
            FuncionalPK funcPk = (FuncionalPK) obj

            if(!funcPk.numfunc().equals(numfunc)){
                return false
            }

            if(!funcPk.numvinc().equals(numvinc)){
                return false
            }

            return true
        }

        return false
    }

    @Override
    public int hashCode() {
        return numfunc.hashCode() + numvinc.hashCode()
    }
}
