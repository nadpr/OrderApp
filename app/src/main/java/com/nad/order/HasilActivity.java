package com.nad.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    private String nameCust;
    private int price;
    private int quantity;
    private boolean toppingKacangHijau;
    private boolean toppingRemahMalkist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        Bundle user_data = getIntent().getBundleExtra("bundle");
        nameCust = user_data.getString("name", "Joko");
        price = user_data.getInt("price", 0);
        quantity = user_data.getInt("quantity", 0);
        toppingKacangHijau = user_data.getBoolean("toppingkacanghijau", false);
        toppingRemahMalkist = user_data.getBoolean("toppingremahmalkist", false);
        TextView custNameTextView = (TextView) findViewById(R.id.hai);
        TextView priceTextView = (TextView)findViewById(R.id.price_idr);

        custNameTextView.setText("Hai Kaptain, " + nameCust);
        priceTextView.setText("IDR " + price);
    }

    public void buatHitung() {

    }
}
