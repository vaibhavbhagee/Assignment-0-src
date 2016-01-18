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

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Context this_context = this;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

                                                                            //Receiving data from calling activity through bundles
        Bundle b = getIntent().getExtras();
        String teamname = b.getString("teamname");
        EditText team = ((EditText) findViewById(R.id.team));
        team.setText(teamname);

        String name1Bundle = b.getString("name1");
        EditText name1 = ((EditText) findViewById(R.id.name1));
        name1.setText(name1Bundle);

        String entry1Bundle = b.getString("entry1");
        EditText entry1 = ((EditText) findViewById(R.id.entry1));
        entry1.setText(entry1Bundle);

        String name2Bundle = b.getString("name2");
        final String name2 = name2Bundle;

        String entry2Bundle = b.getString("entry2");
        final String entry2 = entry2Bundle;

        String name3Bundle = b.getString("name3");
        final String name3 = name3Bundle;

        String entry3Bundle = b.getString("entry3");
        final String entry3 = entry3Bundle;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validate text input here
                String Name_Pattern = "[a-zA-Z][a-zA-Z\\s\\_]*";
                String Entry_no_pattern = "[2][0][0-1][0-9]([B][B][1]|[B][B][5]|[C][E][1]|[C][H][1]|[C][H][7]|[C][S][1]|[C][S][5]|[E][E][1]|[E][E][3]|[E][E][5]|[M][T][1]|[M][T][6]|[P][H][1]|[T][T][1])[0-9][0-9][0-9][0-9]";

                //check for empty string
                if(((EditText) findViewById(R.id.name1)).getText().toString().equals("") || ((EditText) findViewById(R.id.entry1)).getText().toString().equals(""))
                    new AlertDialog.Builder(this_context).setTitle("Error").setMessage("Field cannot be left Blank!!").setNeutralButton("Close", null).show();
                    //check for correct name format
                else {
                    if (!((EditText) findViewById(R.id.name1)).getText().toString().matches(Name_Pattern))
                        new AlertDialog.Builder(this_context).setTitle("Error").setMessage("Enter the name of member 1 correctly!!").setNeutralButton("Close", null).show();
                    else {
                        //check for the correct entry number format
                        if (!((EditText) findViewById(R.id.entry1)).getText().toString().matches(Entry_no_pattern))
                            new AlertDialog.Builder(this_context).setTitle("Error").setMessage("Enter the entry number of member 1 correctly!!").setNeutralButton("Close", null).show();
                        else {
                            //Intent is used to navigate to another activity/page. Bundle used to pass data

                            Intent intent = new Intent(this_context, Main3Activity.class);

                            Bundle b = new Bundle();
                            b.putString("teamname", ((EditText) findViewById(R.id.team)).getText().toString());
                            b.putString("entry1", ((EditText) findViewById(R.id.entry1)).getText().toString());
                            b.putString("name1", ((EditText) findViewById(R.id.name1)).getText().toString());
                            b.putString("entry2", entry2);
                            b.putString("name2", name2);
                            b.putString("entry3", entry3);
                            b.putString("name3", name3);
                            intent.putExtras(b);


                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                        }
                    }
                }
            }
        });


        FloatingActionButton fabback = (FloatingActionButton) findViewById(R.id.fabback);
        fabback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This similar function is for the back button

                Intent intent = new Intent(this_context, MainActivity.class);

                Bundle b = new Bundle();
                b.putString("teamname",((EditText) findViewById(R.id.team)).getText().toString());
                b.putString("entry1", ((EditText) findViewById(R.id.entry1)).getText().toString());
                b.putString("name1",((EditText) findViewById(R.id.name1)).getText().toString());
                b.putString("entry2", entry2);
                b.putString("name2", name2);
                b.putString("entry3",entry3);
                b.putString("name3", name3);
                intent.putExtras(b);



                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_ulta, R.anim.slide_out_ulta);




            }
        });


    }

}
