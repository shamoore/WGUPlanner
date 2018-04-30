package com.smoo182.wguplanner.view.interfaces;

import android.view.View;

import com.smoo182.wguplanner.data.datatypes.ListItem;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.List;

public interface ListViewInterface {

        void startDetailActivity(String title, View viewRoot);

        void setUpAdapterAndView(List<ListItem> listOfData);

        void addNewListItemToView(Term newItem);

        void deleteListItemAt(int position);

        void insertListItemAt(int temporaryListItemPosition, ListItem temporaryListItem);
}
