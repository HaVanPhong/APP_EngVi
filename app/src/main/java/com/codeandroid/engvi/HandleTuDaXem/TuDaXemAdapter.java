package com.codeandroid.engvi.HandleTuDaXem;

import static com.codeandroid.engvi.MainActivity.isEV;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.engvi.HandleTraTu.IOnClickTu;
import com.codeandroid.engvi.R;
import com.codeandroid.engvi.Tu.Tu;

import java.util.Collections;
import java.util.List;

public class TuDaXemAdapter extends RecyclerView.Adapter<TuDaXemAdapter.ViewHolder> {
    List<Tu> list;
    Context context;

    IOnClickTuDX iOnClickTuDX;

    public IOnClickTuDX getiOnClickTuDX() {
        return iOnClickTuDX;
    }

    public void setiOnClickTuDX(IOnClickTuDX iOnClickTuDX) {
        this.iOnClickTuDX = iOnClickTuDX;
    }

    public TuDaXemAdapter(List<Tu> l, Context context) {
        Collections.reverse(l);
        this.list = l;
        this.context= context;
    }

    public List<Tu> getList() {
        return list;
    }

    public void setList(List<Tu> l) {
        Collections.reverse(l);
        this.list = l;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TuDaXemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.layout_item_tu_yeuthich, parent, false);
        TuDaXemAdapter.ViewHolder viewHolder= new TuDaXemAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuDaXemAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tu tu= list.get(position);
        String tuStr= tu.getTuAnh();
        if (!isEV){
            tuStr= tu.getTuViet();
        }
        holder.tvItemTu.setText(tuStr);
        holder.tvItemTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickTuDX.iOnClickTuDX(tu, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTu= itemView.findViewById(R.id.tvTuItem);
        }
    }
}

