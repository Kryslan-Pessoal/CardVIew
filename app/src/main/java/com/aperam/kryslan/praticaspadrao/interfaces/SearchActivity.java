package com.aperam.kryslan.praticaspadrao.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.BancoDeDados.BdSearchActivity;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack{
    private MaterialSearchView searchView;
    private RecyclerView recyclerView;
    private List<ListaPraticas> mList, filterList = new ArrayList<>();
    TelaInicialCards pesquisaBdSalva = new TelaInicialCards();


    private Context c = this;
    private Activity activity = this;

    String queryDePesquisaSubmit = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //PARÂMETROS DA ACTIVITY.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //TOOLBAR
        Toolbar mToolbar = findViewById(R.id.tb_main_search);
        setSupportActionBar(mToolbar);  //Cria a Toolbar.

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);

        //NAVIGATIONDRAWER
        drawerClass.DrawerBodyBuilder(this, savedInstanceState, mToolbar, null, this, headerDrawer);
        mToolbar.setTitle(getResources().getString(R.string.pesquisaAvancadaSemPontos));

        //SEARCHVIEW
        searchView = findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);  //Para o título aparecer e não ser tampado pela caixa do searchView, ele fica oculto por padrão.
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);  //Mas ao abrir ele, ele volta a aparecer.
            }
            @Override
            public void onSearchViewClosed() {
                searchView.setVisibility(View.GONE);  //E ao fechar ele some novamente.
            }
        });
//        String[] a = getHistoricoPesquisa();
//        searchView.setSuggestions(a);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query)){
                    queryDePesquisaSubmit = query;
//                    pesquisaBdSalva.setTextoPrincipal(query);
//                    BdLite.inserirHistoricoPesquisa(pesquisaBdSalva);
                }
                return false;  //Define se o teclado irá fechar ou não (false fecha).
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(!queryDePesquisaSubmit.isEmpty()){
                    newText = queryDePesquisaSubmit;
                }
                if (!TextUtils.isEmpty(newText)) {
                    filterList.clear();
                    for(int i = 0; i < mList.size(); i++)
                    {
                        if(mList.get(i).getNome().toLowerCase().contains(newText) ||
                                mList.get(i).getNumero().toLowerCase().contains(newText) ||
                                //mList.get(i).getAutor().toLowerCase().contains(newText) ||
                                mList.get(i).getDataVersao().toLowerCase().contains(newText)){
                            filterList.add(mList.get(i));
                        }
                    }

                }else{
                    filterList.addAll(mList);
                }
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                recyclerView.setAdapter(adapter);
                queryDePesquisaSubmit = "";
                return true;
            }
        });
        /*searchView.setOnClickListener(new MaterialSearchView.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList.clear();
                filterList.addAll(mList);
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                recyclerView.setAdapter(adapter);
            }
        });  //TESTAR E FAZER FUNCIONAR.*/
        searchView.setHint("Procura");  //NÃO FUNCIONA*****

        //RECYCLER VIEW.
        recyclerView = findViewById(R.id.rv_list_search);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager lm = new LinearLayoutManager(c);
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        recyclerView.setLayoutManager(lm);

        mList = BdLite.SelectTodasAsPraticas();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, mList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new PraticasActivity.RecyclerViewTouchListener(c, recyclerView, this));

        //SCROOL BAR.
        ((DragScrollBar) findViewById(R.id.dragScrollBarSearch))
                .setHandleColour(getResources().getColor(R.color.colorPrimary));  //CONFIGURAR O SAVEDINSTANCESTATE.

    }

    private String[] getHistoricoPesquisa() {  //Vai pegar do banco a tabela com o histórico de pesquisa.
        List<TelaInicialCards> listaHistorico = BdLite.SelectHistoricoPesquisa();
        String[] textoPrincipal = new String[listaHistorico.size()];
        for (int i = 0; i < listaHistorico.size(); i++) {
            textoPrincipal[i] = listaHistorico.get(i).getTextoPrincipal();  //Vai pegar apenas o texto, e não a List completa que o banco retorna.
        }

        return textoPrincipal;
    }

    @Override
    public void onClickListener(View view, int position) {
        //CHAMANDO PRÓXIMA ACTIVITY (DocumentoDrive).
        Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
        if(!filterList.isEmpty()){  //Se filterList não estiver vazio, quer dizer que está exibindo apenas os itens pesquisados, então pega a posição do filterList, e não do mList.
            intent.putExtra("praticascards", filterList.get(position));
        }else {
            intent.putExtra("praticascards", mList.get(position));
        }

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View textoAutor = view.findViewById(R.id.tituloListaPraticas);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(textoAutor, "element2"));  //Não vai achar o element2
        //propositalmente para causar uma animação suave.

        activity.startActivityForResult(intent, 1, options.toBundle());

        //SALVANDO NO BANCO DE DADOS ESSE ITEM PARA EXIBI-LO NO HISTÓRICO.
        BdLite bd = new BdLite(activity);
        bd.InsertHistorico(mList.get(position).getId());
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        List<ListaPraticas> mListAux;
        if(!filterList.isEmpty()){  //Se filterList não estiver vazio, quer dizer que está exibindo apenas os itens pesquisados, então pega a posição do filterList, e não do mList.
            mListAux = filterList;
        }else {
            mListAux = mList;
        }
        String[] corpoDialog = new String[4];
        corpoDialog[0] = "TÍTULO: " + mListAux.get(position).getNome();
        corpoDialog[1] = "Nº: " + mListAux.get(position).getNumero();
        corpoDialog[2] = "AUTOR: " + mListAux.get(position).getAutor();
        corpoDialog[3] = "DATA: " + mListAux.get(position).getDataVersao();
        new MaterialDialog.Builder(this)  //Cria um Dialog com informações da PPA clicada.
                .title(R.string.informacoesDaPpa)
                .items(corpoDialog)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //SEARCHVIEW
        getMenuInflater().inflate(R.menu.menu_praticas_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(searchItem);

        return true;
    }
}
