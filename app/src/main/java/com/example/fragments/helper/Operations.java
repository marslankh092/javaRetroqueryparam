package com.example.fragments.helper;

import com.example.fragments.server.Search;

public interface Operations {

    void detailBtn(Search search);

    void deleteBtn(Search search);

    void updateBtn(Search search, int position);

}
