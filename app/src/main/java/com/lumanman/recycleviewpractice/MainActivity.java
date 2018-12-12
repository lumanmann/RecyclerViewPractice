package com.lumanman.recycleviewpractice;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false); // 多型


        rv.setLayoutManager(layoutManager); // 設定


        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("Title A");
        titleList.add("Title B");
        titleList.add("Title C");
        titleList.add("Title D");
        titleList.add("Title E");
        titleList.add("Title F");
        titleList.add("Title G");

        TestAdapter testAdapter = new TestAdapter(titleList);
        rv.setAdapter(testAdapter);
    }

    private class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<String> titleList;

        // 在View上面包一層
        public class TestViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public TestViewHolder(View view) {
                super(view);
                textView = view.findViewById(R.id.textView);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });


            }
        }

        public TestAdapter(ArrayList<String> titleList) {
            this.titleList = new ArrayList<>(titleList);

        }


        // 當創建ViewHolder時需要產生的對象(Layout實體)
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv, viewGroup, false);
            return new TestViewHolder(view);
        }
        // 當ViewHolder被回收再利用(含第一次被使用)時會呼叫此方法綁定數據
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof  TestViewHolder) {
                TestViewHolder testViewHolder = (TestViewHolder)viewHolder;
                testViewHolder.textView.setText(titleList.get(i));
            }
        }
        // 當前列表中顯示的Item總數
        @Override
        public int getItemCount() {
            return titleList.size();
        }
    }

}
