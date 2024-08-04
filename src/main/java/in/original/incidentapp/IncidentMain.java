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
        // データの作成
        List<IncidentData> dataList = incidentRepository.findAll();
        System.out.println("データリスト: " + dataList);

        // 円グラフの作成
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (IncidentData data : dataList) {
            pieChartData.add(new PieChart.Data(data.getCategory2(), data.getCount()));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("インシデントデータの集計");

        // レイアウトの作成
        VBox vbox = new VBox(pieChart);

        // シーンの作成
        Scene scene = new Scene(vbox, 800, 600);

        // ステージの設定
        primaryStage.setScene(scene);
        primaryStage.setTitle("インシデントデータの集計");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}