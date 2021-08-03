package com.sci.bpm.dao.marketing;

import com.sci.bpm.db.model.SciEnquiryDetails;

import java.util.Comparator;

public class DateSortComparator implements Comparator<SciEnquiryDetails> {
    @Override
    public int compare(SciEnquiryDetails o1, SciEnquiryDetails o2) {
        return o2.getInsertedDate().compareTo(o1.getInsertedDate());
    }
}
