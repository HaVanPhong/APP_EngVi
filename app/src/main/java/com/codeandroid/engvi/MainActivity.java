package com.codeandroid.engvi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeandroid.engvi.HandleTraTu.TraTuAdapter;
import com.codeandroid.engvi.HandleTuDaXem.TuDaXemAdapter;
import com.codeandroid.engvi.HandleTuYeuThich.TuYeuThichAdapter;
import com.codeandroid.engvi.SQLite.SQLHelper;
import com.codeandroid.engvi.Tu.Tu;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    public static TextView tvEV;
    public static EditText edtSearch;
    public static boolean isEV = true;
    public static List<Tu> tuList;
    public static List<Tu> tuListDX;
    public static List<Tu> tuListYT;
    public static SQLHelper sqlHelper;

    public static TuYeuThichAdapter tuYeuThichAdapter;
    public static TuDaXemAdapter tuDaXemAdapter;
    public static TraTuAdapter traTuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateListTu();
        Collections.sort(tuList, new Comparator<Tu>() {
            @Override
            public int compare(Tu tu, Tu t1) {
                return tu.getTuAnh().compareTo(t1.getTuAnh());
            }
        });
        sqlHelper = new SQLHelper(MainActivity.this);

        tuListDX = sqlHelper.getAllTuDaXem();
        tuListYT = sqlHelper.getAllTuYeuThich();

        tuYeuThichAdapter = new TuYeuThichAdapter(tuListYT, MainActivity.this);
        tuDaXemAdapter = new TuDaXemAdapter(tuListDX, MainActivity.this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tvEV = findViewById(R.id.tvEV);
        edtSearch = findViewById(R.id.searchBox);


        //header
        tvEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEV) {
                    tvEV.setText("V-E");
                    isEV = false;
                    traTuAdapter.setList(tuList);
                    tuDaXemAdapter.setList(tuListDX);
                    tuYeuThichAdapter.setList(tuListYT);
                } else {
                    tvEV.setText("E-V");
                    isEV = true;
                    traTuAdapter.setList(tuList);
                    tuDaXemAdapter.setList(tuListDX);
                    tuYeuThichAdapter.setList(tuListYT);
                }
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable.toString();
                List<Tu> tuTra = new ArrayList<>();
                List<Tu> tuTraDX = new ArrayList<>();
                List<Tu> tuTraYT = new ArrayList<>();
                for (int i = 0; i < tuList.size(); i++) {
                    if (isEV) {
                        if (tuList.get(i).getTuAnh().toLowerCase().contains(str)) {
                            tuTra.add(tuList.get(i));
                        }
                    } else {
                        if (tuList.get(i).getTuViet().toLowerCase().contains(str)) {
                            tuTra.add(tuList.get(i));
                        }
                    }
                }
                for (int i = 0; i < tuListDX.size(); i++) {
                    if (isEV) {
                        if (tuListDX.get(i).getTuAnh().toLowerCase().contains(str)) {
                            tuTraDX.add(tuListDX.get(i));
                        }
                    } else {
                        if (tuListDX.get(i).getTuViet().toLowerCase().contains(str)) {
                            tuTraDX.add(tuListDX.get(i));
                        }
                    }
                }

                for (int i = 0; i < tuListYT.size(); i++) {
                    if (isEV) {
                        if (tuListYT.get(i).getTuAnh().toLowerCase().contains(str)) {
                            tuTraYT.add(tuListYT.get(i));
                        }
                    } else {
                        if (tuListYT.get(i).getTuViet().toLowerCase().contains(str)) {
                            tuTraYT.add(tuListYT.get(i));
                        }
                    }
                }

                traTuAdapter.setList(tuTra);
                tuDaXemAdapter.setList(tuTraDX);
                tuYeuThichAdapter.setList(tuTraYT);
            }
        });


        //xử lý chuyển tab
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        viewPager2.setAdapter(new PagerFragment(this));
        tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("TRA TỪ");
                        break;
                    }
                    case 1: {
                        tab.setText("LỊCH SỬ");
                        break;
                    }
                    case 2: {
                        tab.setText("YÊU THÍCH");
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);
            }
        });


    }

    private void generateListTu() {
        tuList = new ArrayList<>();
        tuList.add(new Tu("abandon", "[əˈbandən]", "ngoại động từ", "abandon, forsake, let down, jilt", "bỏ rơi", "từ bỏ, bỏ rơi, ruồng bỏ", "abandon a wish", "từ bỏ một ước mơ"));
        tuList.add(new Tu("girl", "[ɡərl]", "danh từ", "girl, lady, daughter", "cô gái", "cô gái, con gái, thiếu nữ", "it's a beautiful girl", "đó là một cô gái xinh đẹp"));
        tuList.add(new Tu("boy", "[boi]", "danh từ", "boy, son, man-child", "cậu bé", "cậu bé, con trai, thanh niên", "the boy is going to school", "cậu bé đang đi đến trường"));
        tuList.add(new Tu("pen", "[pen]", "danh từ", "pen, plume", "cái bút", "cái bút, cây bút, cây viết", "Can I borrow a pen?", "Tôi có thể mượn một cái bút không?"));
        tuList.add(new Tu("pig", "[pig]", "danh từ", "pig, hog, swine", "con lợn", "con lợn, con heo", "the pig looks so cute", "con lợn trông rất đáng yêu"));
    }
}