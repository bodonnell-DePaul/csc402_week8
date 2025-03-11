package csc402.week8;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MovieDataReader reader = new MovieDataReader();
        List<Movie> movies = reader.readMoviesFromCSV("C:\\Users\\bodonne3\\OneDrive - DePaul University\\Documents\\CSC402\\csc402_week8\\demo\\moviedata.csv");
        LinkedList<Movie> linkedList = new LinkedList<>(movies);

        //movies = mergeSort(movies);
        //movies = insertionSort(movies);
        movies = selectionSort(movies);

        //First in First Out
        // Queue<Movie> queue = new Queue<Movie>();
        // for (Movie movie : movies) {
        //     queue.add(movie);
        // }

        // //First in Last Out
        // Stack<Movie> stack = new Stack<Movie>();
        // for (Movie movie : movies) {
        //     stack.push(movie);
        // }

        PriorityQueue<Movie> priorityQueue = new PriorityQueue<Movie>();
        for (Movie movie : movies) {
            priorityQueue.add(movie);
        }

        Movie m = priorityQueue.poll();

        reader.serializeMoviesToJson(movies, "C:\\\\Users\\\\bodonne3\\\\OneDrive - DePaul University\\\\Documents\\\\CSC402\\\\csc402_week8\\\\demo\\\\moviedata.json");
        System.out.println("Movies data has been serialized to JSON.");
    }

    public static List<Movie> mergeSort(List<Movie> movies) {
        if (movies.size() <= 1) {
            return movies;
        }

        int mid = movies.size() / 2;
        List<Movie> left = new ArrayList<>(movies.subList(0, mid));
        List<Movie> right = new ArrayList<>(movies.subList(mid, movies.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static List<Movie> merge(List<Movie> left, List<Movie> right) {
        List<Movie> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged;
    }

    public static List<Movie> insertionSort(List<Movie> movies) {
        for (int i = 1; i < movies.size(); i++) {
            Movie key = movies.get(i);
            int j = i - 1;

            while (j >= 0 && movies.get(j).compareTo(key) > 0) {
                movies.set(j + 1, movies.get(j));
                j--;
            }
            movies.set(j + 1, key);
        }
        return movies;
    }

    public static List<Movie> selectionSort(List<Movie> movies) {
        for (int i = 0; i < movies.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(j).compareTo(movies.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Movie temp = movies.get(minIndex);
            movies.set(minIndex, movies.get(i));
            movies.set(i, temp);
        }
        return movies;
    }
}
