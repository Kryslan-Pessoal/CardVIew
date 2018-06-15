package com.aperam.kryslan.praticaspadrao.interfaces;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.domain.IndiceRecycleView;
import com.mikepenz.materialdrawer.Drawer;
import com.squareup.picasso.Picasso;

import static android.graphics.Color.WHITE;

public class PraticasActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private IndiceRecycleView indiceRecycleView;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Drawer drawer;
    private MaterialDialog mMaterialDialog;
    private TextView tvDescription;
    private ViewGroup mRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TRANSITIONS
        /*if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                *//*Explode trans1 = new Explode();
                trans1.setDuration(3000);
                Fade trans2 = new Fade();
                trans2.setDuration(3000);

                getWindow().setEnterTransition( trans1 );
                getWindow().setReturnTransition( trans2 );*//*

            TransitionInflater inflater = TransitionInflater.from( this );
            Transition transition = inflater.inflateTransition( R.transition.transitions );

            getWindow().setSharedElementEnterTransition(transition);

            Transition transition1 = getWindow().getSharedElementEnterTransition();
            transition1.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    TransitionManager.beginDelayedTransition(mRoot, new Slide());
                    tvDescription.setVisibility( View.VISIBLE );
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praticas);

        indiceRecycleView = getIntent().getExtras().getParcelable("praticascards");

        /*Fresco.initialize(this);
        setContentView(R.layout.activity_praticas);

        if(savedInstanceState != null){
            praticasCards = savedInstanceState.getParcelable("praticascards");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("praticascards") != null) {
                praticasCards = getIntent().getExtras().getParcelable("praticascards");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }*/

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(indiceRecycleView.getTextoPrincipal());
        mCollapsingToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(WHITE));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ColorStateList.valueOf(WHITE));

        mToolbar = findViewById(R.id.tb_main);
        mToolbar.setTitle(indiceRecycleView.getTextoPrincipal());
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        ImageView ivCar = findViewById(R.id.iv_car);
//        SimpleDraweeView ivCar = findViewById(R.id.iv_car);


        Picasso.get().load(indiceRecycleView.getFotoUrl())  //Pega a imagem da internet e coloca no ImageView.
                .resize(1280, 720)
                .centerCrop()
                .into(ivCar);

        //Uri uri = Uri.parse( "https://lh6.googleusercontent.com/-WRJcLJZJspo/VVtjFVRucBI/AAAAAAADT-w/NnPHX5__C_s/w426-h295/descendo_com_Estilo.gif" );
//        Uri uri = Uri.parse( DataUrl.getUrlCustom( praticasCards.getUrlPhoto(), w) );
        /*DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri( uri )
                .setAutoPlayAnimations(true)
                .setOldController( ivCar.getController() )
                .build();*/

//        ivCar.setController( dc );


        //ivCar.setImageResource(praticasCards.getPhoto());

        /*drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(false)
                .withCloseOnClick(true)
                .withActionBarDrawerToggleAnimated(false)
                .withActionBarDrawerToggle(new ActionBarDrawerToggle(this, new DrawerLayout(this), R.string.drawer_open, R.string.drawer_close){
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        super.onDrawerSlide(drawerView, slideOffset);
                        navigationDrawerLeft.closeDrawer();
                        finish();
                    }
                })
                .build();*/

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PraticasActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_car_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ){
            searchView = (SearchView) item.getActionView();
        }
        else{
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint( getResources().getString(R.string.search_hint) );
*/
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("praticasCards", praticasCards);
    }


    /*@Override
    public void onBackPressed() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            TransitionManager.beginDelayedTransition(mRoot, new Slide());
            tvDescription.setVisibility( View.INVISIBLE );
        }

        super.onBackPressed();
    }*/
}
