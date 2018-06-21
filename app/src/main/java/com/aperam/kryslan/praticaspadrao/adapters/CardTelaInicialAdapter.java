package com.aperam.kryslan.praticaspadrao.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.interfaces.RecyclerViewOnClickListenerHack;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

public class CardTelaInicialAdapter extends RecyclerView.Adapter<CardTelaInicialAdapter.MyViewHolder> implements INameableAdapter{
    private List<TelaInicialCards> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    private Context c;

    public CardTelaInicialAdapter(Context c, List<TelaInicialCards> lista){
        this.c = c;
        mList = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {   //só é chamado na hora de criar uma nova view.
        View v = mLayoutInflater.inflate(R.layout.item_tela_inicial_card, viewGroup, false);
        return new MyViewHolder(v);
    }

    /*public void removeListItem(int position){  //APLICAR AOS FAVORITOS
        mList.remove(position);
        notifyItemRemoved(position);
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Responsável por controlar os cardViews, deletando os que não aparecem na tela para preservar memória
        private ImageView imagemIlustrativa;
        private CardView cardView;
        private TextView nomeNoCard;

        private MyViewHolder(View itemView){
            super(itemView);

            imagemIlustrativa = itemView.findViewById(R.id.imagem_ilustrativa);
            cardView = itemView.findViewById(R.id.card_view_adapter);
            nomeNoCard = itemView.findViewById(R.id.tituloCard);

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
        DisplayMetrics displayMetrics = new DisplayMetrics();
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
        myViewHolder.cardView.setLayoutParams(layoutParamsCard);

        Picasso.get().load(mList.get(positionList)  //Pega a imagem da internet e coloca no ImageView.
                .getFotoUrl())
                .resize(1280, 720)
                .centerCrop()
                .into(myViewHolder.imagemIlustrativa);

        myViewHolder.nomeNoCard.setText(mList.get(positionList).getTextoPrincipal());  //Está fora pois é chamado em todos os casos.

        try {
            YoYo.with(Techniques.FadeInUp)  //Defina a animação na hora de carregar cada Card.
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        } catch (Exception ignored) {

        }
    }

    @Override
    public int getItemCount() {  //Retorna a quantidade da lista.
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;  //Está adicionando o parâmetro de clique de entrada ao hack para ativar o click.
    }

    @Override
    public Character getCharacterForElement(int element) {
        Character c = mList.get(element).getTextoPrincipal().charAt(0);
        if(Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }
}
