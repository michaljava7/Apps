package quiz.quiz_game.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadQuestionService {

    public List<String[]> read() {
        List<String[]> listOfTables = new ArrayList<>();
        String input = null;
        String[] splitTable = null;

        try (FileReader fileReader = new FileReader("question.txt");
             BufferedReader bf = new BufferedReader(fileReader)
        ) {
            while ((input = bf.readLine()) != null) {
                if (!input.trim().isEmpty()) {
                    splitTable = input.trim().split(",");
                    listOfTables.add(splitTable);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfTables;
    }
}
