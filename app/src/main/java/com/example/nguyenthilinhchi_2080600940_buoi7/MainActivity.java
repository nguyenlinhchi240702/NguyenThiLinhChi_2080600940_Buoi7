
package com.example.nguyenthilinhchi_2080600940_buoi7;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.ItemTouchHelper;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.View;

        import com.example.nguyenthilinhchi_2080600940_buoi7.adapter.ProductAdapter;
        import com.example.nguyenthilinhchi_2080600940_buoi7.dao.ProductDAO;
        import com.example.nguyenthilinhchi_2080600940_buoi7.model.Product;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        CustomeAlertDialog customeAlertDialog = new CustomeAlertDialog(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customeAlertDialog.show();
                // Sau khi thêm sản phẩm mới, cập nhật RecyclerView
                customeAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateRecyclerView();
                    }
                });
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo adapter và gán vào RecyclerView
        productAdapter = new ProductAdapter(getProductList());
        recyclerView.setAdapter(productAdapter);
        ProductDAO productDAO = new ProductDAO(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(this, productAdapter, productDAO));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private List<Product> getProductList() {
        // Lấy danh sách sản phẩm từ database hoặc nguồn dữ liệu khác
        ProductDAO productDAO = new ProductDAO(this);
        return productDAO.GetAll();
    }

    private void updateRecyclerView() {
        // Lấy danh sách sản phẩm mới từ database
        List<Product> updatedList = getProductList();

        // Cập nhật dữ liệu trên RecyclerView
        productAdapter.updateData(updatedList);
    }

}