package com.smoo182.wguplanner.view.activities;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.logic.HomeController;
import com.smoo182.wguplanner.view.interfaces.HomeViewInterface;

public class HomeActivity extends BasePrimaryActivity implements HomeViewInterface {

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

    }
}
