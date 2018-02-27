package com.udacity.popular_movies;

import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String url = "https://api.themoviedb.org/3/movie/popular?api_key="+ BuildConfig.TMDB_API_KEY;
    private MovieDb movie;


    @Test
    public void addition_isCor2rect23() throws Exception {
        Integer pageCount = 0 / 20;
         pageCount = 2 / 20;
         pageCount = 20 / 20;
         pageCount = 21 / 20;
         pageCount = 40 / 20;
    }


//    @Test
//    public void addition_isCorrect() throws Exception {
//        URL ob = new URL(url);
//        String asdf = NetworkUtils.getResponseFromHttpUrl(ob);
//        System.out.println(asdf);
//        assertEquals(4, 2 + 2);
//    }

    @Test
    public void addition_isCorrect23() throws Exception {
        TmdbMovies movies = new TmdbApi(BuildConfig.TMDB_API_KEY).getMovies();
        MovieResultsPage page = movies.getPopularMovies("en", 1);
        MovieDb movie1 =  page.getResults().get(0);
        String path = movie1.getPosterPath();

        MovieDb movie = movies.getMovie(5353, "en");
        MovieImages images = movies.getImages(5353, "en");
        Artwork art2 = images.getPosters().get(0);
        Artwork art = movie1.getImages(ArtworkType.POSTER).get(0);

        MovieDb movie2 =  page.getResults().get(1);
        MovieResultsPage page2 = movies.getTopRatedMovies("en", 1);
    }
}