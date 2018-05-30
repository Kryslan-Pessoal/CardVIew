package com.aperam.kryslan.praticaspadrao.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        public ImageView imagemIlustrativa;
        public CardView cardView;
        public TextView nomeNoCard;
        public TextView numeroNoCard;



        public MyViewHolder(View itemView){
            super(itemView);

            imagemIlustrativa = itemView.findViewById(R.id.imagem_ilustrativa);
            cardView = itemView.findViewById(R.id.card_view_adapter);
            nomeNoCard = itemView.findViewById(R.id.nome);
            numeroNoCard = itemView.findViewById(R.id.numero);

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


        //Redimenciona a imagem de acordo com o tamanho da tela.
        Context c = myViewHolder.imagemIlustrativa.getContext();  // Pega o contexto da activity para usar mais a frente.

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        if (windowmanager != null) {  //Para evitar erros, apenas redimenciona a imagem caso o windowmanager exista.
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);  //Pega o tamanho da tela do celular.
            int larguraDispositivo = displayMetrics.widthPixels;

            int larguraFrag;
            if(larguraDispositivo < 720){
                larguraFrag = larguraDispositivo;
            }else{
                larguraFrag = 720;
            }

            Double larguraImagemAux = (larguraFrag*0.926);
            int larguraImagem = larguraImagemAux.intValue();
            Double alturaImagemAux = (larguraImagem * 0.55);
            int alturaImagem = alturaImagemAux.intValue();
            ViewGroup.LayoutParams layoutParamsImagem = myViewHolder.imagemIlustrativa.getLayoutParams();

            layoutParamsImagem.width = larguraImagem;
            layoutParamsImagem.height = alturaImagem;
            myViewHolder.imagemIlustrativa.setLayoutParams(layoutParamsImagem);

            Double larguraCardAux = (larguraImagem*0.9745);
            int larguraCard = larguraCardAux.intValue();
            ViewGroup.LayoutParams layoutParamsCard = myViewHolder.cardView.getLayoutParams();
            layoutParamsCard.width = larguraCard;
            layoutParamsCard.height = 100;
            myViewHolder.cardView.setLayoutParams(layoutParamsCard);


        }else{
            Toast toast = Toast.makeText(c,"Tamanho da tela indefinido.",Toast.LENGTH_SHORT);
            toast.show();  //OLHAR DEPOIS CASO CAIA AQUI.
        }

        Picasso.get().load(mList.get(positionList)  //Pega a imagem da internet e coloca no ImageView.
                .getUrlImagem())
                .resize(1280, 720)
                .centerCrop()
                .into(myViewHolder.imagemIlustrativa);



        //myViewHolder.imagemIlustrativa.setImageBitmap(imgPraticaIlutrativa);

        myViewHolder.nomeNoCard.setText(mList.get(positionList).getNome());
        myViewHolder.numeroNoCard.setText(mList.get(positionList).getNumero());

        try {
            YoYo.with(Techniques.FadeIn)  //Defina a animação na hora de carregar cada Card.
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
