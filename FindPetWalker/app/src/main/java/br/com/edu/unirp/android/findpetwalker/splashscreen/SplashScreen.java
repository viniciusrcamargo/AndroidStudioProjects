package br.com.edu.unirp.android.findpetwalker.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import br.com.edu.unirp.android.findpetwalker.PrincipalActivity;
import br.com.edu.unirp.android.findpetwalker.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarInicial();
            }
        }, 3000);
    }

    private void mostrarInicial(){
        Intent intent = new Intent(SplashScreen.this,
                PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}
