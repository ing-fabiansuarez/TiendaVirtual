package com.fabiansuarez.tiendavirtual;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    private List<Category> listSet;

    public AdapterCategory(List<Category> listSet) {
        this.listSet = listSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardViewColor;
        private TextView mTextViewNameCategory;
        private ImageView mIvIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardViewColor = itemView.findViewById(R.id.cv_color);
            mTextViewNameCategory = itemView.findViewById(R.id.tv_item_category_name);
            mIvIcon = itemView.findViewById(R.id.tv_item_category_icon);
        }

        public void link(Category category) {
            mTextViewNameCategory.setText(category.getName());
            mCardViewColor.setCardBackgroundColor(Color.parseColor(category.getColor()));
            Picasso
                    .get()
                    .load(category.getIconImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(mIvIcon);
        }
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {
        holder.link(listSet.get(position));
    }

    @Override
    public int getItemCount() {
        return listSet.size();
    }




}

