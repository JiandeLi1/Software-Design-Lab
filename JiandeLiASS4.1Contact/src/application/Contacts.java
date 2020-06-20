package application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;





public class Contacts {
	private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final IntegerProperty number = new SimpleIntegerProperty();
    private Image image;
    private File imageFile;
	
	
public Contacts(String l, String f, String e, int n) {
	   setLastName(l);
       setFirstName(f);
        setEmail(e);
		setNumber(n);
		image= new Image("/image/one.png");
		
	}
	
	
public Contacts(String l, String f, String e, int n, Image im){
	    setLastName(f);
        setFirstName(l);
        setEmail(e);
		setNumber(n);
		setImage(im);
		}
 
	
public final StringProperty FirstNameProperty(){
	return this.firstname;
	}
public final StringProperty LastNameProperty(){
	return this.lastname;
	}
public final StringProperty EmailProperty(){
	return this.email;
	}
public final IntegerProperty NumberProperty(){
	return this.number;
	}

public final java.lang.String getFirstName(){ 
	return this.FirstNameProperty().get();
	}
public final java.lang.String getLastName(){ 
	return this.LastNameProperty().get();
	}
public final java.lang.String getEmail(){ 
	return this.EmailProperty().get();
	}
public final java.lang.Integer getNumber(){ 
	return this.NumberProperty().get();
	}

public final void setFirstName(final java.lang.String first){
    this.FirstNameProperty().set(first);
}
public final void setLastName(java.lang.String last){
	this.LastNameProperty().set(last);
	}
public final void setEmail(java.lang.String Email){
    this.EmailProperty().set(Email);
}
public final void setNumber(java.lang.Integer Number){
    this.NumberProperty().set(Number);
}

	public void setImage(Image im) {
		this.image=im;
	}

	public Image getImage() {
		return image;
	}

	 public void copyImageFile() throws IOException, SQLException
	    {
	        
	       
			java.nio.file.Path ThePath = imageFile.toPath();
	        String FileName = (imageFile.getName());
	        java.nio.file.Path targetPath = Paths.get("./src/image/"+FileName);
	        Files.copy(ThePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
	        
	        imageFile = new File(targetPath.toString());
	    }


	public File getImageFile() {
		return imageFile;
	}


	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}


	

	
	   }
	
