package csc402.week8;

public class Movie implements Comparable<Movie> {
    private int rank;
    private String title;
    private String director;
    private double imdbRating;
    private int year;

    // Getters and setters
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Movie o) {
        // TODO Auto-generated method stub
        // if(this.getImdbRating() < o.getImdbRating())
        // {
        //     return 1;
        // }
        // else if(this.getImdbRating() > o.getImdbRating())
        // {
        //     return -1;
        // }
        // else
        // {
        //     return 0;
        // }

        //another comparator for get rank
        if(this.getRank() > o.getRank())
        {
            return 1;
        }
        else if(this.getRank() < o.getRank())
        {
            return -1;
        }
        else
        {
            return 0;
        }

        // another comparator for get year
        // if(this.getYear() > o.getYear())
        // {
        //     return 1;
        // }
        // else if(this.getYear() < o.getYear())
        // {
        //     return -1;
        // }
        // else
        // {
        //     return 0;
        // }
    }
}