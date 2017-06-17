package arquitectura.proyecto.android.appsgpl.util;

import android.content.Context;
import android.content.SharedPreferences;

import static arquitectura.proyecto.android.appsgpl.Activities.Login.id;

/**
 * Created by daniel on 23/03/2017.
 */

public class PreferencesManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    private static final String NAME_PREFERENCE = "estado_intro";
    private static final String PRIMERA_EJECUCION = "estado";

    public PreferencesManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(NAME_PREFERENCE,Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setPrimeraEjecucion(boolean isFirstTime) {
        editor.putBoolean(PRIMERA_EJECUCION, isFirstTime);
        editor.commit();
    }

    public boolean isPrimeraEjecucion() {

        return pref.getBoolean(PRIMERA_EJECUCION,true);
    }
    public void saveIdEmpresa(String id){
        editor.putString("idEmpresa",id);
        editor.commit();
    }
    public void setIdEmpresaSP(){
        pref.edit().remove("idEmpresa").commit();
    }


}
