package com.smoo182.wguplanner.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.FakeDataSource;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.logic.HomeController;
import com.smoo182.wguplanner.view.interfaces.HomeViewInterface;

public class HomeActivity extends BaseActivity implements HomeViewInterface {

    private LayoutInflater layoutInflater;
    private TextView contentTextView;
    private TextView authorTextView;
    private HomeController homeController;

    @SuppressLint("RestrictedApi")
    @Override
    public void setUpAdapterAndView(Quote quote) {
        this.contentTextView.setText(quote.getContent());
        this.authorTextView.setText(quote.getAuthor());
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_home;
    }

    public void populateScreen(){
        contentTextView = (TextView) findViewById(R.id.text_quote);
        authorTextView = (TextView) findViewById(R.id.text_quote_author);

        homeController = new HomeController(this, new FakeDataSource());

    }
}
