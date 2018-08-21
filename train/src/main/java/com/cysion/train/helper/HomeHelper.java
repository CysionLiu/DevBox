package com.cysion.train.helper;

import android.support.v4.app.Fragment;

import com.cysion.baselib.Box;
import com.cysion.train.R;
import com.cysion.train.fragment.DingzhiFragment;
import com.cysion.train.fragment.HomeFragment;
import com.cysion.train.fragment.MainListFragment;
import com.cysion.train.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeHelper {

    private static volatile HomeHelper instance;

    private HomeHelper() {
    }

    public static synchronized HomeHelper obj() {
        if (instance == null) {
            instance = new HomeHelper();
        }
        return instance;
    }

    public List<String> getTitles() {
        List<String> strings = new ArrayList<>();
        strings.add(Box.str(R.string.home_study));
        strings.add(Box.str(R.string.home_hot));
        strings.add(Box.str(R.string.home_dingzhi));
        strings.add(Box.str(R.string.home_user));
        return strings;
    }
    public List<String> getTopTitles() {
        List<String> strings = new ArrayList<>();
        strings.add(Box.str(R.string.app_name));
        strings.add(Box.str(R.string.str_meeting_list));
        strings.add(Box.str(R.string.home_dingzhi));
        strings.add(Box.str(R.string.home_user));
        return strings;
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new MainListFragment());
        fragmentArrayList.add(new DingzhiFragment());
        fragmentArrayList.add(new UserFragment());
        return fragmentArrayList;
    }
    public int[] getIcons(){
        int[] ints = new int[4];
        ints[0] = R.drawable.home_study_selector;
        ints[1] = R.drawable.home_hot_selector;
        ints[2] = R.drawable.home_dingzhi_selector;
        ints[3] = R.drawable.home_user_selector;
        return ints;
    }
}
