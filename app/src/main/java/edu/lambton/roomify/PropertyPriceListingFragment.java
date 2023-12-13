package edu.lambton.roomify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edu.lambton.roomify.databinding.FragmentPropertyPriceListingBinding;

public class PropertyPriceListingFragment extends Fragment {
    public interface OnPriceChangedListener {
        void onPriceChanged(double price);
    }

    private FragmentPropertyPriceListingBinding binding;
    private OnPriceChangedListener onPriceChangedListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPropertyPriceListingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText priceInputEditText = binding.priceInputEditText;

        priceInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                try {
                    double price = Double.parseDouble(charSequence.toString());
                    // Notify the listener about the price change
                    notifyPriceChanged(price);
                } catch (NumberFormatException e) {
                    // Handle the case where the input is not a valid double
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return root;
    }

    public void setOnPriceChangedListener(OnPriceChangedListener listener) {
        this.onPriceChangedListener = listener;
    }

    private void notifyPriceChanged(double price) {
        if (onPriceChangedListener != null) {
            onPriceChangedListener.onPriceChanged(price);
        }
    }
}