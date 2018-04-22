package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.FakeDataSource;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.logic.HomeController;
import com.smoo182.wguplanner.view.interfaces.HomeViewInterface;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface {

   private LayoutInflater layoutInflater;
   private TextView contentTextView;
   private TextView authorTextView;
   private HomeController homeController;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contentTextView = (TextView) findViewById(R.id.text_quote);
        authorTextView = (TextView) findViewById(R.id.text_quote_author);
        homeController = new HomeController(this, new FakeDataSource());
    }


    @Override
    public void setUpAdapterAndView(Quote quote) {
        this.contentTextView.setText(quote.getContent());
        this.authorTextView.setText(quote.getAuthor());

    }
}
