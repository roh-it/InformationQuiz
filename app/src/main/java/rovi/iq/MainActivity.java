package rovi.iq;

/**
 * Created by Rrohi on 12-02-2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;int count=0;


    ArrayList<Details> list=new ArrayList<>();
    int[] image_id={R.drawable.gk,R.drawable.zz,R.drawable.gg,R.drawable.ss,R.drawable.mo};
    String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        name=getResources().getStringArray(R.array.categoryname);


        for(String Name:name)
        {

            Details details=new Details(image_id[count],Name);
            count++;
            list.add(details);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new rovi.iq.DetailsAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    public void move()
    {
            Intent i=new Intent(MainActivity.this,GeneralKnowledgeActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent i=new Intent(MainActivity.this,HomeScreen.class);
        startActivity(i);
        finish();
    }

}