package edu.sucho.libreriaweb.util;

import java.util.List;

public class Comparacion <T> {

     public  Boolean IsEqualsLists(List<T> esperados, List<T> actuales) {
        if (esperados.size() != actuales.size()) return false;
        for (T esperado : esperados) {
            if (!actuales.contains(esperado)) {
                return false;
            }
        }return true;
    }




}
