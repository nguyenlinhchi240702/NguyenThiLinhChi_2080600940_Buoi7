package com.example.nguyenthilinhchi_2080600940_buoi7;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthilinhchi_2080600940_buoi7.adapter.ProductAdapter;
import com.example.nguyenthilinhchi_2080600940_buoi7.dao.ProductDAO;
import com.example.nguyenthilinhchi_2080600940_buoi7.model.Product;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private ProductAdapter adapter;
    private ProductDAO productDAO; // Thêm DAO cho cơ sở dữ liệu
    private Context context;

    public SwipeToDeleteCallback(Context context, ProductAdapter adapter, ProductDAO productDAO) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.context = context;
        this.adapter = adapter;
        this.productDAO = productDAO;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // Lấy vị trí của item bị vuốt
        int position = viewHolder.getAdapterPosition();

        // Lấy sản phẩm cần xóa từ danh sách
        Product deletedProduct = adapter.getProductAtPosition(position);

        // Xóa sản phẩm từ cơ sở dữ liệu
        productDAO.Delete(deletedProduct.getID());
        // Xóa sản phẩm từ danh sách và cập nhật RecyclerView
        adapter.deleteItem(position);
    }
}