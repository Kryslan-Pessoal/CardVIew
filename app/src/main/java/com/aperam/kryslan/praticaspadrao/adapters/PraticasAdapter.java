package com.aperam.kryslan.praticaspadrao.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.domain.Praticas;
import com.aperam.kryslan.praticaspadrao.interfaces.RecyclerViewOnClickListenerHack;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PraticasAdapter extends RecyclerView.Adapter<PraticasAdapter.MyViewHolder>{
    private List<Praticas> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public PraticasAdapter(Context c,List<Praticas> lista){
        mList = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {   //só é chamado na hora de criar uma nova view (novo cardo).
        Log.i("LOG", "onCreateViewHolder()");

        View v = mLayoutInflater.inflate(R.layout.item_pratica, viewGroup, false);  //viewGroup é o recyclerView(extenção) | o false é pois estamos recebendo os parâmetros do recyclerView, e vamos adicionar mais coisas.
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Responsável por controlar os cardViews, deletando os que não aparecem na tela para preservar memória
        public ImageView praticaIlustrativa;
        public TextView nomeNoCard;
        public TextView numeroNoCard;



        public MyViewHolder(View itemView){
            super(itemView);

            praticaIlustrativa = (ImageView) itemView.findViewById(R.id.pratica_ilustrativa);
            nomeNoCard = (TextView) itemView.findViewById(R.id.nome);
            numeroNoCard = (TextView) itemView.findViewById(R.id.numero);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int positionList) {  //Vincula nossos dados com a view. (Seta o valor de cada 'mList' com uma view)
        Log.i("LOG", "onBindViewHolder()");

        //myViewHolder.praticaIlustrativa.setImageResource();
        String url = mList.get(positionList).getUrlImagem();
        Picasso.get().load(mList.get(positionList).getUrlImagem()).into(myViewHolder.praticaIlustrativa);
        //myViewHolder.praticaIlustrativa.setImageBitmap(imgPraticaIlutrativa);

        myViewHolder.nomeNoCard.setText(mList.get(positionList).getNome());
        myViewHolder.numeroNoCard.setText(mList.get(positionList).getNumero());

        try {
            YoYo.with(Techniques.Landing)
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {  //Retorna a quantidade da lista.
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;  //Está adicionando o parâmetro de clique de entrada ao hack para ativar o click.
    }

    public void addListItem(Praticas p, int position){
        mList.add(p);
        notifyItemInserted(position);
    }

}
