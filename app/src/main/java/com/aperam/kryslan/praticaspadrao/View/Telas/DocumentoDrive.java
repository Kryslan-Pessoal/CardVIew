package com.aperam.kryslan.praticaspadrao.View.Telas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aperam.kryslan.praticaspadrao.MainActivity;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.Domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.Controller.Tools.DrawerCreator;
import com.mikepenz.materialdrawer.AccountHeader;

public class DocumentoDrive extends MainActivity{
    private WebView webview;
    private ProgressBar progress;
    private SwipeRefreshLayout swipeLayout;

    Activity c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        TransitionInflater inflater = TransitionInflater.from(this);  //Recebe a animação.
        Transition transition = inflater.inflateTransition(R.transition.transitions);

        getWindow().setSharedElementExitTransition(transition);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_documento_drive);
        Toolbar toolbar = findViewById(R.id.my_toolbar_documento_drive);

        ListaPraticas informacoesDaPratica = getIntent().getExtras().getParcelable("praticascards");

        TextView textViewTitulo = findViewById(R.id.tvDocumentoDriveTitulo);
        textViewTitulo.setText(informacoesDaPratica.getNome());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));  //Defina a cor da appBar para roxo escuro, pois estava vindo branco não sei o porque.
        }

       /* if (savedInstanceState == null) {
            SitePL4Fragment frag = new SitePL4Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }*/

        final String linkPratica = informacoesDaPratica.getLinkDocumento();

        webview = findViewById(R.id.webview);
        progress = findViewById(R.id.progress);
        setWebViewClient(webview);
        //Abilita o zoom.
//        webview.setInitialScale(200);  PARA IMPLEMENTAR O ZOOM MAIS TARDE.
        // Carrega a página
        webview.loadUrl(linkPratica);

        // Swipe to Refresh
        swipeLayout = findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.reload();
            }
        });
        // Cores da animação
        swipeLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.md_red_500);

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);

        ViewPager viewPager = null;
        //NAVIGATIONDRAWER
        drawerClass.DrawerBodyBuilder(c, savedInstanceState, toolbar, viewPager, c, headerDrawer);
    }

    private void setWebViewClient(WebView webview) {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                // Liga o progress
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webview, String url) {
                // Desliga o progress
                progress.setVisibility(View.INVISIBLE);
                // Termina a animação do Swipe to Refresh
                swipeLayout.setRefreshing(false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.endsWith("sobre.htm")) {
//                    AboutDialog.showAbout(getFragmentManager());
                    // Retorna true para informar que interceptamos o evento
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
