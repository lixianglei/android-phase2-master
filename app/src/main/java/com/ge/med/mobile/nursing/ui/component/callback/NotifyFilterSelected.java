package com.ge.med.mobile.nursing.ui.component.callback;

import com.ge.med.mobile.nursing.ui.component.DataFilterMenu;

/**
 * Created by Qu on 2016/12/15.
 */
public interface NotifyFilterSelected {
    void afterFilterSelected(DataFilterMenu.FilterItem selectedFilter);
    boolean beforeFilterSelected(DataFilterMenu.FilterItem selectedFilter);
}
