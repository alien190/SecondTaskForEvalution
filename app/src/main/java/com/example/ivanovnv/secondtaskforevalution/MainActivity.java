package com.example.ivanovnv.secondtaskforevalution;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null) {
            showFragment(0,false);
        }
    }

    /**
    Create main menu with three items: settings, search and exit;
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);

        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
    When select menu item program show toast with menu item name
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        showFragment(item.getItemId(),true);
        return true;
    }

    /**
     Replace fragment in dependency of menu selected item
     */

    private void showFragment(int id,boolean addToBackStack){
        Fragment fragment=null;

        switch (id) {
            case R.id.settings:
                fragment = SettingsFragment.newInstance();
                break;

            case R.id.find:
                fragment = FindFragment.newInstance();
                break;

            case R.id.exit:
                this.finish();
                break;

            default:
                fragment = MainFragment.newInstance();
                break;
        }

        if(fragment!=null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment);

            if(addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().toString());
            }

            fragmentTransaction.commit();
        }
    }
}
