package com.codeandroid.engvi.HandleTuYeuThich;

import static com.codeandroid.engvi.MainActivity.isEV;
import static com.codeandroid.engvi.MainActivity.sqlHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.engvi.HandleTraTu.IOnClickTu;
import com.codeandroid.engvi.HandleTraTu.TraTuAdapter;
import com.codeandroid.engvi.R;
import com.codeandroid.engvi.Tu.Tu;

import java.util.Collections;
import java.util.List;

public class TuYeuThichAdapter extends RecyclerView.Adapter<TuYeuThichAdapter.ViewHolder> {
    List<Tu> list;
    IOnClickTu iOnClickTu;
    Context context;

    public TuYeuThichAdapter(List<Tu> list, Context context) {
        Collections.reverse(list);
        this.list = list;
        this.context= context;
    }

    public List<Tu> getList() {
        return list;
    }

    public void setList(List<Tu> list) {
        Collections.reverse(list);
        this.list = list;
        notifyDataSetChanged();
    }

    public IOnClickTu getiOnClickTu() {
        return iOnClickTu;
    }

    public void setiOnClickTu(IOnClickTu iOnClickTu) {
        this.iOnClickTu = iOnClickTu;
    }

    @NonNull
    @Override
    public TuYeuThichAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.layout_item_tu_yeuthich, parent, false);
        TuYeuThichAdapter.ViewHolder viewHolder= new TuYeuThichAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuYeuThichAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tu tu= list.get(position);
        String tuStr= tu.getTuAnh();
        if (!isEV){
            tuStr= tu.getTuViet();
        }
        holder.tvItemTu.setText(tuStr);
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

