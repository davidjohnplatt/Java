import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;



public class masterSail implements ActionListener{
    JFrame mainFrame;
    BorderLayout layout;
    GridBagLayout gridBag;
    GridBagConstraints gbc;
    Container contentPane;
    Container cPane;
    
    Choice choice1;
    Choice choice2;
    Choice racenoChoice;
        
    JTextField jText1;
    JTextField jText2;
    JTextField jTextBoatType;
    JTextField jTextBoatRating;
    JTextField jTextHours;
    JTextField jText5;
    JTextField jText6;
    JTextField jText7;
    JTextField jText8;
    JTextField jTextRace;
    JTextField jTextRaceDate;
    JTextField jTextRaceComm;
    
    CheckboxGroup rstatus;
   
    
    JButton jButton1;
    JButton jButton2;
    JButton jButtonCalculate;
    JButton jButtonHTML;
   
    Connection conn;
    int currentRow;
    int raceNumber = 0;
    Object [] [] data = new Object [20] [11];
    Hashtable ratemap = new Hashtable();
//    rating ratings = new rating();
     
   	public masterSail() {
   		mainFrame = new JFrame("Sailing Master");
        mainFrame.setSize(200,200);
        contentPane = mainFrame.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        rstatus = new CheckboxGroup();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    	}
    	catch (SQLException e) {
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}
    
       
// this would set componenets to fill available space - not wanted here  
//      gbc.fill = GridBagConstraints.HORIZONTAL;
    
  	    addWidgets();
  	    loadSailors();
  	    loadBoats();
  	    loadRaces();
 
// Exit when the window is closed.
 //       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       	mainFrame.setSize(700,360);
		mainFrame.setVisible(true);

