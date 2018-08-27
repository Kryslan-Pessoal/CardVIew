package com.aperam.kryslan.praticaspadrao;

import android.app.Activity;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.ViewPagerAdapter;
import com.aperam.kryslan.praticaspadrao.interfaces.SearchActivity;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.aperam.kryslan.praticaspadrao.tools.OnClearFromRecentService;
import com.mikepenz.materialdrawer.AccountHeader;

import java.io.IOException;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BdMainActivity.GetTabsBd;

public class MainActivity extends AppCompatActivity {
    private Context c = this;
    private Activity a = this;

    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ANIMAÇÃO.
        TransitionInflater inflater = TransitionInflater.from(this);  //Recebe a animação quando está em outra activity e volta para esta.
        Transition transition = inflater.inflateTransition(R.transition.transitions);  //Seta o tipo de transição

        getWindow().setSharedElementExitTransition(transition);  //Comando para executar a transição.

        //MAIN
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BdLite(c);  //Se o BD não existe, cria ele.

        /*try {
            int insertCount = BdLite.executandoComandosSql(c, R.raw.ppas_bd);
            Toast.makeText(c, "Rows loaded from file= " + insertCount, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(c, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }*/

        /*if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);

        }else{
            listPraticas = getSetPraticasList(50);
        }*/

        //LISTENER DE CloseApp.
        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));  //Inicia o serviço que observa se o app foi fechado, se sim, mata o listener de screenshot.

        //TOOLBAR
        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);  //Informa para o Android que essa View será a toolbar.

        //TABs
        viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), GetTabsBd(c)));  //Criando as Tabs com seus nomes baseado no BdLite.
        tabLayout.setupWithViewPager(viewPager);  //Vinculando o viewPager com o tabLayout.

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);  //Permite que as Tabs sejam roladas verticalmente.
        tabLayout.setHorizontalFadingEdgeEnabled(true);  //Aplica efeito de fadding suave no rolamento do tabLayout na parte da esquerda (Apenas efeito visual).

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);  //Cria as accounts na parte Header do Drawer.

        //NAVIGATIONDRAWER
        drawerClass.DrawerBodyBuilder(this, savedInstanceState, mToolbar, viewPager, c, headerDrawer);  //Cria o Drawer lateral.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //Inserindo as opções na toolbar.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //Quando clicar nos itens do menu.
        int id = item.getItemId();
        if(id == R.id.action_list_type){  //Define o tipo de lista para Todos os fragments.
            new MaterialDialog.Builder(c)  //Cria o Material Dialog com as opções de tipo de lista.
                    .title(R.string.selecioneTipoLista)
                    .items(R.array.tipoLista)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int itemSelecionado, CharSequence text) {  //Listener do Dialog disparado ao selecionar o tipo de lista.
                            int tabSelecionada = viewPager.getCurrentItem();
                            for (int fragment = 0; fragment < 3 ; fragment++) {
                                BdLite.atualizaTipoLista(fragment, itemSelecionado);  //Define todas as listas com o parâmetro passado.
                            }
                            viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), GetTabsBd(c)));  //Reinicia os fragments para mostrar a lista nova.
                            viewPager.setCurrentItem(tabSelecionada);  //Após reiniciar os fragments, deixa no fragment que já estava selecionado.
                        }
                    })
                    .show();  //Mostra o Dialog.
        }else if(id == R.id.pesquisaAvancada){
            //CHAMA ACTIVITY DE PESQUISA AVANÇADA.
            Intent intent = new Intent(c, SearchActivity.class);
            a.startActivityForResult(intent, 1);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data){  //Função feita para ao selecionar uma categoria em outra activity sem ser essa (main) fechar a activity em questão e voltar para a main deixando a tab referente a categoria selecionada selecionada.
        super.onActivityResult(requestCode, resultCode, data);
        //Se o resultado == 2 quer dizer que acabou de fechar outra Activity e que na mesma, foi clicado em algum item do drawer, fechando-a e setando a tab certa.
        if(resultCode==2) {  //Só será dois, se for fechado por outra activity quando na mesma foi clicada em algum item do Drawer, passando assim o comando: startActivityForResult.
            int i = data.getIntExtra("tabSelecionada", 2);  //Pela key "tabSelecionada" pega o valor da tab que deve ser selecionada no mainActivity.
            if (i < 8) {  //Para não dar erro, pois se selecionar configurações no drawer por exemplo, ele vai dar erro pois não existe essa tab (com o nome "configurações").
                viewPager.setCurrentItem(i - 1);  //Exibe o ViewPager selecionado.
            }else{  //Se for maior do que o número de tabs existentes, ele retorna para a tab um, mas IMPLEMENTAR FUTURAMENTE.
                viewPager.setCurrentItem(0);  //Exibe o ViewPager selecionado.
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
