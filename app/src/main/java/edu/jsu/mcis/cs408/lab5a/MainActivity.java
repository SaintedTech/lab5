package edu.jsu.mcis.cs408.lab5a;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
  // private Model model;

    private MemoPadController controller;
    private int memoIdForDel = 1;
    private final MemoPadItemClickHandler itemClick = new MemoPadItemClickHandler();


    //calls to delete memo, passes info to controller class.
    private boolean deleteMemo(){

        return controller.deleteMemo(this.memoIdForDel);

    }

    //main class, moved databasehandler to Model. Abstracted through contoller.
    private class MemoPadItemClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = binding.output.getChildLayoutPosition(v);
            RecyclerViewAdapter adapter = (RecyclerViewAdapter)binding.output.getAdapter();
            if (adapter != null) {
                Memo memo = adapter.getMemo(position);
                memoIdForDel = memo.getId();

                Toast.makeText(v.getContext(), String.valueOf(memoIdForDel), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public MemoPadItemClickHandler getItemClick() { return itemClick; }
    public void addNewMemo() {

        String info = binding.memoInput2.getText().toString();
        controller.addMemo(new Memo(info));
        updateRecyclerView();
    }
    private void updateRecyclerView() {



        ArrayList<Memo> memos = controller.getMemos();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, memos);
        binding.output.setHasFixedSize(true);
        binding.output.setLayoutManager(new LinearLayoutManager(this));
        binding.output.setAdapter(adapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        controller = new MemoPadController(this, null, null, 1);

        controller.addView(this);

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        updateRecyclerView();

        //gets first id for del
       ArrayList<Memo> memos = controller.getMemos();
       if(!memos.isEmpty()) {
           memoIdForDel = memos.get(0).getId();
       }



        binding.createMemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addNewMemo();
            }
        });
        binding.deleteMemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleted =   deleteMemo();
                if(deleted){
                    memoIdForDel+=1;
                }

                updateRecyclerView();


            }
        });



    }


}