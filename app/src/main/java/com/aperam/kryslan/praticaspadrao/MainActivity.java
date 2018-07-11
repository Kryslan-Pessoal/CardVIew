package com.aperam.kryslan.praticaspadrao;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.ViewPagerAdapter;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.aperam.kryslan.praticaspadrao.tools.OnClearFromRecentService;
import com.mikepenz.materialdrawer.AccountHeader;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BdMainActivity.GetTabsBd;

public class MainActivity extends AppCompatActivity {
    private Context c = this;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ANIMAÇÃO.
        TransitionInflater inflater = TransitionInflater.from(this);  //Recebe a animação quando está em outra activity e volta para esta.
        Transition transition = inflater.inflateTransition(R.transition.transitions);

        getWindow().setSharedElementExitTransition(transition);

        //MAIN
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BdLite(c);  //Se BD não existe, cria ele.
//        if(savedInstanceState != null){
//            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
//
//        }else{
//            listPraticas = getSetPraticasList(50);
//        }

        //INICIA O SERVIÇO QUE OBSERVA SE O APP FOI FECHADO, SE SIM, MATA O LISTENER DE SCREENSHOT.
        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));

        //TOOLBAR
        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);  //Cria a Toolbar.

        //TABs
        viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), GetTabsBd(c)));  //Criando as Tabs com seus nomes baseado no BdLite.
        tabLayout.setupWithViewPager(viewPager);  //Vinculando o viewPager com o tabLayout.

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setHorizontalFadingEdgeEnabled(true);  //Aplica efeito de fadding suave no rolamento do tabLayout na parte da esquerda.

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);

        //NAVIGATIONDRAWER
        drawerClass.DrawerBodyBuilder(this, savedInstanceState, mToolbar, viewPager, c, headerDrawer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //Inserindo as opções na toolbar.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //Quando clicar nos itens do menu.
        int id = item.getItemId();
        if(id == R.id.action_list_type){
            new MaterialDialog.Builder(c)
                    .title(R.string.selecioneTipoLista)
                    .items(R.array.tipoLista)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            int tabSelecionada = viewPager.getCurrentItem();
                            for (int i = 0; i < 3 ; i++) {
                                BdLite.atualizaTipoLista(i, which);  //Define todas as listas como o parâmetro passado.
                            }
                            viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), GetTabsBd(c)));  //Reinicia os fragments.
                            viewPager.setCurrentItem(tabSelecionada);  //Após reiniciar os fragments, deixa no fragment que já estava selecionado.
                        }
                    })
                    .show();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //SERVIÇO QUE AVISA QUANDO FAZ ALGUM SCREENSHOT.
        new OnClearFromRecentService().ScreenshotListener(c);  //Funciona em todas as activitys.
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //Se o resultado == 2 quer dizer que acabou de fechar outra Activity e que na mesma, foi clicado em algum item do drawer, fechando-a e setando a tab certa.
        if(resultCode==2) {  //Só será dois, se for fechado pelas activitys Frag, passando o comando de voltar passando informações: startActivityForResult.
            int i = data.getIntExtra("tabSelecionada", 2);  //Retorna o valor do intent como 2, e a tab selecionada.
            if (i < 10) {  //Para não dar erro, pois se selecionar configurações no drawer por exemplo, ele vai dar erro pois não existe essa tab.
                viewPager.setCurrentItem(i - 1);
            }else{  //Se for maior do que o número de tabs existentes, ele retorna para a tab um, mas IMPLEMENTAR FUTURAMENTE.
                viewPager.setCurrentItem(0);
            }
        }
    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
//        outState.putInt("mProfileDrawerSelected", mProfileDrawerSelected);
//        outState.putParcelableArrayList("listCars", (ArrayList<Car>) listCars);
//        outState = navigationDrawerLeft.saveInstanceState(outState);
//        outState = headerNavigationLeft.saveInstanceState(outState);
//        super.onSaveInstanceState(outState);
//    }
}
