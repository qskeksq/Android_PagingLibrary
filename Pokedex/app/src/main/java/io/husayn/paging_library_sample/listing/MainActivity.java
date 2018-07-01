package io.husayn.paging_library_sample.listing;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.husayn.paging_library_sample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. LiveData<PagedList<T>> 를 가지고 있음
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // 2. PagedListAdapter 자체
        final PokemonAdapter adapter = new PokemonAdapter();

        // 3. PagedList를 PagedListAdapter가 Observe 하게 등록해줌
        viewModel.pokemonList.observe(this, adapter::setList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.span_count)));
        recyclerView.setAdapter(adapter);
    }

    /**
     * RecyclerView <- PagedListAdapter <- PagedList
     */

}
