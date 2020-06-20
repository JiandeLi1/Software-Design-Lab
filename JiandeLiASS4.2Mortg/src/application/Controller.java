package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	@FXML
	private TextField price;
	@FXML
	private TextField payment;
	@FXML
	private TextField rate;
	@FXML
	private Label loanm;
	@FXML
	private Label mp;
	@FXML
	private Label yrs;
	@FXML
	private Slider years;
	@FXML
	private Button cal;
	@FXML
	private Float p;
	@FXML
	private Float pm;
	@FXML
	private Float r;
	@FXML
	private String l;
	@FXML
	private Float lo;
	@FXML
	private Float lo1;
	@FXML
	private Float lo2;
	@FXML
	private Float lo3;
	@FXML
	private String mps;
	@FXML
	private String mps2;
	@FXML
	private String mps3;
	@FXML
	private String mps4;
	@FXML
	private Label mp1;
	@FXML
	private Label mp2;
	@FXML
	private Label mp3;
	@FXML
	private Float ri;

	
	
	@FXML
	private void handleButton(ActionEvent enent) {
		yrs.setText(String.valueOf((int)years.getValue()));
	}
	@FXML
	private void Calculate(ActionEvent enent) {
		yrs.setText(String.valueOf((int)years.getValue()));
		p= Float.valueOf(price.getText());
		pm=Float.valueOf(payment.getText());
		r=Float.valueOf(price.getText());
		l=String.valueOf(p-pm);
		loanm.setText(l);
		lo=(p-pm);
		lo1=(p-pm);
		lo2=(p-pm);
		lo3=(p-pm);
		ri=r/100;
		
		
		mps=monthlypay(lo,ri,(int)years.getValue());
		mp.setText(mps);
		
		mps2=monthlypay(lo,ri,10);
		mp1.setText(mps2);
		
		mps3=monthlypay(lo,ri,20);
		mp2.setText(mps3);
		
		mps4=monthlypay(lo,ri,30);
		mp3.setText(mps4);
		
	
		
	}
	
	public String monthlypay(Float lp, Float rl, int yl ) {
		String mm;
		double mmpp;
		Float rly=rl/12;
		
			mmpp=(lp*rly)/(1-Math.pow(1+rly,-yl));
		
		
		mm=String.valueOf(mmpp/(yl*12));
		
		
		return mm;
		
	}
	
	
	 @FXML
	 public void clear() {
		   price.setText(null);
		   payment.setText(null);
		   rate.setText(null);
	   }
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
