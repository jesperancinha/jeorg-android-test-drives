package com.joai.ping2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Objects;

/**
 * Created by joao on 10-1-16.
 */
public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 -> fragment = new PingFragment();
            case 1 -> fragment = new TraceRouteFragment();
            default -> {
            }
        }
        return Objects.requireNonNull(fragment);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
