package com.zubayear.steamsexp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Movie {
    private String name;
    private int releaseYear;
    private double rating;
    private List<String> genres;

    public Movie() {
    }

    public Movie(String name, int releaseYear, double rating, List<String> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                ", genres=" + genres +
                '}';
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
public class MasterStream {
    public static void main(String[] args) {
        // streamOps();
        // ex2(Arrays.asList("Spring", "Spring Boot", "API", "Microservices", "AWS", "Azure", "Docker"));
//        sorting(Arrays.asList("Spring", "Spring Boot", "API", "Microservices", "AWS", "Azure", "Docker"));
        List<Movie> movies = List.of(
                new Movie("The Batman", 2022, 7.8, Arrays.asList("Action", "Crime", "Drama", "Thriller")),
                new Movie("The Flash", 2023, 7, Arrays.asList("Action", "Adventure", "Fantasy", "Sci-Fi")),
                new Movie("Spider-Man: Across the Spider-Verse", 2023, 8.9, Arrays.asList("Action", "Adventure", "Animation", "Fantasy", "Sci-Fi", "Family"))
        );
        streamExp(movies);
    }

    static void streamOps() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer result = numbers.stream()
                .reduce(0, Integer::rotateLeft);
        System.out.println(result);
    }

    static void ex2(List<String> courses) {
        courses.stream()
                .filter(s -> s.contains("Spring"))
                .forEach(System.out::println);
    }

    static void sorting(List<String> course) {
        course.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .forEach(System.out::println);
    }

    static void streamExp(List<Movie> movies) {
        /*
        boolean b = movies.stream()
                .noneMatch(movie -> movie.getRating() > 5);
        System.out.println(b);
        */

        /*movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed().thenComparing(Movie::getName))
                .collect(Collectors.groupingBy(Movie::getReleaseYear, Collectors.mapping(Movie::getName, Collectors.toList())))
                .forEach((integer, movies1) -> System.out.println(integer + " => " + movies1));*/
        List<String> collect = movies.stream()
                .map(Movie::getName)
                .map(m -> m.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);
    }

}
