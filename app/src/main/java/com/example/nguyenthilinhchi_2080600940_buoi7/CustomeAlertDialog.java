package com.example.nguyenthilinhchi_2080600940_buoi7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nguyenthilinhchi_2080600940_buoi7.dao.ProductDAO;
import com.example.nguyenthilinhchi_2080600940_buoi7.model.Product;

public class CustomeAlertDialog extends Dialog {
    private ProductDAO productDAO;
    EditText edtID, edtName, edtImage, edtPrice;
    Button btnSave, btnClear;
    public CustomeAlertDialog(@NonNull Context context) {
        super(context);
        productDAO= new ProductDAO(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_product);
        edtID = findViewById(R.id.edtProductID);
        edtName= findViewById(R.id.edtProductName);
        edtPrice= findViewById(R.id.edtProductPrice);
        edtImage= findViewById(R.id.edtProductImage);
        btnClear= findViewById(R.id.btnClear);
        btnSave= findViewById(R.id.btnSave);
        //xử lý button clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaThongTin();
            }
        });
        //xử lý button Save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nếu người dùng đã nhập đủ thông tin --> lưu thông tin + thông báo thành công + xóa thông tin trong view
                if (isDataValid()){
                    saveData();
                    Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    xoaThongTin();
                }
                //ngược lại thì thông báo người dùng nhập đủ thông tin
                else {
                    Toast.makeText(getContext(), "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void xoaThongTin(){
        edtID.setText("");
        edtImage.setText("");
        edtPrice.setText("");
        edtName.setText("");
    }
    //hàm lưu thông tin xuống SQLite
    private void saveData(){
        String ID= edtID.getText().toString().trim();
        String Name= edtName.getText().toString().trim();
        String Image= "@drawable/"+edtImage.getText().toString().trim();
        String Price= edtPrice.getText().toString().trim();
        Product product = new Product(Integer.parseInt(ID), Name, Image, Float.parseFloat(Price));
        productDAO.Insert(product);
    }
    //hàm kiểm tra xem người dùng đã nhập đủ thông tin chưa
    private boolean isDataValid(){
        String ID= edtID.getText().toString().trim();
        String Name= edtName.getText().toString().trim();
        String Image= edtImage.getText().toString().trim();
        String Price= edtPrice.getText().toString().trim();
        return  !ID.isEmpty() && !Name.isEmpty() && !Image.isEmpty() && !Price.isEmpty();
    }
}
