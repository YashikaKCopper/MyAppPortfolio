package com.udacity.yashika.myappportfolio.popular_movies.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.yashika.myappportfolio.R;
import com.udacity.yashika.myappportfolio.popular_movies.model.Movie;
import com.udacity.yashika.myappportfolio.popular_movies.model.MovieResponse;

import java.util.List;

/**
 * An activity representing a list of Movies. This activity has different presentations for handset
 * and tablet-size devices. On handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing item details. On tablets, the activity
 * presents the list of items and item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends Activity {

    private static final String TAG = MovieListActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
     */
    private boolean mTwoPane;
    private MovieResponse movieResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        movieResponse = (MovieResponse) bundle.getSerializable(TAG);

        View recyclerView = findViewById(R.id.movie_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if(findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        if(movieResponse != null) {
            if(movieResponse.getMovies() != null)
                recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(movieResponse.getMovies()));
        }
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Movie> movies;

        public SimpleItemRecyclerViewAdapter(List<Movie> movies) {
            this.movies = movies;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.movie = movies.get(position);
            holder.titleText.setText(movies.get(position).getMovieTitle());
            holder.contentText.setText(movies.get(position).getMovieOverview());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putString(MovieDetailFragment.ARG_ITEM_ID, holder.movie.id);
//                        MovieDetailFragment fragment = new MovieDetailFragment();
//                        fragment.setArguments(arguments);
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.movie_detail_container, fragment)
//                                .commit();
                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, MovieDetailActivity.class);
//                        intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, holder.movie.id);
//
//                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView titleText;
            public final TextView contentText;
            public Movie movie;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                titleText = (TextView) view.findViewById(R.id.id);
                contentText = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + contentText.getText() + "'";
            }
        }
    }
}