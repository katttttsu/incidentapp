package in.original.incidentapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class IncidentMain extends Application {

    private IncidentRepository incidentRepository;

    @Override
    public void init() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        incidentRepository = context.getBean(IncidentRepository.class);
    }

    @Override
    public void start(Stage primaryStage) {
        List<IncidentData> dataList = incidentRepository.findAll();
        System.out.println("データリスト: " + dataList);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (IncidentData data : dataList) {
            pieChartData.add(new PieChart.Data(data.getSmallCategory(), data.getCount()));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("インシデントデータの集計");

        VBox vbox = new VBox(pieChart);

        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("インシデントデータの集計");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}