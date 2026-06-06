package com.smartvillage.asmanimesh.ultimate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText searchBar;
    private Button btnMovies, btnDrama, btnNews, btnYouTube;
    private RecyclerView recyclerViewTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);
        btnMovies = findViewById(R.id.btnMovies);
        btnDrama = findViewById(R.id.btnDrama);
        btnNews = findViewById(R.id.btnNews);
        btnYouTube = findViewById(R.id.btnYouTube);
        recyclerViewTimeline = findViewById(R.id.recyclerViewTimeline);

        if (recyclerViewTimeline != null) {
            recyclerViewTimeline.setLayoutManager(new LinearLayoutManager(this));
        }

        initAsmaniMeshHourlyEngine();
    }

    private void initAsmaniMeshHourlyEngine() {
        PeriodicWorkRequest meshHourlyRequest = new PeriodicWorkRequest.Builder(
                AsmaniMeshWorker.class, 1, TimeUnit.HOURS)
                .build();
        WorkManager.getInstance(this).enqueue(meshHourlyRequest);
    }

    public static class AsmaniMeshWorker extends androidx.work.Worker {
        public AsmaniMeshWorker(android.content.Context context, androidx.work.WorkerParameters params) {
            super(context, params);
        }
        @Override
        public androidx.work.Result doWork() {
            return androidx.work.Result.success();
        }
    }
}
