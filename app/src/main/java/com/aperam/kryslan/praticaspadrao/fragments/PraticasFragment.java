package com.aperam.kryslan.praticaspadrao.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.PraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.Praticas;
import com.aperam.kryslan.praticaspadrao.interfaces.MainActivity;
import com.aperam.kryslan.praticaspadrao.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;
import java.util.Objects;

public class PraticasFragment extends Fragment implements RecyclerViewOnClickListenerHack {
    private RecyclerView mRecyclerView;
    private List<Praticas> mList;


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

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                PraticasAdapter adapter = (PraticasAdapter) mRecyclerView.getAdapter();
                int l = llm.findLastCompletelyVisibleItemPosition();

                if(mList.size() == l + 1){
                    List<Praticas> listaAux = ((MainActivity) Objects.requireNonNull(getActivity())).getSetPraticasList(2);

                    for (int i = 0; i < listaAux.size(); i++) {
                        adapter.addListItem(listaAux.get(i), mList.size());  //pra add itens a lista vai em PraticasAdapter no método AddListItem.
                    }
                }
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

        mList = ((MainActivity) Objects.requireNonNull(getActivity())).getSetPraticasList(3);  //Se for maior do que a lista, começa a repetir os itens. Mas não da erro.
        PraticasAdapter adapter = new PraticasAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Toast.makeText(getActivity(), "Position: " +position, Toast.LENGTH_SHORT).show();

        PraticasAdapter adapter = (PraticasAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);  //Ao clicar no item, remove ele da lista. 
    }
}