		currentRow = 0;
	}

	
	private void addWidgets(){
		
	
        jText1 = new JTextField(15);
        jText2 = new JTextField(6);
        jTextBoatType = new JTextField(15);
        jTextBoatRating = new JTextField(3);
        jTextHours = new JTextField(3);
        jText5 = new JTextField(3);
        jText6 = new JTextField(3);
        jText7 = new JTextField(15);
        jText8 = new JTextField(15);
        jTextRace = new JTextField(3);
        jTextRaceDate = new JTextField(10);
        jTextRaceComm = new JTextField(20);
        
        
        String t = "default";
        
        choice1 = new Choice();
        choice2 = new Choice();
        racenoChoice = new Choice();
            
        JLabel jLabel1 = new JLabel("Sailor      ");
        JLabel jLabel2 = new JLabel("Boat Number ");
        JLabel jLabel3 = new JLabel("Boat Type   ");
        JLabel jLabel4 = new JLabel("Rating      ");
        JLabel jLabel5 = new JLabel("Hours       ");
        JLabel jLabel6 = new JLabel("Minutes     ");
        JLabel jLabel7 = new JLabel("Seconds     ");
        JLabel jLabel8 = new JLabel("Corrected   ");
        JLabel jLabel9 = new JLabel("Points      ");
        JLabel dummyLabel = new JLabel(" ");
        JLabel dummyLabel1 = new JLabel(" ");
        JLabel dummyLabel2 = new JLabel(" ");
        JLabel raceLabel = new JLabel("Race");
        JLabel raceDateLabel = new JLabel("Date");
        JLabel raceCommitteeLabel = new JLabel("Race Committee");
      
        gbc.anchor = GridBagConstraints.WEST;

        putWidget(0,0,raceLabel);      
        putWidget(1,0,racenoChoice);
        racenoListener controlraceChoice = new racenoListener();
        racenoChoice.addItemListener(controlraceChoice); 
        putWidget(2,0,raceDateLabel);
        putWidget(3,0,jTextRaceDate);
        
        putWidget(2,1,raceCommitteeLabel);
        putWidget(3,1,jTextRaceComm);
 
        putWidget(0,2,dummyLabel1);
        putWidget(0,3,jLabel1); 
        
      	gbc.gridx = 2;
        gbc.gridy = 3;
        gridBag.setConstraints(choice1,gbc);
        contentPane.add(choice1);
        choice1Listener controlChoice1 = new choice1Listener();
        choice1.addItemListener(controlChoice1); 
                
        gbc.gridx = 1;
        gbc.gridy = 3;
        gridBag.setConstraints(jText1,gbc);	
        contentPane.add(jText1);
     
        gbc.gridx = 0;
        gbc.gridy = 4;
        gridBag.setConstraints(jLabel2,gbc);
        contentPane.add(jLabel2); 
       
        gbc.gridx = 1;
        gbc.gridy = 4;
        gridBag.setConstraints(jText2,gbc);	
        contentPane.add(jText2);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        gridBag.setConstraints(choice2,gbc);	
        contentPane.add(choice2);
        choice2Listener controlChoice2 = new choice2Listener();
        choice2.addItemListener(controlChoice2);
         
        gbc.gridx = 0;
        gbc.gridy = 5;
        gridBag.setConstraints(jLabel3,gbc);
        contentPane.add(jLabel3);
         
        gbc.gridx = 1;
        gbc.gridy = 5;
        gridBag.setConstraints(jTextBoatType,gbc);	
        contentPane.add(jTextBoatType);
         
        gbc.gridx = 0;
        gbc.gridy = 6;
        gridBag.setConstraints(jLabel4,gbc);
        contentPane.add(jLabel4); 
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        gridBag.setConstraints(jTextBoatRating,gbc);	
        contentPane.add(jTextBoatRating);
 
        gbc.gridx = 0;
        gbc.gridy = 7;
        gridBag.setConstraints(jLabel5,gbc);
        contentPane.add(jLabel5); 
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        gridBag.setConstraints(jTextHours,gbc);	
        contentPane.add(jTextHours);
        jTextHours.setText("00"); 
       
    
        gbc.gridx = 0;
        gbc.gridy = 8;
        gridBag.setConstraints(jLabel6,gbc);
        contentPane.add(jLabel6); 
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        gridBag.setConstraints(jText5,gbc);	
        contentPane.add(jText5);
        jText5.setText("00");
      
        gbc.gridx = 0;
        gbc.gridy = 9;
        gridBag.setConstraints(jLabel7,gbc);
        contentPane.add(jLabel7); 
        
        gbc.gridx = 1;
        gbc.gridy = 9;
        gridBag.setConstraints(jText6,gbc);	
        contentPane.add(jText6);
        jText6.setText("00");

      /*  gbc.gridx = 0;
        gbc.gridy = 11;
        gridBag.setConstraints(dummyLabel,gbc);
        contentPane.add(dummyLabel); */
        putWidget(0,11,dummyLabel);
        
        gbc.anchor = GridBagConstraints.EAST;
        
        jButton1 = new JButton("Add");
        jButton1.addActionListener(this);
        putWidget(0,13,jButton1);
        
        jButton2 = new JButton("Show");
        jButton2.addActionListener(this);       
        putWidget(1,13,jButton2);
 
        jButtonCalculate = new JButton("Calculate");
        jButtonCalculate.addActionListener(this);
        putWidget(2,13,jButtonCalculate);
        
        jButtonHTML = new JButton("HTML");
        jButtonHTML.addActionListener(this);
        putWidget(3,13,jButtonHTML);
        
        gbc.anchor = GridBagConstraints.WEST;       
        putWidget(2,6,(new Checkbox("OK",rstatus,true)));
        putWidget(2,7,(new Checkbox("DNS",rstatus,false)));
        putWidget(2,8,(new Checkbox("DNF",rstatus,false)));
        putWidget(2,9,(new Checkbox("DSQ",rstatus,false)));
   
   
	}
	
	private void putWidget(int x, int y, Component c){
		gbc.gridx = x;
        gbc.gridy = y;
        gridBag.setConstraints(c,gbc);
        contentPane.add(c);
	}
	

	
	private void loadChoice1() {
		try {
			String s = "";
      		BufferedReader r = new BufferedReader(new FileReader("sailors.txt"));
      		while( ( s = r.readLine())!= null ) {
      			StringTokenizer st = new StringTokenizer(s,",");
          		choice1.add(st.nextToken());
          		String b = st.nextToken();
      		}	
      		r.close();
    	}
    	catch (IOException e) {
      		e.printStackTrace();
    	}	
	}
	
	private void loadSailors() {
		try {
			String query = "SELECT * FROM sailors ORDER BY sailor";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               choice1.add(rset.getString(1));
       		}
    	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}	
	}
	
	private void loadRaces() {
		try {
			String query = "SELECT raceno FROM races ORDER BY raceno";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               racenoChoice.add(rset.getString(1));
       		}
    	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}	
	}
	
	private void loadBoats() { 
 	   try {
 	   		String query = "SELECT * FROM boats ORDER BY boattype";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               choice2.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}
	}
	
	private void addResult(){
		Checkbox c = rstatus.getCurrent();   
		String boatPosition;
		String actualTime = timeToSeconds(jTextHours.getText(),jText5.getText(),jText6.getText());
		String boatRating = getBoatRating(jTextBoatType.getText());
		String correctedTime = correctedSeconds(actualTime,boatRating);
		if (c.getLabel() == "OK"){
			boatPosition = "";
		}
		else {
		    boatPosition = c.getLabel();
		}
        String insCmd = "INSERT INTO race_detail ";	
		insCmd = insCmd + "(raceno,position,sailor,boattype,boatnumber,boatrating,elapsed,corrected)"; 
		insCmd = insCmd + "values("; 
		insCmd = insCmd + raceNumber + ",";
		insCmd = insCmd + "'" + boatPosition + "'" + ",";
		insCmd = insCmd + "'" + jText1.getText() + "'" + ",";
		insCmd = insCmd + "'" + jTextBoatType.getText() + "'" + ",";
		insCmd = insCmd + "'" + jText2.getText() + "'" + ",";
		insCmd = insCmd + "'" + boatRating + "'" + ",";
		insCmd = insCmd + "'" + actualTime + "'" + ",";
		insCmd = insCmd + "'" + correctedTime + "'";
		insCmd = insCmd +")";
		
//		System.out.println(insCmd);
		try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(insCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
		
	}
	
	private void clearScreen() {
		jText1.setText("");
    	jText2.setText("");
    	jTextBoatType.setText("");
    	jTextBoatRating.setText("");
    	jTextHours.setText("00");
    	jText5.setText("00");
    	jText6.setText("00");	
	}
	
	private int countBoats(int raceno) {
		String query = "SELECT COUNT(*) FROM race_detail where raceno = " + raceno;
		int rv = 0;
		try {
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
         	rset.next ();
        	rv = rset.getInt(1);
       	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}
		return rv;
	}
	
	private String getScore(String position,int numBoats) {
		String query = "SELECT score FROM scores where position = " + "'" + position + "'";
		query = query + " AND numboats = " + numBoats;
		String rv = "";
		try {
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
         	rset.next ();
        	rv = rset.getString(1);
       	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}
		return rv;
	}
	
	private void calcScores() {
//		System.out.println(countBoats(raceNumber));
		
		String query = "SELECT * FROM race_detail WHERE raceno = ";
		query = query + raceNumber + " ORDER BY corrected";
		int nb = countBoats(raceNumber);
		String nbs = nb + "";
		try {
			int i = 1;
			Statement stmt = conn.createStatement (ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {      		
        		String sc = i + "";
        		String actualTime = rset.getString(7);
        		String boatRating = rset.getString(6);
        		String correctedTime = correctedSeconds(actualTime,boatRating);
        		int dvalue = rset.getInt(8);       		
        		rset.updateString(8,correctedTime);
        		if (dvalue == 9999) {			
        			rset.updateString(9,getScore(nbs,nb));
        		}
        		else {	
        		    rset.updateString(2,sc);
        			rset.updateString(9,getScore(sc,nb));
        		}
        		rset.updateRow();      	
        		i++;
        	}
        	
       	}
    	catch (SQLException e) {
      		e.printStackTrace();
    	}
		
	}

  	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	String hours,minutes,seconds,secondTime,boatRating;
    	if (source == jButton1){
    		addResult();
    		clearScreen();
    		currentRow++;
    	} else if (source == jButton2){ 		
            DisplayRace r = new DisplayRace(raceNumber);
    	} else if (source == jButtonCalculate) {
            calcScores();
    	} else if (source == jButtonHTML) {
    		makeHTML m = new makeHTML(raceNumber);
    	}
	}
	
	private String timeToSeconds(String inStr1,String inStr2,String inStr3) {	
		double result;
		double hours = Double.parseDouble(inStr1);
		double minutes = Double.parseDouble(inStr2);
		double seconds = Double.parseDouble(inStr3);
		result = hours * 3600 + minutes * 60 + seconds;
		Double Result = new Double(result); 
		return Result.toString();
	}
	
	private String correctedSeconds(String inStr1,String inStr2) {
		double result;
		double seconds = Double.parseDouble(inStr1);
		double rating = Double.parseDouble(inStr2);
		result = (seconds * 100) / rating;
		
		if (result == 0){
			return "9999";
		}
		else{
			Double Result = new Double(result);
			return Result.toString();
		}
	}

	
	public static void main(String[] args) {
		masterSail m = new masterSail();		
	}
	
	public String getBoatRating(String inStr) {
		String outStr = " ";
		String query = "SELECT boatrating FROM boats WHERE boattype = ";
      	query = query  + "'" + inStr + "'";
      	
      	try {
      		Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	rset.next (); 
        	outStr = rset.getString(1);
        }
       	catch (SQLException f){
       		System.out.println(query);
       		System.out.println(f);
       	}
       	return outStr;
	}

	class choice2Listener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
           	jTextBoatType.setText(choice2.getSelectedItem());
           	jTextBoatRating.setText(getBoatRating(choice2.getSelectedItem()));
    	}
  	}
  	
  	class choice1Listener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		String boatRating;
      		jText1.setText((String)choice1.getSelectedItem());
      		
      		String query = "SELECT * FROM sailors WHERE sailor = ";
      		query = query  + "'" + choice1.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
               	jText2.setText(rset.getString(3));
               	jTextBoatType.setText(rset.getString(2));
       			jTextBoatRating.setText(getBoatRating(rset.getString(2)));
       		}
       		catch (SQLException f){
       			System.out.println(query);
       			System.out.println(f);
       		} 
    	}
  	}

  	class racenoListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		
      		String query = "SELECT * FROM races WHERE raceno = ";
      		query = query  + "'" + racenoChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
        		raceNumber = rset.getInt(1);
               	jTextRaceDate.setText(rset.getString(2));
               	jTextRaceComm.setText(rset.getString(3));
       		}
       		catch (SQLException f){
       			System.out.println(query);
       			System.out.println(f);
       		} 
    	}
  	}
}