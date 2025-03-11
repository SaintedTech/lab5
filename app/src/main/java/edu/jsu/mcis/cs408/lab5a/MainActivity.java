package edu.jsu.mcis.cs408.lab5a;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import edu.jsu.mcis.cs408.lab5a.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private Model model;

    private MemoPadController controller;

    //main class, moved databasehandler to Model. Abstracted through contoller.

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        controller = new MemoPadController(this, null, null, 1);
        controller.addView(this);

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        updateRecyclerView();

        binding.createMemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addNewMemo();
            }
        });
    }
    public void addNewMemo() {

        String info = binding.memoInput2.getText().toString();
        controller.addMemo(new Memo(info));
        updateRecyclerView();
    }
    private void updateRecyclerView() {

        //Will need to move to work with fire property change
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(controller.getMemos());
        binding.output.setHasFixedSize(true);
        binding.output.setLayoutManager(new LinearLayoutManager(this));
        binding.output.setAdapter(adapter);
    }

}