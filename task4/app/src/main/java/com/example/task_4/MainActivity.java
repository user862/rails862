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

    List<Project> projects;

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


        Gson gson = new Gson();
        projects = new ArrayList<Project>();

        /*

        recieve


        Ion.with(this)
                .load(getString(R.string.kIndexRequest))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {

                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (result != null) {
                            List<Project> projects = new ArrayList<Project>();
                            for (final JsonElement projectJsonElement : result) {
                                projects.add(new Gson().fromJson(projectJsonElement, Project.class));
                            }

                        }

                    }

                });

        */


        String json =
                "[" +
                        "		{" +
                        "			\"id\": 1," +
                        "			\"title\": \"Семья\"," +
                        "			\"todos\": [{" +
                        "					\"id\": 1," +
                        "					\"text\": \"Купить молоко\"," +
                        "					\"isCompleted\": false" +
                        "				}," +
                        "				{" +
                        "					\"id\": 2," +
                        "					\"text\": \"Забрать обувь\"," +
                        "					\"isCompleted\": true" +
                        "				}," +
                        "				{" +
                        "					\"id\": 3," +
                        "					\"text\": \"Сходить в кино\"," +
                        "					\"isCompleted\": false" +
                        "				}" +
                        "			]" +
                        "		}," +
                        "		{" +
                        "			\"id\": 2," +
                        "			\"title\": \"Работа\"," +
                        "			\"todos\": [{" +
                        "					\"id\": 4," +
                        "					\"text\": \"Позвонить заказчику\"," +
                        "					\"isCompleted\": true" +
                        "				}," +
                        "				{" +
                        "					\"id\": 5," +
                        "					\"text\": \"Уйти пораньше\"," +
                        "					\"isCompleted\": false" +
                        "				}" +
                        "			]" +
                        "		}" +
                        "	]";


        JsonParser jsonParser = new JsonParser();
        JsonArray result = (JsonArray) jsonParser.parse(json);

        for (final JsonElement projectJsonElement : result)
            projects.add(new Gson().fromJson(projectJsonElement, Project.class));

        CustomAdapter adapter1 = new CustomAdapter(this);
        adapter1.addProjects(projects);
        ListView myList1 = (ListView) findViewById(R.id.mylist);
        myList1.setAdapter(adapter1);

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
                startActivity(intent);
                break;
            default:
                break;

        }
    }


}
