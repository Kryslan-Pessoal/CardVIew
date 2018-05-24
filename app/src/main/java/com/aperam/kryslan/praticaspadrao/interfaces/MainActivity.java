package com.aperam.kryslan.praticaspadrao.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.domain.Praticas;
import com.aperam.kryslan.praticaspadrao.fragments.PraticasFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.my_toolbar);  //Cria a toolbar.
        setSupportActionBar(mToolbar);

        //FRAGMENT
        PraticasFragment frag = (PraticasFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){  //Se o fragment não existir, cria e infla ele na tela inicial.
            frag = new PraticasFragment();
            android.support.v4.app.FragmentTransaction pt = getSupportFragmentManager().beginTransaction();
            pt.replace(R.id.rl_fragment_container, frag, "mainFrag");
            pt.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //Inserindo as opções na toolbar.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.setting:
                startActivity(new Intent(this, SecondActivity.class));  //Manda para a segundo activity.
            default:
                return super.onOptionsItemSelected(item);  //Caso venha algum id desconhecido (só para não dar erro).
        }
    }

    public List<Praticas> getSetPraticasList(int qtd){
        String[] nome = new String[]{"Inspecionar veículo", "Inspecionar fumaça preta"};
        String[] numero = new String[]{"n40", "n20"};
        String[] urlImagem = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        String[] link = new String[]{" ", " "};
        List<Praticas> listAux = new ArrayList<>();

        for(int i=0; i<qtd; i++){
            Praticas p = new Praticas(nome[i % nome.length], numero[i % nome.length], urlImagem[i % nome.length], link[i % nome.length]);
            listAux.add(p);
        }
        return(listAux);
    }
}
