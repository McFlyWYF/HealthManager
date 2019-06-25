package com.eric.cookbook.ui.menufind.fragment.findhistory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.eric.cookbook.R;
import com.eric.cookbook.adapter.SearchHistoryAdapter;
import com.eric.cookbook.bean.SearchHistoryBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/10.
 */

public class FindHistoryFragment extends Fragment implements FindHistoryContract.IFindHistoryView{

    private FindHistoryPresenter findHistoryPresenter;
    private SearchHistoryAdapter searchHistoryAdapter;
    private ArrayList<SearchHistoryBean> histories = new ArrayList<>();
    @Bind(R.id.listView_history)
    ListView listViewHistory;
    @Bind(R.id.ll_search_empty)
    LinearLayout llSearchEmpty;
    @Bind(R.id.ll_search_history)
    LinearLayout llSearchHistory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            searchCallback = (OnSearchCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnSearchCallback");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_history, container,  false);
        ButterKnife.bind(this, view);
        findHistoryPresenter = new FindHistoryPresenter(this, getContext());
        searchHistoryAdapter = new SearchHistoryAdapter(getContext(), histories);
        searchHistoryAdapter.setOnSearchHistoryListener(new SearchHistoryAdapter.OnSearchHistoryListener() {
            @Override
            public void onDelete(String key) {
                findHistoryPresenter.removeHistory(key);
            }

            @Override
            public void onSelect(String content) {
                searchCallback.sendSearchContent(content);
            }
        });
        listViewHistory.setAdapter(searchHistoryAdapter);
        findHistoryPresenter.sortHistory();
        return view ;
    }

    @Override
    public void showHistories(ArrayList<SearchHistoryBean> results) {
        llSearchEmpty.setVisibility(0 != results.size() ? View.VISIBLE : View.GONE);
        searchHistoryAdapter.refreshData(results);
    }

    @OnClick(R.id.ll_search_empty)
    public void onClick(){
        findHistoryPresenter.clearHistory();
    }

    public interface OnSearchCallback{
        void sendSearchContent(String content);
    }
    private OnSearchCallback searchCallback;
}
