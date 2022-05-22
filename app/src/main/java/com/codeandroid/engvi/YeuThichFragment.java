package com.codeandroid.engvi;

import static com.codeandroid.engvi.MainActivity.sqlHelper;
import static com.codeandroid.engvi.MainActivity.tuListYT;
import static com.codeandroid.engvi.MainActivity.tuYeuThichAdapter;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeandroid.engvi.HandleTuYeuThich.TuYeuThichAdapter;
import com.codeandroid.engvi.Tu.Tu;
import com.codeandroid.engvi.databinding.FragmentYeuThichBinding;

import java.util.List;

public class YeuThichFragment extends Fragment {


    FragmentYeuThichBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_yeu_thich, container, false);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revTuDaYeuThich.setLayoutManager(layoutManager);
        binding.revTuDaYeuThich.setAdapter(tuYeuThichAdapter);
        if (tuListYT==null||tuListYT.size()==0){
            binding.tvKhongCoTuYeuThich.setVisibility(View.VISIBLE);
            binding.revTuDaYeuThich.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }
}