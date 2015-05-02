package com.mapia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MapActivity extends FragmentActivity implements OnClickListener {

    int currentFragmentIndex;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mapia.R.layout.activity_map);

        btn1 = (Button)findViewById(com.mapia.R.id.btn_Fragment1);
        btn2 = (Button)findViewById(com.mapia.R.id.btn_Fragment2);
        btn3 = (Button)findViewById(com.mapia.R.id.btn_Fragment3);
        btn4 = (Button)findViewById(com.mapia.R.id.btn_Fragment4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        currentFragmentIndex = 1;

        fragmentReplace(currentFragmentIndex);
    }

    private void fragmentReplace(int newFragmentIndex) {
        Fragment newFragment = null;
        newFragment = getFragment(newFragmentIndex);

        final android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(com.mapia.R.id.mapShowFragment, newFragment);
        transaction.commit();
    }


    private Fragment getFragment(int index) {
        Fragment newFragment = null;
        switch(index){
            case 1:
                newFragment = new MapPrivateFragment();
                break;
            case 2:
                newFragment = new MapGroupFragment();
                break;
            case 3:
                newFragment = new MapFollowFragment();
                break;
            case 4:
                newFragment = new MapPublicFragment();
                break;
            default:
                break;
        }
        return newFragment;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case com.mapia.R.id.btn_Fragment1:
                currentFragmentIndex = 1;
                break;
            case com.mapia.R.id.btn_Fragment2:
                currentFragmentIndex = 2;
                break;
            case com.mapia.R.id.btn_Fragment3:
                currentFragmentIndex = 3;
                break;
            case com.mapia.R.id.btn_Fragment4:
                currentFragmentIndex = 4;
                break;
        }
        fragmentReplace(currentFragmentIndex);
    }
}
