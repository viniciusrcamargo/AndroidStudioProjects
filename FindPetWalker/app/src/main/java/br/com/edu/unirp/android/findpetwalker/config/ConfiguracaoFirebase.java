package br.com.edu.unirp.android.findpetwalker.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Vinicius on 19/10/2017.
 */

public final class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;

    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFirebase;
    }
}
