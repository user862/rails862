package com.example.task_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class NewTodoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                finish();
            case R.id.send:
                EditText edite_Text = (EditText) findViewById(R.id.newTodeText);
                String newTodeText = edite_Text.getText().toString();
                if(pr_list.getCheckedItemPosition()!=-1 && !newTodeText.isEmpty()){
                    int projectId = Integer.parseInt(projectsInfo[pr_list.getCheckedItemPosition()][0]);

                    JsonObject json = new JsonObject();
                    json.addProperty("project_id", projectId);
                    json.addProperty("text", newTodeText);

                    Ion.with(this)
                            .load(getString(R.string.kIndexRequestCreate))
                            .setJsonObjectBody(json)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    Intent intent = new Intent();
                                    intent.putExtra("key", "value");
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            });
                    Intent returnIntent = new Intent(this, MainActivity.class);
                    setResult(RESULT_CANCELED, returnIntent);
                    finish();
                    //Toast toast = Toast.makeText(this,"projectId: "+projectsInfo[pr_list.getCheckedItemPosition()][0]+" text: "+newTodeText,Toast.LENGTH_LONG);
                    //toast.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private ListView pr_list;
    private String[][] projectsInfo;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle b=this.getIntent().getExtras();
        projectsInfo = (String[][]) b.getSerializable("key");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/opensanslight.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        String[] projectsNames = new String[projectsInfo.length];
        int i=0;
        for(String[] project: projectsInfo){
            projectsNames[i] = projectsInfo[i][1];
            i++;
        }

        Toolbar toolbar = findViewById(R.id.tvTitle2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        pr_list = (ListView) findViewById(R.id.project_list);
        pr_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.projectlist_cell);
        adapter.addAll(projectsNames);
        pr_list.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
    }
}
