package com.example.cop290.assignment0;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {        //This function launched when activity loads



        String TeamNameString = "";
        String Entry1 = "";
        String Name1 = "";
        String Entry2 = "";
        String Name2 = "";
        String Entry3 = "";
        String Name3 = "";


        final Context this_context = this;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bundle b = getIntent().getExtras();                 //Receiving bundle when another activity calls this and passes values

        String teamname = (b!=null)?b.getString("teamname"):"";
        EditText team = ((EditText) findViewById(R.id.team));
        team.setText(teamname);

        String name1Bundle = (b != null) ? b.getString("name1"):"";
        final String name1 =  name1Bundle ;
        String entry1Bundle = (b != null) ?b.getString("entry1"):"";
        final String entry1 = entry1Bundle;
        String name2Bundle = (b != null) ?b.getString("name2"):"";
        final String name2 =  name2Bundle;
        String entry2Bundle = (b != null) ?b.getString("entry2"):"";
        final String entry2 = entry2Bundle;
        String name3Bundle = (b != null) ?b.getString("name3"):"";
        final String name3 = name3Bundle;
        String entry3Bundle = (b != null) ?b.getString("entry3"):"";
        final String entry3 = entry3Bundle;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validate text input here
                String Name_Pattern = "[a-zA-Z][a-zA-Z\\s\\_]*";

                //check for empty string
                if(((EditText) findViewById(R.id.team)).getText().toString().equals(""))
                    new AlertDialog.Builder(this_context).setTitle("Error").setMessage("Field cannot be left Blank!!").setNeutralButton("Close", null).show();
                    //check for correct team name format
                else {
                    if (!((EditText) findViewById(R.id.team)).getText().toString().matches(Name_Pattern))
                        new AlertDialog.Builder(this_context).setTitle("Error").setMessage("Enter the name of the team correctly!!").setNeutralButton("Close", null).show();
                    else {
                        //Creating Intent to navigate to another activity, using bundle to pass data

                        Intent intent = new Intent(this_context, Main2Activity.class);

                        Bundle b = new Bundle();
                        b.putString("teamname", ((EditText) findViewById(R.id.team)).getText().toString());
                        b.putString("entry1", entry1);
                        b.putString("name1", name1);
                        b.putString("entry2", entry2);
                        b.putString("name2", name2);
                        b.putString("entry3", entry3);
                        b.putString("name3", name3);
                        intent.putExtras(b);


                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                        //Animation
                    }

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
