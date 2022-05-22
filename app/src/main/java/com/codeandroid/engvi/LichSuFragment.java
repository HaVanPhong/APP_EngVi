package com.codeandroid.engvi;

import static com.codeandroid.engvi.MainActivity.sqlHelper;
import static com.codeandroid.engvi.MainActivity.tuDaXemAdapter;
import static com.codeandroid.engvi.MainActivity.tuListDX;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codeandroid.engvi.HandleTuDaXem.IOnClickTuDX;
import com.codeandroid.engvi.HandleTuDaXem.TuDaXemAdapter;
import com.codeandroid.engvi.HandleTuYeuThich.TuYeuThichAdapter;
import com.codeandroid.engvi.Tu.Tu;
import com.codeandroid.engvi.databinding.FragmentLichSuBinding;
import com.codeandroid.engvi.databinding.FragmentYeuThichBinding;

import java.util.List;


public class LichSuFragment extends Fragment {

    FragmentLichSuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_lich_su, container, false);


        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revTuDaXem.setLayoutManager(layoutManager);
        binding.revTuDaXem.setAdapter(tuDaXemAdapter);
        if (tuListDX==null || tuListDX.size()==0){
            binding.tvKhongCoTuDaXem.setVisibility(View.VISIBLE);
            binding.revTuDaXem.setVisibility(View.GONE);
        }
        tuDaXemAdapter.setiOnClickTuDX(new IOnClickTuDX() {
            @Override
            public void iOnClickTuDX(Tu tu, int pos) {
                AlertDialog.Builder builder=  new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xóa khỏi lịch sử");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tuListDX.remove(pos);
                        tuDaXemAdapter.setList(tuListDX);
                        sqlHelper.deleteTuDX(tu.getTuAnh());
                        dialogInterface.cancel();
                        Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
        return binding.getRoot();
    }
}