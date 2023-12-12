package edu.lambton.roomify.landlord.view.questionnaire.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.databinding.FragmentStep2DBinding;

public class StepTwoDFragment extends Fragment {

    public interface OnDescriptionChangedListener {
        void onDescriptionChanged(String description);
    }

    private FragmentStep2DBinding binding;
    private OnDescriptionChangedListener onDescriptionChangedListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStep2DBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        EditText descriptionTextView = binding.descriptionTextView;
        TextView textWatcher = binding.textWatcher;

        descriptionTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String description = charSequence.toString();
                int length = description.length();
                textWatcher.setText(length + "/500");
                // Notify the listener about the description change
                notifyDescriptionChanged(description);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return root;
    }

    public void setOnDescriptionChangedListener(OnDescriptionChangedListener listener) {
        this.onDescriptionChangedListener = listener;
    }

    private void notifyDescriptionChanged(String description) {
        if (onDescriptionChangedListener != null) {
            onDescriptionChangedListener.onDescriptionChanged(description);
        }
    }
}
