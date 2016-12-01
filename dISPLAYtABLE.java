import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.sql.*;
import javafx.stage.Stage;
 
public class dISPLAYtABLE extends Application {
	
	private Connection connect;
	private ResultSet rest;
	private PreparedStatement pest;
	String srequest = "SELET * FROM ";
	private Statement stmt;
    private TableView<RegRec> table = new TableView<RegRec>();

 
 //   private TableView<RegRec> table = new TableView<RegRec>();
    private final ObservableList<RegRec> data =
        FXCollections.observableArrayList();
  
    
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Display Exercise35_03");
        stage.setWidth(450);
        stage.setHeight(500);
 
        final Label label = new Label("Table Name:"); 
        final TextField tfdbName = new TextField();
        Button btShow = new Button("Show Contents");
        table.setEditable(false);
        HBox hb = new HBox();
        hb.getChildren().addAll(label, tfdbName, btShow);
        VBox vb = new VBox(2);
        vb.getChildren().addAll(hb, table);
 
        TableColumn ssnCol = new TableColumn("ssn");
        ssnCol.setMinWidth(100);
        ssnCol.setCellValueFactory(
                new PropertyValueFactory<RegRec, String>("ssn"));
 
        TableColumn courseidCol = new TableColumn("courseid");
        courseidCol.setMinWidth(100);
        courseidCol.setCellValueFactory(
                new PropertyValueFactory<RegRec, String>("courseid"));
 
        TableColumn dregCol = new TableColumn("dateregistered");
        dregCol.setMinWidth(200);
        dregCol.setCellValueFactory(
                new PropertyValueFactory<RegRec, String>("dateregistered"));
        
        TableColumn gradeCol = new TableColumn("grade");
        gradeCol.setMinWidth(100);
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<RegRec, String>("grade"));

 
        table.setItems(data);
        table.getColumns().addAll(ssnCol, courseidCol, dregCol, gradeCol);
 
       
 
        ((Group) scene.getRoot()).getChildren().addAll(vb);
 
        stage.setScene(scene);
        stage.show();
        btShow.setOnAction(e->
        {
        	int i=1;
        	
        	try {
				 rest = stmt.executeQuery(srequest + "'?'");

        	while (rest.absolute(i))
			{
				rest.next();
				pest.setString(1,  tfdbName.getText());
				rest = pest.executeQuery();
        	
				data.add(new RegRec("" + rest.getString("ssn") + "",
						rest.getString("courseid"),
						rest.getString("dateRegistered"),
						rest.getString("grade") 
			
   ));		}	i++;
        } catch (SQLException es)
        {
        	System.out.println(es);
        } finally {
        	
        }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void connectDB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			}catch (ClassNotFoundException e)
			{ System.out.println("Database cannot open");
			}
			try {
				connect = DriverManager.getConnection
							("jdbc:mysql://localhost/login?user=root&password=");
				System.out.println("Connection established");
				 stmt = connect.createStatement();
			
			} catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
			
	}
 
    public class RegRec {
 
        private final SimpleStringProperty ssId;
        private final SimpleStringProperty courseId;
        private final SimpleStringProperty dateReg;
        private final SimpleStringProperty courseGrade;

 
        private RegRec(String socid, String crsid, String regdate, String crsgrade) {
            this.ssId = new SimpleStringProperty(socid);
            this.courseId = new SimpleStringProperty(crsid);
            this.dateReg = new SimpleStringProperty(regdate);
            this.courseGrade = new SimpleStringProperty(crsgrade);

        }
 
        public String getCourseId() {
            return courseId.get();
        }
 
        public void setFirstName(String csId) {
            courseId.set(csId);
        }
 
        public String getSSN() {
            return ssId.get();
        }
 
        public void setSSN(String ssN) {
            ssId.set(ssN);
        }
 
        public String getDateReg() {
            return dateReg.get();
        }
 
        public void setDateReg(String datereG) {
            dateReg.set(datereG);
        }

            public String getCourseGrade() {
                return courseGrade.get();
            }
     
            public void setCourseGrade(String datereG) {
                dateReg.set(datereG);
        }
    }
}