package com.example.sailingrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //ListViewアダプター
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(btnClick);
    }

    private final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CsvReader parser = new CsvReader();
            List<ListData> list = parser.reader(getApplicationContext());
            //ListViewAdapter listViewAdapter = new ListViewAdapter(this, 0, parser.objects);
            adapter = new ListViewAdapter(view.getContext(), R.layout.list_item, randomList(list));
            ListView listView = (ListView)findViewById(R.id.mList);
            listView.setAdapter(adapter);

            Button btn = findViewById(R.id.btn_start);
            btn.setText("再編成");
            ImageView sortie = findViewById(R.id.mSortie);
            sortie.setVisibility(View.VISIBLE);

            ImageView image_sortie = findViewById(R.id.mSortie);
            image_sortie.setOnClickListener(sortieCick);
        }
    };

    private View.OnClickListener sortieCick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), EndActivity.class);
            startActivity(intent);
        }
    };

    private List<ListData> randomList(List<ListData> list){
        List<ListData> result = new ArrayList<>(); // ランダムに選択された要素を持たせるリスト
        List<ListData> remaining = new ArrayList<>(list); // 残っている要素のリスト
        Random random = new Random(); // 乱数生成器
        for (int i = 0; i < 6; i++) { // 6回繰り返す。
            int remainingCount = remaining.size(); // 残っている要素の数
            int index = random.nextInt(remainingCount); // ランダムに選択されたインデックス
            int id = i + 1;

            ListData element = remaining.get(index); // ランダムに選択された要素
            element.setId(String.valueOf(id));
            result.add(element); // ランダムに選択された要素を持たせるリストの末尾に、ランダムに選択された要素を追加する。

            int lastIndex = remainingCount - 1; // 残っている要素のリストの末尾のインデックス
            ListData lastElement = remaining.remove(lastIndex); // 残っている要素のリストから末尾を削除する。
            if (index < lastIndex) { // ランダムに選択された要素が末尾以外なら…
                remaining.set(index, lastElement); // それを末尾の要素で置換する。
            }
        }
        return result;
    }
}