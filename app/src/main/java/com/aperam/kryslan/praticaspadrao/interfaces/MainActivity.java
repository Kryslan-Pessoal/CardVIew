package com.aperam.kryslan.praticaspadrao.interfaces;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.domain.Praticas;
import com.aperam.kryslan.praticaspadrao.fragments.PraticasFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private AccountHeader headerDrawer;
    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;

    private List<PrimaryDrawerItem> listCategorias;
    private List<Praticas> listPraticas;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(MainActivity.this, "onCheckedChanged"+(isChecked? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.my_toolbar);  //Cria a toolbar.
        //mToolbar.setTitle("(Nome da activity)");
        setSupportActionBar(mToolbar);

        //FRAGMENT
        PraticasFragment frag = (PraticasFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){  //Se o fragment não existir, cria e infla ele na tela inicial.
            frag = new PraticasFragment();
            FragmentTransaction pt = getSupportFragmentManager().beginTransaction();
            pt.replace(R.id.rl_fragment_container, frag, "mainFrag");
            pt.commit();
        }

        //HEADER
        headerDrawer = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.aperam_logo)
                .addProfiles(
                        new ProfileDrawerItem().withName("Felipe").withEmail("felipe.paula@aperam.com").withIcon(R.drawable.felipe),
                        new ProfileDrawerItem().withName("Geraldo").withEmail("geraldo.sousa@aperam.com").withIcon(R.drawable.geraldo),
                        new ProfileDrawerItem().withName("Kryslan").withEmail("kryslan.gomes@aperam.com").withIcon(R.drawable.kryslan),
                        new ProfileDrawerItem().withName("Rithiely").withEmail("rithiely.tecchio@aperam.com").withIcon(R.drawable.rithiely)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //Toast.makeText(MainActivity.this, "mudança de profile" + profile, Toast.LENGTH_SHORT).show();
                        //caso eu mude de conta.
                        return false;
                    }
                })
                .build();

        //NAVIGATIONDRAWER
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Fragment frag = null;
                        //mItemDrawerSelected = i;

                        /*if(i == 0){ // ALL CARS
                            frag = new CarFragment();
                        }
                        else if(i == 1){ // LUXURY CAR
                            frag = new LuxuryCarFragment();
                        }
                        else if(i == 2){ // SPORT CAR
                            frag = new SportCarFragment();
                        }
                        else if(i == 3){ // OLD CAR
                            frag = new OldCarFragment();
                        }
                        else if(i == 4){ // POPULAR CAR
                            frag = new PopularCarFragment();
                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();

                        mToolbar.setTitle( ((PrimaryDrawerItem) iDrawerItem).getName() );*/
                        return false;  //Faz o drawer fechar ou não.
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "Clique longo" + position, Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .withAccountHeader(headerDrawer)
                .build();

        listCategorias = getSetCategoryList();
        if(listCategorias != null && listCategorias.size() > 0){
            for(int i = 0; i < listCategorias.size(); i++ ){
                drawer.addItem(listCategorias.get(i));
            }
            drawer.setSelection(mItemDrawerSelected);
        }
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.configuracoes).withIcon(R.drawable.settings));
        drawer.addItem(new SwitchDrawerItem().withName("Lista resumida").withChecked(false).withOnCheckedChangeListener(mOnCheckedChangeListener));
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

    // CATEGORIAS
    private List<PrimaryDrawerItem> getSetCategoryList(){
        String[] nomes = new String[]{
                getResources().getString(R.string.areaEminente),
                getResources().getString(R.string.areasRelacionadas),
                getResources().getString(R.string.autor),
                getResources().getString(R.string.dataDeVigencia),
                getResources().getString(R.string.nivel),
                getResources().getString(R.string.processo),
                getResources().getString(R.string.restrito)};
        int[] icons = new int[]{
                R.drawable.area_eminente,
                R.drawable.areas_relacionadas,
                R.drawable.autor,
                R.drawable.data_de_vigencia,
                R.drawable.nivel,
                R.drawable.processo,
                R.drawable.restrito };
        int[] iconsSelected = new int[]{
                R.drawable.area_eminente_selected,
                R.drawable.areas_relacionadas_selected,
                R.drawable.autor_selected,
                R.drawable.data_de_vigencia_selected,
                R.drawable.nivel_selected,
                R.drawable.processo_selected,
                R.drawable.restrito_selected};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < nomes.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.withName( nomes[i] );
            aux.withIcon(getResources().getDrawable(icons[i]));
            aux.withTextColor(getResources().getColor(R.color.PrimaryText));
            aux.withSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.withSelectedTextColor(getResources().getColor(R.color.colorPrimary));

            list.add( aux );
        }
        return(list);
    }

    public List<Praticas> getSetPraticasList(int qtd){
        String[] nome = new String[]{"Inspecionar veículo", "Inspecionar fumaça preta"};
        String[] numero = new String[]{"27", "27"};
        String[] urlImagem = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        String[] numeroId = new String[]{"PPA 27-0008", "PPA27-0007"};
        List<Praticas> listAux = new ArrayList<>();

        for(int i=0; i<qtd; i++){
            Praticas p = new Praticas(nome[i % nome.length], numero[i % nome.length], urlImagem[i % nome.length], numeroId[i % nome.length]);
            listAux.add(p);
        }
        return(listAux);
    }
}
