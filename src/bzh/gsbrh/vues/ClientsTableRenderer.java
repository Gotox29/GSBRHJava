package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class ClientsTableRenderer extends EditeurCellule
{
	private Bouton button;
	private String label;
	private boolean clicked;
	private int row, col;
	private JTable table;
	public String id;
	
	public ClientsTableRenderer(JCheckBox checkBox, Observateur o)
	{
		super(checkBox, o);
		
		button = new BReinitialiser();
		button.setOpaque(true);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fireEditingStopped();
			}
		});
	}
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		this.table = table;
		this.row = row;
		this.col = column;

		button.setForeground(Color.black);
		button.setBackground(UIManager.getColor("Button.background"));
		label = (value == null) ? "" : value.toString();
		if(value instanceof Bouton){
			  label = ((Bouton) value).getText();
		}
		button.setText(label);
		clicked = true;
		return button;
	}
	public Object getCellEditorValue()
	{
		String id = "";
		if (clicked)
		{
			id = (String) table.getValueAt(row, 0);
			
			if(new String(label).equals(Lexique.BOUTON_MODIF)){
				notifierObservateur(id,Lexique.LI_MO);
			}
			
			if(new String(label).equals(Lexique.BOUTON_SUPPR)){
				notifierObservateur(id,Lexique.LI_PR);
			}
			
		}
		clicked = false;
		
		return new String(label);
	}

	public boolean stopCellEditing()
	{
		clicked = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped()
	{
		try{
		super.fireEditingStopped();
		}catch(Exception e){
			
		}
	}

}