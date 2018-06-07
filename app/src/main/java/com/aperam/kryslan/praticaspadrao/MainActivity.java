package com.aperam.kryslan.praticaspadrao;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.domain.PraticasCards;
import com.aperam.kryslan.praticaspadrao.fragments.PraticasFragment;
import com.aperam.kryslan.praticaspadrao.interfaces.SecondActivity;
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

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetTabsBd;


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private AccountHeader headerDrawer;
    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;
    private Context contextoMain = this;

    private List<PrimaryDrawerItem> listCategorias;
    private List<PraticasCards> listPraticas;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            PraticasFragment frag = new PraticasFragment();
            Bundle args = new Bundle();
            if (isChecked) {
                args.putBoolean("formatoLista", true);
            }else{
                args.putBoolean("formatoLista", false);
            }
            frag.setArguments(args);
            FragmentTransaction pt = getSupportFragmentManager().beginTransaction();
            pt.replace(R.id.rl_fragment_container, frag, "mainFrag");
            pt.commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);

        }else{
            listPraticas = getSetPraticasList(50);
        }*/

        mToolbar = findViewById(R.id.my_toolbar);  //Cria a toolbar.
        setSupportActionBar(mToolbar);

        //FRAGMENT
        Bundle args = new Bundle();

        PraticasFragment frag = (PraticasFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){  //Se o fragment não existir, cria e infla ele na tela inicial.
            frag = new PraticasFragment();
            frag.setArguments(args);
            FragmentTransaction pt = getSupportFragmentManager().beginTransaction();
            pt.replace(R.id.rl_fragment_container, frag, "mainFrag");
            pt.commit();
        }

        //TABs
        TabLayout tabLayout = findViewById(R.id.tab);
        String[] nomesTabs = GetTabsBd(contextoMain);
        for (String tabName:nomesTabs) {
            tabLayout.addTab(tabLayout.newTab (). setText(tabName));
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

        listCategorias = getSetCategoryList(); //Aqui é onde desenha os dados no drawer.
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
    private List<PrimaryDrawerItem> getSetCategoryList(){  //Cria a lista de categorias que ficará no drawer.
        String[] nomes = GetTabsBd(contextoMain);
        int[] icons = new int[]{
                R.drawable.area_eminente,
                R.drawable.areas_relacionadas,
                R.drawable.autor,
                R.drawable.data_de_vigencia,
                R.drawable.nivel,
                R.drawable.processo,
                R.drawable.restrito,
                R.drawable.estrela_cheia};
//        int[] iconsSelected = new int[]{
//                R.drawable.area_eminente_selected,
//                R.drawable.areas_relacionadas_selected,
//                R.drawable.autor_selected,
//                R.drawable.data_de_vigencia_selected,
//                R.drawable.nivel_selected,
//                R.drawable.processo_selected,
//                R.drawable.restrito_selected};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < nomes.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.withName( nomes[i] );
            aux.withIcon(getResources().getDrawable(icons[i]));
            aux.withTextColor(getResources().getColor(R.color.PrimaryText));
//            aux.withSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.withSelectedTextColor(getResources().getColor(R.color.colorPrimary));

            list.add( aux );
        }
        return(list);
    }

    public List<PraticasCards> getSetPraticasList(int qtd){  //Informações que ficará nos Cards.
        String[] nome = new String[]{"Inspecionar veículo", "Inspecionar fumaça preta"};
        String[] numeroSetor = new String[]{"27", "27"};
        String[] areaEmitente = new String[]{"Logística Integrada", "Logística Integrada"};
        String[] autor = new String[]{"CRISTINA BORGES P. M. ALTO", "CRISTINA BORGES P. M. ALTO"};
        String[] processo = new String[]{"Logística de Transporte", "Logística de Transporte"};
        String[] urlImagem = new String[]{"https://drive.google.com/uc?id=1k3XiOU8sOtuHO_ainqMOHZbZW6oOVnXf", "https://drive.google.com/uc?id=142z4b4FK-NDu7fnh48DPTVMqbcgaHXeJ"};
        String[] urlDocumento = new String[]{"https://drive.google.com/open?id=1IK_eTLIkkuC30U7lUwbUFHEUjLYThT7_7D4_NCWLhZE", "https://drive.google.com/open?id=1x2XoDPLHRAyH9vej5gCen53YFUL_BncmPGLQ6bp5igY"};
        int[] numeroId = new int[]{270008, 270007};
        List<PraticasCards> listAux = new ArrayList<>();

        for(int i=0; i<qtd; i++){
            PraticasCards p = new PraticasCards(
                    nome[i % nome.length],
                    numeroSetor[i % nome.length],
                    areaEmitente[i % nome.length],
                    autor[i % nome.length],
                    processo[i % nome.length],
                    urlImagem[i % nome.length],
                    urlDocumento[i % nome.length],
                    numeroId[i % nome.length]);
            listAux.add(p);
        }
        return(listAux);
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putInt("mProfileDrawerSelected", mProfileDrawerSelected);
        outState.putParcelableArrayList("listCars", (ArrayList<Car>) listCars);
        outState = navigationDrawerLeft.saveInstanceState(outState);
        outState = headerNavigationLeft.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }*/
}
