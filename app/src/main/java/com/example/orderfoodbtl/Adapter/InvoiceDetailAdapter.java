package com.example.orderfoodbtl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodbtl.DBHelper.DBHelper;
import com.example.orderfoodbtl.Model.Product;
import com.example.orderfoodbtl.R;

import java.util.List;

public class InvoiceDetailAdapter extends RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceDetailViewHolder>{
    private Context context;
    private List<Product> InvoiceDetailList;
    DBHelper dbHelper;

    public InvoiceDetailAdapter(Context context, List<Product> invoiceDetailList) {
        this.context = context;
        InvoiceDetailList = invoiceDetailList;
    }

    @NonNull
    @Override
    public InvoiceDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice_detail,parent,false);
        return new InvoiceDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceDetailViewHolder holder, int position) {
        Product product = InvoiceDetailList.get(position);
        holder.itemImage.setImageResource(product.getImageResId());
        holder.itemName.setText(product.getName());
        double price = product.getPrice();
        double total = product.getTotalPrice();
        String formattedprice = "$" + String.format("%.2f", price);
        String formattedtotal = "$" + String.format("%.2f", total);
        String fommattedQuantity = "x" + product.getQuantity();
        holder.Price.setText(formattedprice);
        holder.Quantity.setText(fommattedQuantity);
        holder.itemTotalPrice.setText(formattedtotal);
    }

    @Override
    public int getItemCount() {
        return InvoiceDetailList.size();
    }

    public static class InvoiceDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName,Price,Quantity,itemTotalPrice;
        public InvoiceDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            Price = itemView.findViewById(R.id.itemUnitPrice);
            itemTotalPrice = itemView.findViewById(R.id.itemTotalPrice);
            Quantity = itemView.findViewById(R.id.itemQuantity);

        }
    }
}
