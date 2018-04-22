package com.smoo182.wguplanner.logic;

import com.smoo182.wguplanner.data.DataSourceInterface;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.view.interfaces.HomeViewInterface;

public class HomeController {

    private Quote tempQuote;

    private HomeViewInterface view;

    private DataSourceInterface dataSource;

    public HomeController(HomeViewInterface view, DataSourceInterface dataSource){
        this.view =view;
        this.dataSource = dataSource;

        getQuoteFromDataSource();

    }

    private void getQuoteFromDataSource() {
        view.setUpAdapterAndView(dataSource.getRandomQuote());
    }
}
