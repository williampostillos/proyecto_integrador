package com.example.proyecto_integrador.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto_integrador.Domains.InformacionDomain;
import com.example.proyecto_integrador.R;
import com.example.proyecto_integrador.data.Detalle;

import java.util.ArrayList;

public class InformacionAdapter extends RecyclerView.Adapter<InformacionAdapter.viewHolder> {
    private ArrayList<Detalle> items;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Detalle detalle);
    }

    public InformacionAdapter(ArrayList<Detalle> items, OnItemClickListener onItemClickListener) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public InformacionAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_informacion, parent, false);
        context = parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InformacionAdapter.viewHolder holder, int position) {
        Detalle detalle = items.get(position);

        holder.nameTxt.setText(detalle.getName());
        holder.otherTxt.setText(detalle.getOther());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(String.valueOf(detalle.getPicPath()), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.picPath);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(detalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, otherTxt;
        ImageView picPath;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameTxt);
            otherTxt = itemView.findViewById(R.id.otherTxt);
            picPath = itemView.findViewById(R.id.picPath);
        }
    }
}
