package com.codeandroid.engvi;

import static com.codeandroid.engvi.MainActivity.edtSearch;
import static com.codeandroid.engvi.MainActivity.isEV;
import static com.codeandroid.engvi.MainActivity.sqlHelper;
import static com.codeandroid.engvi.MainActivity.traTuAdapter;
import static com.codeandroid.engvi.MainActivity.tuDaXemAdapter;
import static com.codeandroid.engvi.MainActivity.tuList;
import static com.codeandroid.engvi.MainActivity.tuListDX;
import static com.codeandroid.engvi.MainActivity.tuListYT;
import static com.codeandroid.engvi.MainActivity.tuYeuThichAdapter;
import static com.codeandroid.engvi.MainActivity.tvEV;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.engvi.HandleTraTu.IOnClickTu;
import com.codeandroid.engvi.HandleTraTu.TraTuAdapter;
import com.codeandroid.engvi.SQLite.SQLHelper;
import com.codeandroid.engvi.Tu.Tu;
import com.codeandroid.engvi.databinding.FragmentTraTuBinding;

import java.util.ArrayList;
import java.util.List;

public class TraTuFragment extends Fragment {
    FragmentTraTuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_tra_tu, container, false);
        traTuAdapter= new TraTuAdapter(tuList, getContext());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revTraTu.setAdapter(traTuAdapter);
        binding.revTraTu.setLayoutManager(layoutManager);
        traTuAdapter.setiOnClickTu(new IOnClickTu() {
            @Override
            public void iOnClickTu(Tu tu, int pos) {
                binding.revTraTu.setVisibility(View.GONE);
                binding.lnChiTietTu.setVisibility(View.VISIBLE);
                sqlHelper.xemTu(tu);
                if (isEV){
                    binding.tvTuTra.setText(tu.getTuAnh());
                    binding.tvPhienAm.setText(tu.getPhienAmAnh());
                    binding.tvLoaiTu.setText(tu.getLoai());
                    binding.tvNghia.setText(tu.getCacTuDongNghiaViet());
                    binding.tvVD.setText(tu.getVdAnh());
                    binding.tvNghiaVD.setText(tu.getVdViet());
                }else {
                    binding.tvTuTra.setText(tu.getTuViet());
                    binding.tvLoaiTu.setText(tu.getLoai());
                    binding.tvNghia.setText(tu.getCacTuDongNghiaAnh());
                    binding.tvVD.setText(tu.getVdViet());
                    binding.tvNghiaVD.setText(tu.getVdAnh());
                }
            }

            @Override
            public void iOnClickYeuThich(Tu tu, int pos) {
                sqlHelper.yeuThichVaBoYeuThichTu(tu);
            }
        });
        binding.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.lnChiTietTu.setVisibility(View.GONE);
                binding.revTraTu.setVisibility(View.VISIBLE);
            }
        });




        return binding.getRoot();
    }
}