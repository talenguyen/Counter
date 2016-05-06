package vn.tale.counter.ui.counter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.tale.counter.R;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class CounterActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counter);
  }
}
