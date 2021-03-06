package com.aperam.kryslan.praticaspadrao.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.View.Telas.RecyclerViewOnClickListenerHack;
import com.aperam.kryslan.praticaspadrao.Controller.Tools.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WhatsAppTelaInicialAdapter extends RecyclerView.Adapter<WhatsAppTelaInicialAdapter.MyViewHolder>{  //Esse nome é devido a esse tipo de lista se parecer muito visualm
        private List<TelaInicialCards> mList;
        private LayoutInflater mLayoutInflater;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        private Context c;

        public WhatsAppTelaInicialAdapter(Context c, List<TelaInicialCards> lista){
            this.c = c;
            mList = lista;
            mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {   //só é chamado na hora de criar uma nova view.
            View v = mLayoutInflater.inflate(R.layout.adapter_lista_whatsapp_main_activity, viewGroup, false);
            return new MyViewHolder(v);
        }

    /*public void removeListItem(int position){  //APLICAR AOS FAVORITOS
        mList.remove(position);
        notifyItemRemoved(position);
    }*/

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Responsável por controlar os cardViews, deletando os que não aparecem na tela para preservar memória
            private RoundedImageView imagemIlustrativa;
        private TextView nomeNoCard;

        private MyViewHolder(View itemView){
            super(itemView);

            imagemIlustrativa = itemView.findViewById(R.id.ivTelaInicialResumida);
            nomeNoCard = itemView.findViewById(R.id.tituloListaResumidaTelaInicial);

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
        //Redimenciona a imagem de acordo com o tamanho da tela.
        //Cria o tamanho das imagens e dos Cards baseado na largura da tela do dispositivo em questão.
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        int larguraFrag;
        if (windowmanager != null) {  //Para evitar erros, redimenciona a imagem apenas se o windowmanager exista.
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);  //Pega o tamanho da tela do celular.
            int larguraDispositivo = displayMetrics.widthPixels;

            if(larguraDispositivo < 1080){
                larguraFrag = larguraDispositivo;
            }else{
                larguraFrag = 1080;  //Caso o dispositivo seja muito grande (maior que 720pixels) os tamanhos dos cards e imagens não irão passar de 720, para não esticar demais a imagem.
            }
        }else{
            Toast toast = Toast.makeText(c,"Tamanho da tela indefinido. \n(Talvez as imagens não fiquem bem ajustadas).",Toast.LENGTH_LONG);
            toast.show();
            larguraFrag = 1080;
        }

        //Dimensões das imgens.
        Double larguraImagemAux = (larguraFrag*0.926);  //A imagem deve ser +-7% menor do que o fragment.
        int larguraImagem = larguraImagemAux.intValue();
        Double alturaImagemAux = (larguraImagem * 0.55);  //A largura da imagem é equivalente a 55% de sua altura (para ficar melhor ajustada).
        int alturaImagem = alturaImagemAux.intValue();
        ViewGroup.LayoutParams layoutParamsImagem = myViewHolder.imagemIlustrativa.getLayoutParams();

        layoutParamsImagem.width = larguraImagem;
        layoutParamsImagem.height = alturaImagem;
        myViewHolder.imagemIlustrativa.setLayoutParams(layoutParamsImagem);

        //Dimensões dos cards.
        Double larguraCardAux = (larguraImagem*0.9745);  //O card deve ser +-3% menor do que a imagem.
        int larguraCard = larguraCardAux.intValue();
        ViewGroup.LayoutParams layoutParamsCard = myViewHolder.cardView.getLayoutParams();
        layoutParamsCard.width = larguraCard;

        DisplayMetrics realmetrics = new DisplayMetrics();
        Float valor = displayMetrics.xdpi;

        Double valorFinalAux = valor * 0.36;

        int valorFinal = valorFinalAux.intValue();
        if (windowmanager != null) {
            windowmanager.getDefaultDisplay().getRealMetrics(realmetrics);
        }
        layoutParamsCard.height = valorFinal;
        myViewHolder.cardView.setLayoutParams(layoutParamsCard);*/

        if(!mList.get(positionList).getFotoUrl().equals(""))
            Picasso.get().load(mList.get(positionList)  //Pega a imagem da internet e coloca no ImageView.
                .getFotoUrl())
                .resize(1280, 720)
                .centerCrop()
                .into(myViewHolder.imagemIlustrativa);
        else  //Se esse item não tiver image, uma imagem padrão será usada
            Picasso.get().load("https://drive.google.com/uc?id=10W-D9UAY5v9mp0fbysn9cVpAkE51NFzD")
                    .resize(1280, 720)
                    .centerCrop()
                    .into(myViewHolder.imagemIlustrativa);

        myViewHolder.nomeNoCard.setText(mList.get(positionList).getTextoPrincipal());  //Está fora pois é chamado em todos os casos.

       /* try {
            YoYo.with(Techniques.FadeInUp)  //Defina a animação na hora de carregar cada Card.
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        } catch (Exception ignored) {

        }*/
    }

    @Override
    public int getItemCount() {  //Retorna a quantidade da lista.
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;  //Está adicionando o parâmetro de clique de entrada ao hack para ativar o click.
    }
}
