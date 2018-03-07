package com.example.ivanovnv.secondtaskforevalution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by IvanovNV on 05.03.2018.
 */

public class SettingsFragment extends Fragment {

    SharedPreferencesHelper mSharedPreferencesHelper = null;
    RadioGroup mRadioGroup;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * create view, read value in SharedPreferences, and check RadioGroup item
     *
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_settings,container,false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        mRadioGroup = v.findViewById(R.id.radio_group);
        View checkedItem = mRadioGroup.findViewWithTag(mSharedPreferencesHelper.readValue());

        if(checkedItem==null) {
            checkedItem=mRadioGroup.getChildAt(0);
        }

        mRadioGroup.check(checkedItem.getId());

        return v;
    }

    /**
     * set OnCheckedChangeListener for RadioGroup
     */
    @Override
    public void onResume() {
        mRadioGroup.setOnCheckedChangeListener(onSettingsCheckedChangeListener);
        super.onResume();
    }

    /**
     * activity paused, so unset nCheckedChangeListener for RadioGroup
     */

    @Override
    public void onPause() {
        mRadioGroup.setOnCheckedChangeListener(null);
        super.onPause();
    }

    /**
     * when destroy, free memory allocated in onCreateView
     */

    @Override
    public void onDestroyView() {
        mSharedPreferencesHelper=null;
        super.onDestroyView();
    }

    /**
     * save user chose in SharedPreferences
     */

    private RadioGroup.OnCheckedChangeListener onSettingsCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(mSharedPreferencesHelper!= null) {
                View v = group.findViewById(checkedId);
                mSharedPreferencesHelper.writeValue(v.getTag().toString());
            }
        }
    };


}
