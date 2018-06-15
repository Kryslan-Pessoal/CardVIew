package com.aperam.kryslan.praticaspadrao;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
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

import com.aperam.kryslan.praticaspadrao.BancoDeDados.BD;
import com.aperam.kryslan.praticaspadrao.adapters.ViewPagerAdapter;
import com.aperam.kryslan.praticaspadrao.domain.PraticasCards;
import com.aperam.kryslan.praticaspadrao.interfaces.PraticasActivity;
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
    private Context contextoMain = this;

    private List<PraticasCards> listPraticas;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){  //Listener do "Lista resumida" no drawer.
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {}
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

        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);  //Cria a Toolbar.

        //TABs
        final ViewPager viewPager = findViewById(R.id.pager);
        final TabLayout tabLayout = findViewById(R.id.tab);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), BD.GetTabsBd(contextoMain)));  //Criando as Tabs com seus nomes baseado no BD.
        tabLayout.setupWithViewPager(viewPager);  //Vinculando o viewPager com o tabLayout.

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setHorizontalFadingEdgeEnabled(true);  //Aplica efeito de aparição e desaparição suave no rolamento do tabLayout na parte da esquerda.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {}
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //HEADER
        AccountHeader headerDrawer = new AccountHeaderBuilder()
                .withActivity(this)  //Contexto da activity onde o drawer será criado.
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
                        return false;  //false: fecha o drawe. Verdadeiro: não fecha.
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
                    public boolean onItemClick(View view, final int i, IDrawerItem drawerItem) {
                        if (i < 9) {  //Para não dar erro, pois se selecionar configurações no drawer por exemplo, ele vai dar erro pois não existe essa tab.
                            viewPager.setCurrentItem(i - 1);  //muda a posição da Tab e página selecionada sempre que mudar a posição no Drawer.
                        }
                        return false;  //Faz o drawer fechar ou não. (false fecha)
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "Clique longo" + position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withAccountHeader(headerDrawer)
                .build();

        List<PrimaryDrawerItem> listCategorias = getSetCategoryList();  //Cria a lista de categorias que fica no Drawer.
        if(listCategorias != null && listCategorias.size() > 0){
            for(int i = 0; i < listCategorias.size(); i++ ){
                drawer.addItem(listCategorias.get(i));  //Adiciona cada item no Drawer.
            }
//            drawer.setSelection(mItemDrawerSelected);
        }
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.configuracoes).withIcon(R.drawable.settings));
        //drawer.addItem(new SwitchDrawerItem().withName("Lista resumida").withChecked(false).withOnCheckedChangeListener(mOnCheckedChangeListener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //Inserindo as opções na toolbar.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);  //Quando clicar nos itens do menu.
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
