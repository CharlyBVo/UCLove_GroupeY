package com.example.charles_henry.testquimarche;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charles-henry on 24-04-16.
 */
public class HistoricActivity extends ListActivity {
    public static final String CHOSEN_TEXT ="texteChoisi";
    List<String> someStrings;
    String chosenString;
    String logtest;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logtest = getIntent().getStringExtra("LogUtil");
        RelationManager relM = new RelationManager(this);
        relM.open();
        someStrings=RelationManager.relToString(relM.getRelations(logtest),logtest);
        relM.close();
        if(someStrings==null){
            someStrings= new ArrayList<String>();
            someStrings.add("L'historique est vide");
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,someStrings);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenString= someStrings.get(position);

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