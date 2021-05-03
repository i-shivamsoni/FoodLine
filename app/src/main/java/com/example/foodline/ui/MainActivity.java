package com.example.foodline.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodline.R;
import com.example.foodline.databinding.ActivityMainBinding;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.exception.AppNotFoundException;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.PaymentApp;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public class MainActivity extends AppCompatActivity implements PaymentStatusListener {

    private ActivityMainBinding binding;
    private NavController navController;
    private EasyUpiPayment easyUpiPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_login);
        // initcheckout();
        easyUpiPayment.setPaymentStatusListener((PaymentStatusListener) this);

    }

    public void initcheckout(String PayeeVpa, String PayeeName, String PayeeMerchantCode, String TransactionId, String TransactionRefId, int Amount) throws AppNotFoundException {
        EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
                .setPayeeVpa(PayeeVpa)
                .setPayeeName(PayeeName)
                .setPayeeMerchantCode(PayeeMerchantCode)
                .setTransactionId(TransactionId)
                .setTransactionRefId(TransactionRefId)
                .setDescription("Payment for id" + TransactionId)
                .setAmount(String.valueOf(Amount)).with(PaymentApp.ALL);
        try {
            // Build instance
            easyUpiPayment = builder.build();

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener((PaymentStatusListener) this);

            // Start payment / transaction
            easyUpiPayment.startPayment();
        } catch (Exception exception) {
            exception.printStackTrace();
            toast("Error: " + exception.getMessage());
        }
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // Transaction Completed
        Log.d("TransactionDetails", transactionDetails.toString());

       // statusView.setText(transactionDetails.toString()); link it with textview for showing the status

        switch (transactionDetails.getTransactionStatus()) {
            case SUCCESS:
                onTransactionSuccess();
                break;
            case FAILURE:
                onTransactionFailed();
                break;
            case SUBMITTED:
                onTransactionSubmitted();
                break;
        }
    }

    @Override
    public void onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user");
//        imageView.setImageResource(R.drawable.ic_failed);
    }

    private void onTransactionSuccess() {
        // Payment Success
        toast("Success");
//        imageView.setImageResource(R.drawable.ic_success);
    }

    private void onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted");
//        imageView.setImageResource(R.drawable.ic_success);
    }

    private void onTransactionFailed() {
        // Payment Failed
        toast("Failed");
//        imageView.setImageResource(R.drawable.ic_failed);
    }
    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}