package com.codeandroid.engvi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerFragment  extends FragmentStateAdapter {

    public PagerFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TraTuFragment();
            case 1:
                return new LichSuFragment();
            case 2:
                return new YeuThichFragment();
            default:
                return new TraTuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
