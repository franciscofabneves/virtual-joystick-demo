package io.github.controlwear.joystickdemo;

/**
 * Created by germano on 18/10/17.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RealJoystick.newInstance();
            case 1:
                return VirtualJoystick.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return RealJoystick.TITLE;
            case 1:
                return VirtualJoystick.TITLE;
        }
        return super.getPageTitle(position);
    }
}