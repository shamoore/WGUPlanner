package com.smoo182.wguplanner.logic;

import com.smoo182.wguplanner.data.PlannerDao;
import com.smoo182.wguplanner.view.interfaces.HomeViewInterface;

public class HomeController {

    private HomeViewInterface view;

    private PlannerDao dataSource;

    public HomeController(HomeViewInterface view, PlannerDao dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getQuoteFromDataSource();

    }

    private void getQuoteFromDataSource() {
        view.setUpAdapterAndView(dataSource.getRandomQuote());
    }
}
