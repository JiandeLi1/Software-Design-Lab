package application;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.embed.swing.SwingFXUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import application.Contacts;
import javafx.util.converter.IntegerStringConverter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import application.Main;

public class Controller implements Initializable{
	@FXML
	private Label lastn;
	@FXML
	private Label firstn;
	@FXML
	private Label emailn;
	@FXML
	private Label numn;
	@FXML
	private ImageView image;

	private Contacts theperson;
	

	private FileChooser fileChooser;
	private File filepath;
	@FXML
	public TableView<Contacts> tableview;
    @FXML
    public ListView<String> listview;
    @FXML 
    private TableColumn<Contacts, String> firstNameColumn;
    @FXML 
    private TableColumn<Contacts, String> lastNameColumn;
    @FXML 
    private TableColumn<Contacts, String> emailColumn;
    @FXML 
    private TableColumn<Contacts, Integer> numColumn;
    
    ObservableList<Contacts> ListItems = FXCollections.observableArrayList(contact-> new Observable[]{
    	       contact.FirstNameProperty(),
    	       contact.LastNameProperty(),
    	       contact.EmailProperty(),
    	       contact.NumberProperty(),
    	    });
     

    ObservableList<String> NewOne = FXCollections.observableArrayList();
    


  
   @FXML
   private TextField last;
   @FXML
   private TextField first;
   @FXML
   private TextField email;
   @FXML
   private TextField num;
   @FXML
   private int n;
  
   @FXML
   public void clearFields() {
	   last.setText(null);
	   first.setText(null);
	   email.setText(null);
	   num.setText(null);
   }

   
   public void chooseImage(ActionEvent event) throws SQLException {
	   Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	   fileChooser=new FileChooser();
	   fileChooser.setTitle("Open image");
	   this.filepath=fileChooser.showOpenDialog(stage);
	   try {
	  	 BufferedImage bufferedImage=ImageIO.read(filepath);
	  	 Image image1=SwingFXUtils.toFXImage(bufferedImage, null);
	  	 theperson.setImageFile(filepath);
	  	 theperson.copyImageFile();
	  	 theperson.setImage(image1);
	  	 image.setImage(theperson.getImage());
	   }catch(IOException e)
	   {
	  	 System.err.println(e.getMessage());
	   }
	   }
   
   public void selectionContact(){
   	theperson = tableview.getSelectionModel().getSelectedItem();
   	lastn.setText(theperson.getLastName());
   	firstn.setText(theperson.getFirstName());
   	emailn.setText(theperson.getEmail());
   	numn.setText(theperson.getNumber().toString());
   	image.setImage(theperson.getImage());
}


   
   
   @FXML
   public void AddAction(ActionEvent event) {
	   n= Integer.valueOf(num.getText());
       Contacts person = new Contacts(last.getText(),first.getText(),email.getText(),n);
       tableview.getItems().add(person);
       clearFields();
       JOptionPane.showMessageDialog(null,"Adding person in Contact is successful!");
	 }
   
   @FXML
   private void DeletePerson(ActionEvent event) {
	   ObservableList<Contacts> selectedRows, allPeople;
       allPeople = tableview.getItems();
       selectedRows = tableview.getSelectionModel().getSelectedItems();
       JOptionPane.showMessageDialog(null,"Deleting person in Contact is successful!");
       for (Contacts person: selectedRows)
       {
           allPeople.remove(person);
       }
       
       
       }

   @FXML
   private void changeFirstName(CellEditEvent edditedCell){
       Contacts contactSelected = tableview.getSelectionModel().getSelectedItem();
       contactSelected.setFirstName(edditedCell.getNewValue().toString());
   }
   @FXML
   private void changeLastName(CellEditEvent edittedCell){
       Contacts contactSelected = tableview.getSelectionModel().getSelectedItem();
       contactSelected.setLastName(edittedCell.getNewValue().toString());
   }
   @FXML
   private void changeEmail(CellEditEvent edditedCell){
       Contacts contactSelected = tableview.getSelectionModel().getSelectedItem();
       contactSelected.setEmail(edditedCell.getNewValue().toString());
   }
   
   @FXML
   private void changeNumber(CellEditEvent edditedCell){
       Contacts contactSelected = tableview.getSelectionModel().getSelectedItem();
       contactSelected.setNumber((int)(edditedCell.getNewValue()));
   }

@Override
public void initialize(URL url, ResourceBundle rb) {
	firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts,String>("firstName"));
	lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts,String>("lastName"));
	emailColumn.setCellValueFactory(new PropertyValueFactory<Contacts,String>("email"));
    numColumn.setCellValueFactory(new PropertyValueFactory<Contacts,Integer>("number"));
    ListItems=getPeople();
    tableview.setItems(ListItems);
    tableview.setEditable(true);
    firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    numColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
 
    ListItems.addListener((Change<? extends Contacts> newm)->{
        while(newm.next()){
            if(newm.wasAdded()){
                NewOne.add(last.getText()+" was added");
                listview.setItems(NewOne);
            }
            if(newm.wasRemoved()){
                NewOne.add("The person you choose was removed");
                listview.setItems(NewOne);
            }
        }
    });
    }

public ObservableList<Contacts>  getPeople()
{
    ObservableList<Contacts> people = FXCollections.observableArrayList();
    people.add(new Contacts("Li", "Jiande", "jli030@citymail.cuny.edu", 123456789));
    people.add(new Contacts("Cool", "Guy", "cy112@citymail.cuny.edu", 123456789,new Image("/image/one.png")));
   
    return people;
}


}
	






