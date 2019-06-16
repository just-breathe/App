package com.example.justbreathe.atti.Activity.Activities.Recommend;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.justbreathe.atti.Activity.Adapter.Recommend_RecyclerAdapter;
import com.example.justbreathe.atti.Activity.Object.RecAC_list;
import com.example.justbreathe.atti.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class RecommendActiivity extends AppCompatActivity {
    RecyclerView rcv;
    ArrayList<RecAC_list> items = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    JSONObject jsonObject;
    String image;
    String name;
    Recommend_RecyclerAdapter adapter;
    String location, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_activity);
        rcv = findViewById(R.id.recommend_recycler);
        adapter = new Recommend_RecyclerAdapter(items);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.rec_list_devieder_stroke));
        rcv.addItemDecoration(dividerItemDecoration);

        db.collection("places")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    jsonObject=new JSONObject(document.getData());
                                    image = jsonObject.getJSONArray("images").getString(0);
                                    name=jsonObject.getString("name");
                                    location=jsonObject.getString("location");
                                    day=jsonObject.getString("day");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                items.add(new RecAC_list(image,name,location,day));
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("DB", "Error getting documents: ", task.getException());
                        }
                    }
                });
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
    }
}
