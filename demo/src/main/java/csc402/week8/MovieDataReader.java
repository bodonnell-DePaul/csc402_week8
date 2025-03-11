package csc402.week8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MovieDataReader {
    public List<Movie> readMoviesFromCSV(String filePath) {
        List<Movie> movies = new ArrayList<>();
        String[] values = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for(int i = 0; i < lines.size(); i++) {
                if (i == 0) continue; // Skip header line
                if(lines.get(i).contains("\""))
                {
                    String [] temp = lines.get(i).split(",");   
                    String[] actualData = new String[5];

                    int ctr = 0;
                    for(int j = 0; j < temp.length; j++){
                        if(temp[j].contains("\""))
                        {
                            if(j+1 > temp.length)
                            {
                                break;
                            }

                            String firstPart = temp[j].replace("\"","" );
                            String secondPart = temp[j+1].replace("\"","" );
                            String combined = firstPart + "," + secondPart;
                            actualData[ctr] = combined;
                            ctr++;
                            j++;
                        }
                        else
                        {
                            actualData[ctr] = temp[j];
                            ctr++;
                        }
                    }
                    values = actualData;
                }
                else{
                    values = lines.get(i).split(",");
                }
                

                Movie movie = new Movie();
                movie.setRank(Integer.parseInt(values[0]));
                movie.setTitle(values[1]);
                movie.setDirector(values[2]);
                try{
                    movie.setImdbRating(Double.parseDouble(values[3]));}
                catch (NumberFormatException e){
                    movie.setImdbRating(Double.parseDouble(values[3].split(",")[1]));
                }
                
            

                movie.setYear(Integer.parseInt(values[4]));
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> readMoviesFromExcel(String filePath) {
        List<Movie> movies = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unknown Cell Type\t");
                            break;
                    }
                }
                System.out.println();
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