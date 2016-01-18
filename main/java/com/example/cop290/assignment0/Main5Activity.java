package com.example.cop290.assignment0;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle b = getIntent().getExtras();

        String serverresult = b.getString("response");
        final String res_split[] = serverresult.split(" ");

        Toast.makeText(Main5Activity.this, serverresult.split("\"")[5], Toast.LENGTH_LONG).show();
        Log.d("Response", serverresult);

                                                            //Modifies the layout and message of final screen depending whether registration is successful or nit
        if(res_split[1].equals("1,")==true)
            setContentView(R.layout.activity_main5);
        else {
            setContentView(R.layout.activity_main5_fail);


            TextView tv = (TextView) findViewById(R.id.textView);
            if (res_split[3].equals("\"Data") == true)
                tv.setText("Please enter data properly.");
            else
                tv.setText("Already Registered. Go register in some other course.");
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Depending on whether registration successful or not, go back to previous page to correct errors or home page to registerr new team

                if (res_split[1].equals("1,") == true) {
                    Intent i = new Intent(Main5Activity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                } else {
                    Intent t = new Intent(Main5Activity.this, Main4Activity.class);
                    t.putExtras(b);
                    startActivity(t);
                    overridePendingTransition(R.anim.slide_in_ulta, R.anim.slide_out_ulta);


                }
            }
        });

    }

}
