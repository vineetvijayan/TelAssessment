package com.example.telassessment.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.telassessment.R;

public class CardsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cards);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CardListFragment.newInstance())
                    .commitNow();
        }
    }
}
