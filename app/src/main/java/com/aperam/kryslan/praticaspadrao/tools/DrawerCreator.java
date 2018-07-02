package com.aperam.kryslan.praticaspadrao.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BD.GetTabsBd;

public class DrawerCreator {
    private Context c;

    public AccountHeader DraweHeaderBuilder(Activity a, Bundle savedInstanceState) {
        return new AccountHeaderBuilder()
                .withActivity(a)  //Contexto da activity onde o drawer será criado.
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
    }

    public void DrawerBodyBuilder(final Activity a, Bundle savedInstanceState, Toolbar mToolbar, final ViewPager viewPager, final Context c, AccountHeader headerDrawer){
        this.c = c;
        Drawer drawer;
        //MainActivity
        if(viewPager != null) {  //Se viewPager for diferente de null, quer dizer que o Drawer será criado na MainActivity.
            drawer = new DrawerBuilder()
                .withActivity(a)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, final int i, IDrawerItem drawerItem) {
                        if (i < 10) {  //Para não dar erro, pois se selecionar configurações no drawer por exemplo, ele vai dar erro pois não existe essa tab.
                            viewPager.setCurrentItem(i - 1);  //muda a posição da Tab e página selecionada sempre que mudar a posição no DrawerCreator.
                        }
                        return false;  //Faz o drawer fechar ou não. (false fecha)
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(c, "Clique longo" + position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withAccountHeader(headerDrawer)
                .build();
            //PraticasActivity
        }else {
            drawer = new DrawerBuilder()
            .withActivity(a)
            .withToolbar(mToolbar)
            .withDisplayBelowStatusBar(true)
            .withActionBarDrawerToggleAnimated(true)
            .withDrawerGravity(Gravity.START)
            .withSavedInstance(savedInstanceState)
            .withSelectedItem(1)
            .withActionBarDrawerToggle(false)  //Esta condição só possúi aqui, ela faz com que na AppBar, no canto esquerdo fique uma seta de voltar, e não as 3 linhas de abrir o Drawer.
            //Esta também, faz o botão voltar no AppBar realmente voltar.
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, final int i, IDrawerItem drawerItem) {
                    Intent intent = new Intent();
                    intent.putExtra("tabSelecionada",i);  //Passa a posição do item no drawer para a activity anterior (Main) para essa setar a tab correta.
                    a.setResult(2,intent);  //Manda resultCode 2 para rodar a função no if apenas se ela voltar por este caminho, e não por onCreate normal etc.
                    a.finish();

                    return false;  //Faz o drawer fechar ou não. (false fecha)
                }
            })
            .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                @Override
                public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                    Toast.makeText(c, "Clique longo" + position, Toast.LENGTH_SHORT).show();
                    return false;
                }
            })
            .withAccountHeader(headerDrawer)
            .build();
        }

        List<PrimaryDrawerItem> listCategorias = getSetCategoryList();  //Cria a lista de categorias que fica no DrawerCreator.
        if(listCategorias != null && listCategorias.size() > 0){
            for(int i = 0; i < listCategorias.size(); i++ ){
                drawer.addItem(listCategorias.get(i));  //Adiciona cada item no DrawerCreator.
            }
        }
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.configuracoes).withIcon(R.drawable.settings));
        //drawer.addItem(new SwitchDrawerItem().withName("Lista resumida").withChecked(false).withOnCheckedChangeListener(mOnCheckedChangeListener));

    }



    // CATEGORIAS
    private List<PrimaryDrawerItem> getSetCategoryList(){  //Cria a lista de categorias que ficará no drawer.
        String[] nomes = GetTabsBd(c);
        int[] icons = new int[]{
                R.drawable.area_eminente,
                R.drawable.areas_relacionadas,
                R.drawable.autor,
                R.drawable.data_de_vigencia,
                R.drawable.nivel,
                R.drawable.processo,
                R.drawable.restrito,
                R.drawable.estrela_cheia,
                R.drawable.historico};
        int[] iconsSelected = new int[]{
                R.drawable.area_eminente_selected,
                R.drawable.areas_relacionadas_selected,
                R.drawable.autor_selected,
                R.drawable.data_de_vigencia_selected,
                R.drawable.nivel_selected,
                R.drawable.processo_selected,
                R.drawable.restrito_selected,
                R.drawable.estrela_cheia,
                R.drawable.historico_selected};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < nomes.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.withName( nomes[i] );
            aux.withIcon(c.getResources().getDrawable(icons[i]));
            aux.withTextColor(c.getResources().getColor(R.color.PrimaryText));
            aux.withSelectedIcon(c.getResources().getDrawable(iconsSelected[i]));
            aux.withSelectedTextColor(c.getResources().getColor(R.color.colorPrimary));

            list.add( aux );
        }
        return(list);
    }
}
