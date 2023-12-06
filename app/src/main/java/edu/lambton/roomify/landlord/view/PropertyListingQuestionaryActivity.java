package edu.lambton.roomify.landlord.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityPropertyListingQuestionaryBinding;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.QuestionnairePagerAdapter;

public class PropertyListingQuestionaryActivity extends AppCompatActivity {

    private ActivityPropertyListingQuestionaryBinding binding;
    private ViewPager viewPager;
    private QuestionnairePagerAdapter pagerAdapter;
    private Button btnNext, btnPrevious;

    private List<Fragment> questionnaireFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPropertyListingQuestionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = binding.viewPager;
        btnNext = binding.btnNext;
        btnPrevious = binding.btnPrevious;

        // List of Fragments for Questionnaire
        questionnaireFragments = new ArrayList<>();
        questionnaireFragments.add(new ListingOneAFragment());
        questionnaireFragments.add(new ListingOneCFragment());

        pagerAdapter = new QuestionnairePagerAdapter(getSupportFragmentManager(), questionnaireFragments);
        viewPager.setAdapter(pagerAdapter);

        // Set initial button visibility
        updateButtonVisibility();

        btnNext.setOnClickListener(this::nextQuestion);

        btnPrevious.setOnClickListener(this::previousQuestion);

    }

    private void previousQuestion(View view) {
        if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            updateButtonVisibility();
        }
    }

    private void nextQuestion(View view) {
        if (viewPager.getCurrentItem() < questionnaireFragments.size() - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            updateButtonVisibility();
        }
    }

    private void updateButtonVisibility() {
        if (viewPager.getCurrentItem() == 0) {
            btnPrevious.setVisibility(View.INVISIBLE);
        } else {
            btnPrevious.setVisibility(View.VISIBLE);
        }

        if (viewPager.getCurrentItem() == pagerAdapter.getCount() - 1) {
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
        }
    }
}