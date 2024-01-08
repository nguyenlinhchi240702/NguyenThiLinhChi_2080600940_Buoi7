package com.example.nguyenthilinhchi_2080600940_buoi7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthilinhchi_2080600940_buoi7.R;
import com.example.nguyenthilinhchi_2080600940_buoi7.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product> lstProduct;
    public ProductAdapter(List<Product> lstProducts){
        lstProduct=lstProducts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View productView= inflater.inflate(R.layout.item_product, parent,false);
        ViewHolder viewHolder= new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product temp= lstProduct.get(position);
        holder.txvProductID.setText(""+ temp.getID());//có thể bị lỗi
        holder.txvProductName.setText(temp.getName());
        holder.txvproductPrice.setText(""+ temp.getPrice());
        Context context= holder.imvProductImage.getContext();
        int imageID= context.getResources().getIdentifier(temp.getImage(),
                "drawable", context.getPackageName());
        if (imageID!=0){
            holder.imvProductImage.setImageResource(imageID);
        }
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductImage;
        TextView txvProductID, txvProductName, txvproductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProductImage= itemView.findViewById(R.id.imvProduct);
            txvProductID= itemView.findViewById(R.id.txvProductID);
            txvProductName= itemView.findViewById(R.id.txvProductName);
            txvproductPrice= itemView.findViewById(R.id.txvProductPrice);
        }
    }
    public void updateData(List<Product> newData) {
        lstProduct.clear();
        lstProduct.addAll(newData);
        notifyDataSetChanged();
    }
    public void deleteItem(int position) {
        // Xóa sản phẩm từ danh sách
        lstProduct.remove(position);
        // Cập nhật RecyclerView
        notifyDataSetChanged();
    }
    public Product getProductAtPosition(int position) {
        return lstProduct.get(position);
    }
}
