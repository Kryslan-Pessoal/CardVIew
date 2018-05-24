package com.aperam.kryslan.praticaspadrao.interfaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.aperam.kryslan.praticaspadrao.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;  //Para a próxima linha não dar erro, pois se o actionBar não existe ele daria erro sem esse comando.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Volta para o home.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
