package com.aperam.kryslan.praticaspadrao.View.Adapters;

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

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.View.Telas.RecyclerViewOnClickListenerHack;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

public class CardTelaInicialAdapter extends RecyclerView.Adapter<CardTelaInicialAdapter.MyViewHolder> implements INameableAdapter{  //INameableAdapter é responsável pelas letras que aparecerão na barra de rolagem (DragScrollBar) indicando o índice.
    private Context c;

    private List<TelaInicialCards> mList;
    private LayoutInflater mLayoutInflater;  //Vai inflar (desenhar) os Cards.
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;  //Listener de clique nos cards.

    public CardTelaInicialAdapter(Context c, List<TelaInicialCards> lista){
        this.c = c;
        mList = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_tela_inicial_card, viewGroup, false);  //Vincula o XML que contém o desenho e formato do card com uma View.
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Responsável por controlar os cardViews, deletando os que não aparecem na tela para preservar memória
        //Views dos itens dentro do card.
        private ImageView imagemIlustrativa;
        private CardView cardView;
        private TextView nomeNoCard;

        private MyViewHolder(View itemView){
            super(itemView);

            imagemIlustrativa = itemView.findViewById(R.id.imagem_ilustrativa);
            cardView = itemView.findViewById(R.id.card_view_adapter);
            nomeNoCard = itemView.findViewById(R.id.tituloCard);

            itemView.setOnClickListener(this);  //Ativa o Listener de Clique cada vez que cria um Card (card por card).
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
                larguraFrag = 1080;  //Caso o dispositivo seja muito grande (maior que 1080 pixels) os tamanhos dos cards e imagens não irão passar de 1080, para não esticar demais a imagem.
            }
        }else{
            larguraFrag = 1080;  //Se o tamanho não for identificado, o app pegará como medida padrão 1080p.
        }

        //DIMENSÕES DAS IMAGENS.
        Double larguraImagemAux = (larguraFrag*0.926);  //A imagem deve ser +-7% menor do que o fragment que o envolve.
        int larguraImagem = larguraImagemAux.intValue();
        Double alturaImagemAux = (larguraImagem * 0.55);  //A largura da imagem é equivalente a 55% de sua altura (para ficar melhor ajustada).
        int alturaImagem = alturaImagemAux.intValue();
        ViewGroup.LayoutParams layoutParamsImagem = myViewHolder.imagemIlustrativa.getLayoutParams();

        layoutParamsImagem.width = larguraImagem;
        layoutParamsImagem.height = alturaImagem;
        myViewHolder.imagemIlustrativa.setLayoutParams(layoutParamsImagem);  //Seta os parâmetros de largura e altura no viewHolder.

        //DIMENSÕES DOS CARDS.
        Double larguraCardAux = (larguraImagem*0.9745);  //O card deve ser +-3% menor do que a imagem.
        int larguraCard = larguraCardAux.intValue();
        ViewGroup.LayoutParams layoutParamsCard = myViewHolder.cardView.getLayoutParams();
        layoutParamsCard.width = larguraCard;

        Float valor = displayMetrics.xdpi;

        Double valorFinal = valor * 0.36;

        layoutParamsCard.height = valorFinal.intValue();
        myViewHolder.cardView.setLayoutParams(layoutParamsCard);

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

        myViewHolder.nomeNoCard.setText(mList.get(positionList).getTextoPrincipal());

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
        mRecyclerViewOnClickListenerHack = r;
    }

    @Override
    public Character getCharacterForElement(int element) {  //como o "INameableAdapter" (início do código), é responsável pelas letras que aparecerão na barra de rolagem (DragScrollBar) indicando o índice.
        Character c = mList.get(element).getTextoPrincipal().charAt(0);
        if(Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }
}
