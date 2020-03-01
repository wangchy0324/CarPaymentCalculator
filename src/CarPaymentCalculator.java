import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
public class CarPaymentCalculator{
	public JFrame mainFrame;
	public JLabel askCreditLabel=new JLabel();
	public JLabel showScoreLabel=new JLabel();
	public JLabel carPrice=new JLabel();
	public JTextField carPriceText=new JTextField();
	public JLabel afterNegotiations=new JLabel();
	public JLabel tradeIn=new JLabel();
	public JTextField tradeInText=new JTextField();
	public JLabel tradeInHint=new JLabel();
	public JLabel interestRate=new JLabel();
	public JTextField interestText=new JTextField();
	public JLabel interestHint=new JLabel();
	public JLabel monthNumber=new JLabel();
	public JTextField monthNumberText=new JTextField();
	public JLabel monthNumberHint=new JLabel();
	public JLabel paymentDetails=new JLabel();
	public JLabel monthlyPayment=new JLabel();
	public JLabel monthlyPaymentPrice=new JLabel();
	public JLabel beforePrice=new JLabel();
	public JLabel totalAmountPaid=new JLabel();
	public JLabel totalAmountPrice=new JLabel();
	public JLabel overTheLife=new JLabel();
	public JLabel totalInterestPaid=new JLabel();
	public JLabel totalInterestPrice=new JLabel();
	private double amount=0.0;
	private double rate = 0.0;
	private int numPeriods = 0;
	private double tradein=0.0;
	private double payment = 0.0;
	private double totalamountpaid=0.0;
	private double totalinterestpaid=0.0;
	public void createCarPaymentGUI(){
		mainFrame = new JFrame("Car Payment Calculator");
		String[] creditScoreList = new String[] {"Deep subprime (300-500)","Subprime (501-600)","Nonprime (601-660)","Prime (661-780)","Super prime (781-850)"};
		String deepSubPrime="<html>"+"Based on your score, the average rate is 13.95% (new)"+"<br/>"+"or 19.38% (used). Consider buying an inxepensive"+"<br/>"+"used car and refinancing in 6-12 months."+"</html>";
		String subPrime="<html>"+"Based on your score, the average rate is 11.11% (new)"+"<br/>"+"or 16.23% (used)."+"</html>";
		String nonPrime="<html>"+"Based on your score, the average rate is 6.83% (new)"+"<br/>"+"or 9.98% (used)."+"</html>";
		String prime="<html>"+"Based on your score, the average rate is 3.99% (new)"+"<br/>"+"or 5.45% (used)."+"</html>";
		String superPrime="<html>"+"Based on your score, the average rate is 3.08% (new)"+"<br/>"+"or 3.76% (used)."+"</html>";
		JComboBox<String> creditList = new JComboBox<>(creditScoreList);
		paymentDetails.setText("Payment details");
		paymentDetails.setBounds(800,20,300,40);
		monthlyPayment.setText("Monthly payment");
		monthlyPayment.setBounds(800,80,300,40);
		monthlyPaymentPrice.setBounds(800,140,300,60);
		monthlyPaymentPrice.setForeground(Color.blue);  
		monthlyPaymentPrice.setFont(new Font("Serif", Font.BOLD, 20));  
		beforePrice.setText("(Before taxes & fees)");
		totalAmountPaid.setText("Total amount paid");
		totalAmountPaid.setBounds(800,240,300,40);
		totalAmountPrice.setText("$ 0.00");
		totalAmountPrice.setBounds(800,300,300,40);
		totalAmountPrice.setForeground(Color.blue);  
		totalAmountPrice.setFont(new Font("Serif", Font.BOLD, 20));
		overTheLife.setText("(Over the life of the loan)");
		overTheLife.setBounds(800,350,300,40);
		totalInterestPaid.setText("Total Interest paid");
		totalInterestPaid.setBounds(800,400,300,40);
		totalInterestPrice.setText("$ 0.00");
		totalInterestPrice.setBounds(800,450,300,40);
		totalInterestPrice.setForeground(Color.blue);  
		totalInterestPrice.setFont(new Font("Serif", Font.BOLD, 20));
		beforePrice.setBounds(800,210,300,20);
		askCreditLabel.setText("What's your credit score?");
		showScoreLabel.setText(deepSubPrime);
		askCreditLabel.setBounds(60, 20, 300, 20);
		creditList.setBounds(50, 50,300,40);   
		showScoreLabel.setBounds(60,80,400,70);
		carPrice.setText("Car price ($)");
		carPrice.setBounds(60,170,200,20);
		carPriceText.setBounds(50,200,500,40);
		afterNegotiations.setText("After negotiations"); 
		afterNegotiations.setBounds(60,250,300,20);
		tradeIn.setText("Trade-in / down payment ($)");
		tradeIn.setBounds(60,300,400,20);
		tradeInText.setBounds(50,330,500,40);
		tradeInHint.setText("Your trade-in can be all or part of a down payment");
		tradeInHint.setBounds(60,380,400,20);
		interestRate.setText("Interest rate (%)");
		interestRate.setBounds(60,480,400,20);
		interestText.setBounds(50,510,500,40);
		interestHint.setText("A higher credit score means lower interest rates");
		interestHint.setBounds(60,560,400,20);
		monthNumber.setText("Number of months");
		monthNumber.setBounds(60,610,400,20);
		monthNumberText.setBounds(50,640,500,40);
		monthNumberHint.setText("Suggested max: 36 months for used cars, 60 for new");
		monthNumberHint.setBounds(60,690,400,20);
		mainFrame.add(creditList);     
		mainFrame.add(askCreditLabel);
		mainFrame.add(showScoreLabel);
		mainFrame.add(carPrice);
		mainFrame.add(carPriceText);
		mainFrame.add(afterNegotiations);
		mainFrame.add(tradeIn);
		mainFrame.add(tradeInText);
		mainFrame.add(tradeInHint);
		mainFrame.add(interestRate);
		mainFrame.add(interestText);
		mainFrame.add(interestHint);
		mainFrame.add(monthNumber);
		mainFrame.add(monthNumberText);
		mainFrame.add(monthNumberHint);
		mainFrame.add(paymentDetails);
		mainFrame.add(monthlyPayment);
		mainFrame.add(monthlyPaymentPrice);
		mainFrame.add(beforePrice);
		mainFrame.add(totalAmountPaid);
		mainFrame.add(totalAmountPrice);
		mainFrame.add(overTheLife);
		mainFrame.add(totalInterestPaid);
		mainFrame.add(totalInterestPrice);
		carPriceText.getDocument().addDocumentListener(documentListener);
		carPriceText.getDocument().putProperty("name", "amount");
		interestText.getDocument().addDocumentListener(documentListener);
		interestText.getDocument().putProperty("name", "rate");
		monthNumberText.getDocument().addDocumentListener(documentListener);
		monthNumberText.getDocument().putProperty("name", "month");
		tradeInText.getDocument().addDocumentListener(documentListener);
		tradeInText.getDocument().putProperty("name", "tradein");
		mainFrame.setLayout(null);    
		mainFrame.setSize(1200,1000);    
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creditList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				if(creditList.getItemAt(creditList.getSelectedIndex())=="Subprime (501-600)" ){
					showScoreLabel.setText(subPrime);
				}else if(creditList.getItemAt(creditList.getSelectedIndex())=="Nonprime (601-660)" ){
					showScoreLabel.setText(nonPrime);
				}else if(creditList.getItemAt(creditList.getSelectedIndex())=="Prime (661-780)" ){
					showScoreLabel.setText(prime);
				}else if(creditList.getItemAt(creditList.getSelectedIndex())=="Super prime (781-850)" ){
					showScoreLabel.setText(superPrime);
				}
				else if(creditList.getItemAt(creditList.getSelectedIndex())=="Deep subprime (300-500)" ){
					showScoreLabel.setText(deepSubPrime);
				}
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CarPaymentCalculator().createCarPaymentGUI();;
	}
	DocumentListener documentListener = new DocumentListener() {
	      public void changedUpdate(DocumentEvent documentEvent) {
	    	  update(documentEvent);
	      }
	      public void insertUpdate(DocumentEvent documentEvent) {
	    	  update(documentEvent);
	      }
	      public void removeUpdate(DocumentEvent documentEvent) {
	    	  update(documentEvent);
	      }
	      private void update(DocumentEvent documentEvent) {
	    	  Pattern pattern = Pattern.compile("[0-9]+(\\.[0-9]*)?");
	    	  Document d = documentEvent.getDocument();
	    	  if (d.getProperty("name").equals("amount")){
	    		  	if(carPriceText.getText().equals("")){
	    		  		amount=0.0;
	    		  	}else{
	    		  		Matcher isNum = pattern.matcher(carPriceText.getText());
	    		  		if (!isNum.matches()) {
	    		  			JOptionPane.showMessageDialog(null, "The input is not a number.", "Error", JOptionPane.ERROR_MESSAGE);
	    		        }else{
	    		        	amount = Double.parseDouble(carPriceText.getText());
	    		        }
	    		  	}
	    	  }
	          else if (d.getProperty("name").equals("rate")){
	        	  	if(interestText.getText().equals("")){
	        	  		rate=0.0;
	        	  	}else{
	        	  		Matcher isNum = pattern.matcher(interestText.getText());
	    		  		if (!isNum.matches()) {
	    		  			JOptionPane.showMessageDialog(null, "The input is not a number.", "Error", JOptionPane.ERROR_MESSAGE);
	    		        }else{
	    		        	rate = Double.parseDouble(interestText.getText());
	    		        }
	        	  	}
	          }
	          else if (d.getProperty("name").equals("month")){
	        	  	if(monthNumberText.getText().equals("")){
	        	  		numPeriods=0;
	        	  	}else{
	        	  		//Matcher isNum = pattern.matcher(monthNumberText.getText());
	    		  		if (!isInteger(monthNumberText.getText())) {
	    		  			JOptionPane.showMessageDialog(null, "The input is not a integer.", "Error", JOptionPane.ERROR_MESSAGE);
	    		        }else{
	    		        	if(Integer.parseInt(monthNumberText.getText())<0 || Integer.parseInt(monthNumberText.getText())>84){
			        	  		JOptionPane.showMessageDialog(null, "The month number must be in 0-84.", "Error", JOptionPane.ERROR_MESSAGE);
			        	  	}
		        	  		numPeriods = Integer.parseInt(monthNumberText.getText());
	    		        }
	        	  	}
	          }
	          else if(d.getProperty("name").equals("tradein")){
	        	  	if(tradeInText.getText().equals("")){
	        	  		tradein=0.0;
	        	  	}else{
	        	  		Matcher isNum = pattern.matcher(tradeInText.getText());
	    		  		if (!isNum.matches()) {
	    		  			JOptionPane.showMessageDialog(null, "The input is not a number.", "Error", JOptionPane.ERROR_MESSAGE);
	    		        }else{
	    		        	tradein = Double.parseDouble(tradeInText.getText());
	    		        }
	        	  	}
	          }
	          payment = computePayment(amount, rate, numPeriods,tradein);
	          if(numPeriods==0.0||rate==0.0){
	        	  totalamountpaid=amount-tradein;
	          }else{
	        	  totalamountpaid=payment*numPeriods;
	        	  BigDecimal btotalpayment = new BigDecimal(totalamountpaid);
	        	  totalamountpaid = btotalpayment.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	          }
	          if(rate==0.0||numPeriods==0.0){
	        	  totalinterestpaid=0.0;
	          }else{
	        	  totalinterestpaid=totalamountpaid-(amount-tradein);
	          }
	          BigDecimal bpayment = new BigDecimal(payment);
	          payment = bpayment.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	          DecimalFormat dFormat = new DecimalFormat("###,####,###,###.00");
	          boolean paymentFlag=false;
	          boolean totalamountFlag=false;
	          boolean totalinterestFlag=false;
	          if(payment>=0.0){
        		  paymentFlag=true;
        	  }else{
        		  paymentFlag=false;
        		  payment=Math.abs(payment);
        	  }
        	  if(totalamountpaid>=0.0){
        		  totalamountFlag=true;
        	  }else{
        		  totalamountFlag=false;
        		  totalamountpaid=Math.abs(totalamountpaid);
        	  }
        	  if(totalinterestpaid>=0.0){
        		  totalinterestFlag=true;
        	  }else{
        		  totalinterestFlag=false;
        		  totalinterestpaid=Math.abs(totalinterestpaid);
        	  }
	          if(payment==0.0){
	        	  monthlyPaymentPrice.setText("$ 0.00");
	          }else{
	        	  if(payment*numPeriods<totalamountpaid){
	        		  double lastmonthpayment=totalamountpaid-(payment*(numPeriods-1));
	        		  BigDecimal b = new BigDecimal(lastmonthpayment);
	        		  Double newmonthpayment = b.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	        		  if(payment<1.0 && newmonthpayment<1.0){
	        			  if(paymentFlag==false){
	        				  monthlyPaymentPrice.setText("<html>$ -0"+dFormat.format(payment)+"<br/>"+"    (The last month payment is: <br/>"+"$ -0"+dFormat.format(newmonthpayment)+")"+"</html>");
	        			  }else{
	        				  monthlyPaymentPrice.setText("<html>$ 0"+dFormat.format(payment)+"<br/>"+"    (The last month payment is: <br/>"+"$ 0"+dFormat.format(newmonthpayment)+")"+"</html>");
	        			  }
	        		  }else{
	        			  if(paymentFlag==false){
	        				  monthlyPaymentPrice.setText("<html>$ -"+dFormat.format(payment)+"<br/>"+"    (The last month payment is: <br/>"+"$ -"+dFormat.format(newmonthpayment)+")"+"</html>");
	        			  }else{
	        				  monthlyPaymentPrice.setText("<html>$ "+dFormat.format(payment)+"<br/>"+"    (The last month payment is: <br/>"+"$ "+dFormat.format(newmonthpayment)+")"+"</html>");
	        			  }
	        		  }
	        	  }else{
	        		  if(payment<1.0){
	        			  if(paymentFlag==false){
	        				  monthlyPaymentPrice.setText("$ -0"+dFormat.format(payment));
	        			  }else{
	        				  monthlyPaymentPrice.setText("$ 0"+dFormat.format(payment));
	        			  }
	        		  }else{
	        			  if(paymentFlag==false){
	        				  monthlyPaymentPrice.setText("$ -"+dFormat.format(payment));
	        			  }else{
	        				  monthlyPaymentPrice.setText("$ "+dFormat.format(payment));
	        			  }
	        		  }
	        	  }
	          }
	          if(totalamountpaid<1.0 && totalamountpaid>0.0){
	        	  if(totalamountFlag==false){
	        		  totalAmountPrice.setText("$ -0"+dFormat.format(totalamountpaid));
	        	  }else{
	        		  totalAmountPrice.setText("$ 0"+dFormat.format(totalamountpaid));
	        	  }
	          }else if(totalamountpaid==0.0){
	        	  totalAmountPrice.setText("$ 0.00");
	          }
	          else{
	        	  if(totalamountFlag==false){
	        		  totalAmountPrice.setText("$ -"+dFormat.format(totalamountpaid));
	        	  }else{
	        		  totalAmountPrice.setText("$ "+dFormat.format(totalamountpaid));
	        	  }
	          }
	          if(totalinterestpaid==0.0){
	        	  totalInterestPrice.setText("$ 0.00");
	          }else if(totalinterestpaid<1.0 && totalinterestpaid>0.0){
	        	  if(totalinterestFlag==false){
	        		  totalInterestPrice.setText("$ -0"+dFormat.format(totalinterestpaid));
	        	  }else{
	        		  totalInterestPrice.setText("$ 0"+dFormat.format(totalinterestpaid));
	        	  }
	          }else{
	        	  if(totalinterestFlag==false){
	        		  totalInterestPrice.setText("$ -"+dFormat.format(totalinterestpaid));
	        	  }else{
	        		  totalInterestPrice.setText("$ "+dFormat.format(totalinterestpaid));
	        	  }
	          }
	      }
	};
	double computePayment(double amountPrice, double interestRate, int monthNumber,double tradeinprice) {
		double monthlyI, partial1, denominator, result;
		if(interestRate==0.0){
			if(monthNumber==0){
				result=0.00;
			}else{
				result=(amountPrice-tradeinprice)/monthNumber;
			}
			return result;
		}else{
			if(monthNumber==0){
				result=0.00;
			}else{
		        monthlyI = interestRate / 100.0 / 12.0;
		        partial1=(amountPrice-tradeinprice)*(monthlyI*(Math.pow((1+monthlyI),(double)monthNumber)));
		        denominator=Math.pow((1+monthlyI),monthNumber)-1;
		        result=partial1/denominator;
			}
	        
	        return result;
		}
    }
	public static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	      }
	      return isValidInteger;
	   }

}
