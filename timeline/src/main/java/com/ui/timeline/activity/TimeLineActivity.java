package com.ui.timeline.activity;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ui.timeline.Analog;
import com.ui.timeline.tlineadapter.AnalogAdapter;
import com.ui.timeline.R;
import com.ui.timeline.TimeLineDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.ui.timeline.TimeLineDecoration.BEGIN;
import static com.ui.timeline.TimeLineDecoration.END_FULL;
import static com.ui.timeline.TimeLineDecoration.NORMAL;

/**
 * Created by lin18 on 2017/8/23.
 */
public class TimeLineActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    AnalogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);



        recyclerView = findViewById(R.id.recyclerView);

        initView();
        initData();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final TimeLineDecoration decoration = new TimeLineDecoration(this)
                .setLineColor(android.R.color.black)
                .setLineWidth(1)
                .setLeftDistance(16)
                .setTopDistance(16)
                .setBeginMarker(R.drawable.begin_marker)
                .setMarkerRadius(4)
                .setMarkerColor(R.color.colorAccent)
                .setCallback(new TimeLineDecoration.TimeLineAdapter() {

                    @Nullable
                    @Override
                    public Rect getRect(int position) {
                        return new Rect(0, 16, 0, 16);
                    }

                    @Override
                    public int getTimeLineType(int position) {
                        if (position == 0) return BEGIN;
                        else if (position == adapter.getItemCount() - 1) return END_FULL;
                        else return NORMAL;
                    }
                });
        recyclerView.addItemDecoration(decoration);

        adapter = new AnalogAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        final List<Analog> analogs = new ArrayList<>();

        Analog analog0 = new Analog();
        analog0.isHead = true;
        analog0.text = "更新了日志";
        analog0.time = "2016-01-08 10:20:10";
        analogs.add(analog0);

        Analog analog1 = new Analog();
        analog1.isHead = false;
        analog1.text = "上传了图片";
        analog1.time = "2016-01-02 15:10:10";
        analogs.add(analog1);

        Analog analog2 = new Analog();
        analog2.isHead = false;
        analog2.text = "开通了空间";
        analog2.time = "2016-01-01 10:10:10";
        analogs.add(analog2);

        adapter.setItems(analogs);
    }

}
