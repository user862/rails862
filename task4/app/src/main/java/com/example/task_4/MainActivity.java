package com.example.task_4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.SimpleMiddleware;
import com.koushikdutta.ion.Ion;
import com.scalified.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    /*@Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        startActivity(getIntent());
        //Refresh your stuff here
    }*/



    List<Project> projects;
void getProjects(Context cnt){
    Ion.with(cnt)
            .load(getString(R.string.kIndexRequest))
            .setTimeout(60 * 60 * 1000)
            .setHeader("content-type", "application/json; charset=utf-8")
            .setHeader("accept", "application/json")
            .asJsonArray()
            .setCallback(new FutureCallback<JsonArray>() {

                @Override
                public void onCompleted(Exception e, JsonArray result) {
                    Log.e("loggers","loggers : "+result);

                    if (result != null) {
                        projects = new ArrayList<Project>();
                        for (final JsonElement projectJsonElement : result) {
                            projects.add(new Gson().fromJson(projectJsonElement, Project.class));
                        }
                        ListView myList1 = (ListView) findViewById(R.id.mylist);
                        CustomAdapter adapter1 = new CustomAdapter(cnt);
                        adapter1.addProjects(projects,cnt);

                        myList1.setAdapter(adapter1);


                    } else {
                        Toast toast = Toast.makeText(cnt,"no connection",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }

            });
}

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("key");
                Toast toast = Toast.makeText(this,"check",Toast.LENGTH_LONG);
                toast.show();
                getProjects(this);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/opensanslight.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        Toolbar toolbar = findViewById(R.id.tvTitle1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ListView myList1 = (ListView) findViewById(R.id.mylist);

        getProjects(this);

        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.setImageResource(R.drawable.fab_plus_icon);
        actionButton.setButtonColor(getResources().getColor(R.color.accentColor));
        actionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_button:

                Intent intent = new Intent(this, NewTodoActivity.class);
                Bundle b=new Bundle();

                String[][] projectsInfo = new String[projects.size()][];
                int i = 0;
                for(Project project: projects){
                    projectsInfo[i] = new String[2];
                    projectsInfo[i][0] = Integer.toString(project.getId());
                    projectsInfo[i][1] = project.getTitle();
                    i++;
                }
                b.putSerializable("key",projectsInfo);
                intent.putExtras(b);
                startActivityForResult(intent,1);
                break;
            default:
                break;

        }
    }


}
