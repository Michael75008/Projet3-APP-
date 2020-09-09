package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return NeighbourFragment.newInstance(false);

            default:
                return NeighbourFragment.newInstance(true);
        }
    }

    /**
     * get the number of pages
     */

    @Override
    public int getCount() {
        return 2;
    }
}