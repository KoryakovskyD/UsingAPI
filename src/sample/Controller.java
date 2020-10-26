package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button getData;

    @FXML
    private TextField city;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_pressure;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            String output = getUrlContent("https://history.openweathermap.org/data/2.5/aggregated/year?q=" + getUserCity + "&appid=96d550f17e46bffee6d2bd2eb98dab68");
            System.out.println("Все работает!");
        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такого города нет!");
        }
        return content.toString();
    }
}
