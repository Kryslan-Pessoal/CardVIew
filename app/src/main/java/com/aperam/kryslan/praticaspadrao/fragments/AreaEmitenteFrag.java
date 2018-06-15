package com.aperam.kryslan.praticaspadrao.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.MainActivity;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.CardAdapter;
import com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD;
import com.aperam.kryslan.praticaspadrao.domain.IndiceRecycleView;
import com.aperam.kryslan.praticaspadrao.interfaces.PraticasActivity;
import com.aperam.kryslan.praticaspadrao.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

public class AreaEmitenteFrag extends Fragment implements RecyclerViewOnClickListenerHack {
    private RecyclerView mRecyclerView;
    private List<IndiceRecycleView> mList;
    private IndiceRecycleView indiceRecycleView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        View view = inflater.inflate(R.layout.fragment_praticas, container, false);  //pegando o fragment.

        mRecyclerView = view.findViewById(R.id.rv_list);  //Pegando o recyclerView.
        mRecyclerView.setHasFixedSize(true);  //Vai garantir que o recyclerView não mude de tamanho.

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
           @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                /*LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CardAdapter adapter = (CardAdapter) mRecyclerView.getAdapter();  //Implementar caso queira que a lista continue gerando se chegar no final dela (no caso repetindo os valores).

                //int l = llm.findLastCompletelyVisibleItemPosition();

                if(mList.size() == l + 1){  //MODIFICAR PRA MAIS DEPOIS.
                    List<IndiceRecycleView> listaAux = GetAreasRelacionadasBd(recyclerView.getContext());  //Define a quantidade que será criado a cada rolagem
                    for (int i = 0; i < listaAux.size(); i++) {
                        adapter.addListItem(listaAux.get(i), mList.size());  //pra add itens a lista vai em CardAdapter no método AddListItem.
                    }
                }*/
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

//        boolean formatoLista = getArguments().getBoolean("formatoLista");

        mList = AreaEmitenteBD.GetAreaEmitenteBd(getActivity());  //Se for maior do que a lista, começa a repetir os itens. Mas não da erro.
        CardAdapter adapter = new CardAdapter(getActivity(), mList);
        //adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(getActivity(), PraticasActivity.class);
        indiceRecycleView = mList.get(position);
        intent.putExtra("praticascards", indiceRecycleView);


        getActivity().startActivityForResult(intent, 1);
//        startActivity(intent);
        // TRANSITIONS
        /*if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){

            View ivCar = view.findViewById(R.id.iv_car);
            View tvModel = view.findViewById(R.id.tv_model);
            View tvBrand = view.findViewById(R.id.tv_brand);

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    Pair.create( ivCar, "element1" ),
                    Pair.create( tvModel, "element2" ),
                    Pair.create( tvBrand, "element3" ));

            getActivity().startActivity( intent, options.toBundle() );
        }
        else{
            getActivity().startActivity(intent);
        }*/


//        Toast.makeText(getActivity(), "onClickListener(): " +position, Toast.LENGTH_SHORT).show();

//        CardAdapter adapter = (CardAdapter) mRecyclerView.getAdapter();
//        adapter.removeListItem(position);  //Ao clicar no item, remove ele da lista.
    }

    @Override
    public void onLongPressClickListener(View view, int position) {  //Aqui define o que acontece ao clicar e segurar em cada card.
        Toast.makeText(getActivity(), "onLongPressClickListener: " +position, Toast.LENGTH_SHORT).show();

//        CardAdapter adapter = (CardAdapter) mRecyclerView.getAdapter();
//        adapter.removeListItem(position);  //Ao clicar no item, remove ele da lista.
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{  //Ao clicar nos itens lança um Listener, para fazer a animação.
        private Context mContext;  //Pega várias informações do app no tempo de execução para usar essas informações.
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvhack){
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvhack;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){  //É chamado em onInterceptTouchEvent.
               //--- MotionEvent pode identificar vários tipos de cliques diferentes. ---
               @Override
               public void onLongPress(MotionEvent e){
                    super.onLongPress(e);

                    View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).

                    if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null){  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(viewSelecionada, rv.getChildAdapterPosition(viewSelecionada));
                    }
               }

               @Override
               public boolean onSingleTapUp(MotionEvent e){
                   View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).

                   if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null){  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
                       mRecyclerViewOnClickListenerHack.onClickListener(viewSelecionada, rv.getChildAdapterPosition(viewSelecionada));  //Aqui chama "onClickListener"
                   }
                   return (true);
               }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);  //Identifica se foi um clique simples ou longPress.
            return false;  //se for true, pega o evento de click disparado do layout root (RelativeLayout no caso).
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }
}
