package csc402.week8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MovieDataReader {
    public List<Movie> readMoviesFromCSV(String filePath) {
        List<Movie> movies = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] values = line.split(",");
                Movie movie = new Movie();
                movie.setRank(Integer.parseInt(values[0]));
                movie.setTitle(values[1]);
                movie.setDirector(values[2]);
                movie.setImdbRating(Double.parseDouble(values[3]));
                movie.setYear(Integer.parseInt(values[4]));
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void serializeMoviesToJson(List<Movie> movies, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(filePath), movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}