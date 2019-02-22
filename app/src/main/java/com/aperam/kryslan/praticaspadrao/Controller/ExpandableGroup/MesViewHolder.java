package com.aperam.kryslan.praticaspadrao.Controller.ExpandableGroup;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.aperam.kryslan.praticaspadrao.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MesViewHolder extends GroupViewHolder {

    private TextView genreTitle;
    private ImageView arrow;

    public MesViewHolder(View itemView) {
        super(itemView);
        arrow = itemView.findViewById(R.id.list_item_genre_arrow);
        genreTitle = itemView.findViewById(R.id.tituloListaExpandable);
    }

    public void setMesTitle(ExpandableGroup group) {
        genreTitle.setText(group.getTitle());
    }

    //ADICIONANDO ANIMAÇÕES.
    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 450, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(450, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
