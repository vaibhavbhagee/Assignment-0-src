package com.example.cop290.assignment0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {


    //This function is called when send button is pressed to send data to server for registration

    private void registerTeamID(Bundle b, final Context this_context_2) {


                                                                //Receiving data from calling funcniton in form of bundle
        String teamname = b.getString("teamname");
        EditText team = ((EditText) findViewById(R.id.team));
        team.setText(teamname);

        String name1Bundle = b.getString("name1");
        final String name1 = name1Bundle;

        String entry1Bundle = b.getString("entry1");
        final String entry1 = entry1Bundle;

        String name2Bundle = b.getString("name2");
        final String name2 = name2Bundle;

        String entry2Bundle = b.getString("entry2");
        final String entry2 = entry2Bundle;


        //validate text input here
        String Name_Pattern = "[a-zA-Z][a-zA-Z\\s\\_]*";
        String Entry_no_pattern = "[2][0][0-1][0-9]([B][B][1]|[B][B][5]|[C][E][1]|[C][H][1]|[C][H][7]|[C][S][1]|[C][S][5]|[E][E][1]|[E][E][3]|[E][E][5]|[M][T][1]|[M][T][6]|[P][H][1]|[T][T][1])[0-9][0-9][0-9][0-9]";

        //check for empty string
        if(((EditText) findViewById(R.id.name3)).getText().toString().equals("") || ((EditText) findViewById(R.id.entry3)).getText().toString().equals(""))
            new AlertDialog.Builder(this_context_2).setTitle("Error").setMessage("Field cannot be left Blank!!").setNeutralButton("Close", null).show();
            //check for correct name format
        else {
            if (!((EditText) findViewById(R.id.name3)).getText().toString().matches(Name_Pattern))
                new AlertDialog.Builder(this_context_2).setTitle("Error").setMessage("Enter the name of member 3 correctly!!").setNeutralButton("Close", null).show();
            else {
                //check for the correct entry number format
                if (!((EditText) findViewById(R.id.entry3)).getText().toString().matches(Entry_no_pattern))
                    new AlertDialog.Builder(this_context_2).setTitle("Error").setMessage("Enter the entry number of member 3 correctly!!").setNeutralButton("Close", null).show();
                else {

                    //The following event handler listens for response from server and takes appropriate action

                    final String ServerURL = "http://agni.iitd.ernet.in/cop290/assign0/register/";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerURL,
                            new Response.Listener<String>() {
                                @Override
                                //On valid response
                                public void onResponse(String response) {


                                    //Intent to navigate to another page, bundle to send data

                                    Intent intent = new Intent(this_context_2, Main5Activity.class);

                                    Bundle b = new Bundle();
                                    b.putString("teamname",((EditText) findViewById(R.id.team)).getText().toString());
                                    b.putString("entry1", entry1);
                                    b.putString("name1", name1);
                                    b.putString("entry2", entry2);
                                    b.putString("name2", name2);
                                    b.putString("entry3",((EditText) findViewById(R.id.entry3)).getText().toString());
                                    b.putString("name3",((EditText) findViewById(R.id.name3)).getText().toString());
                                    b.putString("response", response);
                                    intent.putExtras(b);



                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

                                }
                            },
                            //Launched when server return error
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Main4Activity.this, "Server Error. Enter smaller names or please try later.", Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        //Organises data as JSON to be sent to server through POST request
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("teamname", ((EditText) findViewById(R.id.team)).getText().toString());
                            params.put("entry1", entry1);
                            params.put("name1", name1);
                            params.put("entry2", entry2);
                            params.put("name2", name2);
                            params.put("entry3", ((EditText) findViewById(R.id.entry3)).getText().toString());
                            params.put("name3", ((EditText) findViewById(R.id.name3)).getText().toString());
                            return params;
                        }

                    };
                    //Manages the queue of requests
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);

                }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Context this_context = this;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        final Bundle b = getIntent().getExtras();


                                                                    //Receiving data from calling function using bundle
        String teamname = b.getString("teamname");
        EditText team = ((EditText) findViewById(R.id.team));
        team.setText(teamname);

        String name1Bundle = b.getString("name1");
        final String name1 = name1Bundle;

        String entry1Bundle = b.getString("entry1");
        final String entry1 = entry1Bundle;

        String name2Bundle = b.getString("name2");
        final String name2 = name2Bundle;

        String entry2Bundle = b.getString("entry2");
        final String entry2 = entry2Bundle;

        String name3Bundle = b.getString("name3");
        EditText name3 = ((EditText) findViewById(R.id.name3));
        name3.setText(name3Bundle);

        String entry3Bundle = b.getString("entry3");
        EditText entry3 = ((EditText) findViewById(R.id.entry3));
        entry3.setText(entry3Bundle);



        //Handles when forward button is pressed
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //When next button is pressed on last page this function is invoked to send data though server
                registerTeamID(b, this_context);



            }
        });
        //Handles when back button is pressed
        FloatingActionButton fabback = (FloatingActionButton) findViewById(R.id.fabback);
        fabback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(this_context, Main3Activity.class);
                //Intent and bundle to pass data to another activity
                Bundle b = new Bundle();
                b.putString("teamname",((EditText) findViewById(R.id.team)).getText().toString());
                b.putString("entry1", entry1);
                b.putString("name1", name1);
                b.putString("entry2", entry2);
                b.putString("name2", name2);
                b.putString("entry3",((EditText) findViewById(R.id.entry3)).getText().toString());
                b.putString("name3",((EditText) findViewById(R.id.name3)).getText().toString());
                intent.putExtras(b);



                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_ulta, R.anim.slide_out_ulta);


            }
        });


    }

}
