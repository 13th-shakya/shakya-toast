package com.example.shakyatoast;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.shakyatoast.databinding.ActivityMainBinding;
import com.example.shakyatoast.databinding.ToastBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button.setOnClickListener(view -> {
            final AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("請選擇功能")
                    .setMessage("請更具下方按鈕選擇要顯示的物件")
                    .setNeutralButton("取消", (dialogInterface, i) ->
                            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show()
                    )
                    .setNegativeButton("自定義 Toast", (dialogInterface, i) -> showCustomDialog())
                    .setPositiveButton("顯示 list", (dialogInterface, i) -> showListDialog())
                    .create();
            dialog.show();
        });
    }

    private void showCustomDialog() {
        Toast toast = new Toast(this);
        ToastBinding toastBinding = ToastBinding.inflate(getLayoutInflater());
        toast.setGravity(Gravity.TOP, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastBinding.getRoot());
        toast.show();
    }

    private void showListDialog() {
        final String[] list = {
                "message1",
                "message2",
                "message3",
                "message4",
                "message5",
        };
        AlertDialog listDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("使用 List 呈現")
                .setItems(list, ((dialogInterface, i) -> {
                    Toast.makeText(MainActivity.this, "您選擇的是 " + list[i], Toast.LENGTH_SHORT).show();
                }))
                .create();
        listDialog.show();
    }
}
