/*
	BASE N - BASE N CONVERTOR IN JAVA
	GUI based program in Java to convert numbers from one number system to another.
*/

import java.awt.event.*;  
import java.awt.Color;
import javax.swing.*; 

public class Convertor
{

	// Function to convert number from one base to another
	static String baseToBase(String num, int base1, int base2) 
	{
    	int no = convFrmBaseToDeci(num, base1);
    	return convFrmDecToBase(no, base2);
	}

	// Function to convert from Decimal to Base N
	static String convFrmDecToBase(int num, int base)
	{

	    String res = "";
	    int rem;
	    // Convert input number is given base by repeatedly
	    // dividing it by base and taking remainder
	    while (num > 0) {
	        rem = num % base;
	        if (base == 16) {
	            if (rem == 10)
	                res += 'A';
	            else if (rem == 11)
	                res += 'B';
	            else if (rem == 12)
	                res += 'C';
	            else if (rem == 13)
	                res += 'D';
	            else if (rem == 14)
	                res += 'E';
	            else if (rem == 15)
	                res += 'F';
	            else
	                res += rem;
	        } else
	            res += rem;

	        num /= base;
	    }
	    // Reverse the result
	    return new StringBuffer(res).reverse().toString();
	}

	// Function to convert from Base N to Decimal
	static int convFrmBaseToDeci(String num, int base) 
	{

	    if (base < 2 || (base > 10 && base != 16))
	        return -1;

	    int val = 0;
	    int power = 1;

	    for (int i = num.length() - 1; i >= 0; i--) {
	        int digit = digitToVal(num.charAt(i));

	        if (digit < 0 || digit >= base)
	            return -1;

	        // Decimal equivalent is str[len-1]*1 +
	        // str[len-1]*base + str[len-1]*(base^2) + ...
	        val += digit * power;
	        power = power * base;
	    }

	    return val;
	}

	// Function to find the int value of characters eg. A = 10
	static int digitToVal(char c) 
	{
	    if (c >= '0' && c <= '9')
	        return (int) c - '0';
	    else
	        return (int) c - 'A' + 10;
	}

	// Function to check weather the input is a valid Base N number 
	static boolean isValid(int base, String num)
	{
		if (num.length() == 0)
			return false;

		switch (base)
		{
			case 2:
				for (int i = 0; i < num.length(); i++)
				{
					if (num.charAt(i) != '0' && num.charAt(i) != '1')
						return false;
				}
				break;

			case 10:
				for (int i = 0; i < num.length(); i++)
				{
					if (!Character.isDigit(num.charAt(i)))
						return false;
				}
				break;

			case 8:
				for (int i =0; i< num.length(); i++)
				{
					if (!Character.isDigit(num.charAt(i)) || num.charAt(i) == '8' || num.charAt(i) == '9')
						return false;
				}
				break;

			case 16: 
				for (int i = 0; i <num.length(); i++)
				{
					if (!Character.isDigit(num.charAt(i)))
						if (!"ABDCEF".contains(String.valueOf(num.charAt(i))))
							return false;
				}
				break;
		}

		return true;
	}


	public static void main(String args[])
	{
		// Creating instance of JFrame with the title
		JFrame frame = new JFrame("Base N Convertor");

		// Creating the title label (instacne of JLabel)
		JLabel title = new JLabel("<html><h1><b>Base N Convertor</b></h1></html>");
		// Setting bounds of the JComponent: x axis, y axis, width, height 
		title.setBounds(0,50,400,30); 
		// Setting the text alignment to center
		title.setHorizontalAlignment(SwingConstants.CENTER);

		// Creating the to label (instacne of JLabel)
		JLabel label1 = new JLabel("<html><h3><b>to</b></h3></html>");
		// Setting bounds of the JComponent: x axis, y axis, width, height 
		label1.setBounds(0,150,400,30); 
		// Setting the text alignment to center
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		String system[]={"Decimal","Binary","Octal","Hexadecimal"};        
    	JComboBox combobox1 = new JComboBox(system);
    	combobox1.setBounds(25,150,150,30);

    	JComboBox combobox2 = new JComboBox(system);
    	combobox2.setBounds(225,150,150,30);

		// Creating the Text Field for the input (instance of JTextField)
		JTextField textfield1 = new JTextField();
		// Setting bounds of the JComponent: x axis, y axis, width, height 
		textfield1.setBounds(125, 230, 150, 30);
		// Setting the text alignment to right
		textfield1.setHorizontalAlignment(SwingConstants.RIGHT);           

		// Creating the Calculate button (instance of JButton)
		JButton button1 = new JButton("Convert"); 
		// Setting bounds of the JComponent: x axis, y axis, width, height 
		button1.setBounds(150, 280, 100, 40); 

		// Creting the results label (instance of JLabel)
		JLabel result = new JLabel();
		// Setting bounds of the JComponent: x axis, y axis, width, height
		result.setBounds(20,300,360,100);
		// Setting the text alignment to center
		result.setHorizontalAlignment(SwingConstants.CENTER);
		// Making the label invisible (initially)
		result.setVisible(false);

		// Adding an ActionListener to the button to detect clicks
		button1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int baseArray[] = {10,2,8,16};

					int base1 = baseArray[combobox1.getSelectedIndex()];
					int base2 = baseArray[combobox2.getSelectedIndex()];
					String num = textfield1.getText().toUpperCase();

					String resultString = "";

					if (isValid(base1, num))
					{
						resultString =   "<html><h2><b>" + 
												baseToBase(num, base1, base2) + 
												"</b></h2></html>";
					}
					else
					{
						resultString =   "<html><h2><b>" + 
												"Invalid " + combobox1.getItemAt(combobox1.getSelectedIndex()) + " number" + 
												"</b></h2></html>";
					}

					result.setVisible(true);
					result.setText(resultString);
				}
			});

		// Adding all the components to the frame
		frame.add(title);
		frame.add(label1);
		frame.add(combobox1);
		frame.add(combobox2);
		frame.add(textfield1);
		frame.add(button1); 
		frame.add(result);
		 
		// Setting the size of the frame to 400 width and 500 height          
		frame.setSize(400,500);
		// Setting the background color to yellow
		frame.getContentPane().setBackground(Color.gray);
		// Setting the Calculate button as the default button
		frame.getRootPane().setDefaultButton(button1);
		// Using no layout managers   
		frame.setLayout(null);
		// Making the frame visible 
		frame.setVisible(true);   
	}

}