package com.codeandroid.engvi.HandleTraTu;

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

import com.codeandroid.engvi.R;
import com.codeandroid.engvi.Tu.Tu;

import java.util.List;

public class TraTuAdapter extends RecyclerView.Adapter<TraTuAdapter.ViewHolder> {
    List<Tu> list;
    IOnClickTu iOnClickTu;
    Context context;

    public TraTuAdapter(List<Tu> list, Context context) {
        this.list = list;
        this.context= context;
    }

    public List<Tu> getList() {
        return list;
    }

    public void setList(List<Tu> list) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.layout_item_tu, parent, false);
        TraTuAdapter.ViewHolder viewHolder= new TraTuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tu tu= list.get(position);
        boolean isYt= false;
        String tuStr= tu.getTuAnh();
        if (!isEV){
            tuStr= tu.getTuViet();
        }
        holder.tvItemTu.setText(tuStr);
        if (sqlHelper.daYeuThichTu(tu.getTuAnh())){
            holder.imgSao.setColorFilter(context.getResources().getColor(R.color.cl_yt));
            isYt= true;
        }
        holder.tvItemTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickTu.iOnClickTu(tu, position);
            }
        });
        final boolean[] finalIsYt = {isYt};
        holder.imgSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickTu.iOnClickYeuThich(tu, position);
                if (finalIsYt[0]) {
                    holder.imgSao.setColorFilter(context.getResources().getColor(R.color.cl_sao));
                    finalIsYt[0] = !finalIsYt[0];
                }else {
                    holder.imgSao.setColorFilter(context.getResources().getColor(R.color.cl_yt));
                    finalIsYt[0] = !finalIsYt[0];
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTu;
        ImageView imgSao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTu= itemView.findViewById(R.id.tvTuItem);
            imgSao= itemView.findViewById(R.id.imgSao);
        }
    }
}
