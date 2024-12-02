package com.example.orderfoodbtl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodbtl.Adapter.InvoiceDetailAdapter;
import com.example.orderfoodbtl.DBHelper.DBHelper;
import com.example.orderfoodbtl.Model.InvoiceDetail;
import com.example.orderfoodbtl.Model.Product;

import java.util.List;

public class InvoiceDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InvoiceDetailAdapter invoiceDetailAdapter;
    private ImageView BackToInvoice;
    private TextView DSubtotalValue,DDeliveryValue,DTotalValue;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_invoice_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(this);
        DDeliveryValue = findViewById(R.id.DDeliveryValue);
        DSubtotalValue = findViewById(R.id.DSubtotalValue);
        DTotalValue = findViewById(R.id.DTotalValue);
        Intent intent = getIntent();
        String invoiceId = intent.getStringExtra("invoice_id");
        String total = intent.getStringExtra("total");
        int invoiceID = Integer.parseInt(invoiceId);
        DTotalValue.setText(total);
        double delivery = dbHelper.getDelivery(invoiceID);
        double subtotal = dbHelper.SubTotalDetailInvoicePrice(invoiceID);

        String formatteddelivery = "$" + String.format("%.2f", delivery);
        String formattedsubtotal = "$" + String.format("%.2f", subtotal);
        DSubtotalValue.setText(formattedsubtotal);
        DDeliveryValue.setText(formatteddelivery);
        recyclerView = findViewById(R.id.RvDetailInvoice);
        BackToInvoice = findViewById(R.id.BackToInvoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Product> InvoiceDetailList = dbHelper.getInvoiceDetail(invoiceID);

        invoiceDetailAdapter = new InvoiceDetailAdapter(this,InvoiceDetailList);
        recyclerView.setAdapter(invoiceDetailAdapter);

        BackToInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InvoiceDetailActivity.this, InvoiceActivity.class);
                startActivity(intent1);
            }
        });
    }
}