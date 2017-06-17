package arquitectura.proyecto.android.appsgpl.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;

public class SplashScreen extends AppCompatActivity {
    private  PreferencesManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefManager = new PreferencesManager(this);

        Thread mythread = new Thread(){
                @Override
                public void run() {

                    try {
                        sleep(3000);
                        if (prefManager.isPrimeraEjecucion()) {
                            prefManager.setPrimeraEjecucion(false);
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mythread.start();


    }

}
