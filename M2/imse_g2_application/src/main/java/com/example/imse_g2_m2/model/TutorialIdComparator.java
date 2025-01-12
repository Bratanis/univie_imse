package com.example.imse_g2_m2.model;

import java.util.Comparator;

public class TutorialIdComparator implements Comparator<Tutorial> {
    @Override
    public int compare(Tutorial t1, Tutorial t2) {
        return Integer.compare(t1.getTutorialId(), t2.getTutorialId());
    }
}

