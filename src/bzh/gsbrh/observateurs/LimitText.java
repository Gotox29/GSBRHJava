package bzh.gsbrh.observateurs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitText extends PlainDocument{
	private int limit;
	private boolean nombre;
	private String[] numero = {"1","2","3","4","5","6","7","8","9","0"};

	public LimitText(int limit, boolean nombre){
		super();
		this.nombre = nombre;
		this.limit =limit;
	}
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException{
		if(str == null) return;
		
		if(nombre){
			try{
				int number = Integer.parseInt(str);
				if(number >= 0 && number < 99999 && (getLength() + str.length()) <= limit){
					super.insertString(offset, str, attr);
				}else if(number > 0 && (getLength() + str.length()) <= limit){
					NumberFormat formatterTel = new DecimalFormat("0000000000");
					str = formatterTel.format(number);
					super.insertString(offset,  str, attr);
				}else {
					
					return;
				}
			}catch(Exception e){
				
				return;
			}

		}else if((getLength() + str.length()) <= limit){
			super.insertString(offset,  str, attr);

		}else return;

	}

}
