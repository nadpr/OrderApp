package com.nad.order;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int i;
    private int quantity;
    private int price;
    private boolean toppingskacanghijau;
    private boolean toppingsremahmalkist;
    private String topKacangHijau;
    private String topRemahMalkist;
    private boolean kirimEmail;
    private String customerName;
    private EditText nameCust;
    private TextView titleText;
    private TextView quantityTextView;
    private CheckBox checkToppingsKacangIjo;
    private CheckBox checkToppingsRemahMalkist;
    private CheckBox checkKirimEmail;
    private Button checkOutButton;
    private boolean readyCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleText = (TextView) findViewById(R.id.title_pesan);
        checkOutButton = (Button) findViewById(R.id.checkout_btn);
//        checkOutButton.setVisibility(View.GONE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddOrder();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogAddOrder() {
        final Dialog add_new_order = new Dialog(this);
        add_new_order.setContentView(R.layout.dialog_add_order);
        quantity = 0;
        Button btnAddOrder = (Button) add_new_order.findViewById(R.id.button_add);
        Button btnCancelAdd = (Button)
                add_new_order.findViewById(R.id.button_cancel_add);

        nameCust = (EditText) add_new_order.findViewById(R.id.namecust);
        checkToppingsKacangIjo = (CheckBox) add_new_order.findViewById(R.id.checkBoxkacangijo);
        checkToppingsRemahMalkist = (CheckBox) add_new_order.findViewById(R.id.checkBoxremahmalkist);
        quantityTextView = (TextView) add_new_order.findViewById(R.id.quantity_text_view);
        add_new_order.show();
        btnAddOrder.
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customerName = nameCust.getText().toString();
                        if (customerName.equals("")) {
                            Toast.makeText(MainActivity.this, "Monggo isi namamu dulu :)"
                                    , Toast.LENGTH_SHORT).show();
                        } else {
                            submitOrder();
                            add_new_order.dismiss();
                        }
                    }
                });
        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Kami tunggu pesananmu :)"
                        , Toast.LENGTH_SHORT).show();
                add_new_order.dismiss();
            }
        });

    }

    public void submitOrder() {
        toppingskacanghijau = checkToppingsKacangIjo.isChecked();
        toppingsremahmalkist = checkToppingsRemahMalkist.isChecked();
//        kirimEmail = checkKirimEmail.isChecked();

        if (toppingskacanghijau)
            topKacangHijau = "Pake";
        else
            topKacangHijau = "Tidak pake";

        if (toppingsremahmalkist)
            topRemahMalkist = "Pake";
        else
            topRemahMalkist = "Tidak pake";
//        customerName = nameCust.getText().toString();
        price = calculatePrice(quantity, toppingskacanghijau, toppingsremahmalkist);
//        if (customerName.equals("")) {
//        } else {
        String tulisan = createOrderSummary(price, customerName);
        displayMessage(tulisan);
        checkOutButton.setVisibility(View.VISIBLE);

//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HHmmss", Locale.US);
//            String waktu = sdf.format(new Date());
//
//            if (kirimEmail) {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, "burjolegit@gmail.com");
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pesenan Burjo Legit " + waktu);
//                emailIntent.putExtra(Intent.EXTRA_TEXT, tulisan);
//
//                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                finish();
//                Log.i("Finished sending email", "");
//            }
//        }
    }

    private String createOrderSummary(int price, String customerName) {
        String pricemessage = ""; //inisialisasi variable pricemessage
        pricemessage = "Name: Kaptain " + customerName + "\n Quantity : "
                + quantity + "\n Topping Kacang Ijo : " + topKacangHijau + " \n Topping Remah " +
                "Malkist : " + topRemahMalkist + " \n Total : "
                + price + "\n Maturnuwun! :3";
        return pricemessage;
    }

    private int calculatePrice(int quantity, boolean toppingki, boolean toppingrm) {
        int satucup = 5000;
        if (toppingki) {
            satucup = satucup + 1000;
        }
        if (toppingrm) {
            satucup = satucup + 2000;
        }
        price = quantity * satucup;
        return price;
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        }
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    public void checkout(View view) {
        Bundle user_data = new Bundle();
        user_data.putString("name", customerName);
        user_data.putInt("price", price);
        user_data.putInt("quantity", quantity);
        user_data.putBoolean("toppingkacanghijau", toppingskacanghijau);
        user_data.putBoolean("toppingremahmalkist", toppingsremahmalkist);

        Intent checkoutIntent = new Intent(this, HasilActivity.class);
        checkoutIntent.putExtra("bundle", user_data);
        startActivity(checkoutIntent);
    }
}
