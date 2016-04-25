package com.example.charles_henry.testquimarche;

/**
 * Created by charles-henry on 23-04-16.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

/**
 * Created by charles-henry on 02-03-16.
 */
public class FriendListActivity extends ListActivity {
    public static final String CHOSEN_TEXT ="texteChoisi";
    List<String> someStrings;
    String chosenString;
    String logtest;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logtest = getIntent().getStringExtra("LogUtil");
        UtilisateurManager utilM= new UtilisateurManager(this);
        utilM.open();
        someStrings=utilM.getFriendsofUser(logtest);
        utilM.close();
        if(someStrings==null){
            someStrings= new ArrayList<String>();
            someStrings.add("Vous n'avez pas d'amis :/");
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,someStrings);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenString= someStrings.get(position);
                Toast.makeText(FriendListActivity.this, "Vous avez choisi :" + chosenString, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void finish()
    {
        if(chosenString!=null)
        {
            Intent data= new Intent();
            data.putExtra(CHOSEN_TEXT,chosenString);
            setResult(RESULT_OK,data);
        }
        super.finish();
    }
}
