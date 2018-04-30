package com.smoo182.wguplanner.logic;

import android.view.View;

import com.smoo182.wguplanner.data.DataSourceInterface;
import com.smoo182.wguplanner.data.datatypes.ListItem;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.view.interfaces.ListViewInterface;

import java.util.ArrayList;
import java.util.List;

public class TermsController {

    private ListItem temporaryListItem;
    private int temporaryListItemPosition;


    private ListViewInterface view;
    private DataSourceInterface dataSource;

    public TermsController(ListViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        getListFromDataSource();
    }

    public TermsController() {
    }

    public void onListItemClick(ListItem selectedItem, View viewRoot) {
        view.startDetailActivity(
                selectedItem.getTitle(),
                viewRoot
        );
    }

    public void getListFromDataSource() {
        List<ListItem> listItems = populateList();

        view.setUpAdapterAndView(listItems);
    }

    public List<ListItem> populateList() {
        ArrayList<ListItem> listItems = new ArrayList<>();
        List<Term> termList = dataSource.getTermList();
        for (Term term:termList) {

            listItems.add(new ListItem(term.getTitle(), (term.getStartDate() + " - " + term.getEndDate())));
        }
        return listItems;
    }


    public void createNewListItem() {
        Term newItem = dataSource.addNewTerm();
        view.addNewListItemToView(newItem);
    }

    public void onListItemSwiped(int position, ListItem listItem) {
        //ensure that the view and data layers have consistent state
        dataSource.deleteTerm(listItem.getTitle());
        view.deleteListItemAt(position);

        temporaryListItemPosition = position;
        temporaryListItem = listItem;

    }


}

