package bzh.gsbrh.vues;

import javax.swing.JOptionPane;

import bzh.gsbrh.observateurs.Fabrique;
import bzh.gsbrh.observateurs.Languages;

public class FactMessage extends Fabrique{

	

	public static int message(int code){
		int confirm = 1;
		switch(code){
		case _CONFIRM_S:
			confirm = JOptionPane.showConfirmDialog(null, Languages._CONFIRM_S, "Confirmation", JOptionPane.YES_NO_OPTION);
			break;
		case _CONFIRM_M:
			confirm = JOptionPane.showConfirmDialog(null, Languages._CONFIRM_M, "Confirmation", JOptionPane.YES_NO_OPTION);
			break;
		case _CONFIRM_F:
			confirm = JOptionPane.showConfirmDialog(null, Languages._CONFIRM_F, "Confirmation", JOptionPane.YES_NO_OPTION);
			break;
			
		case _MESSAGE_A:
			JOptionPane.showMessageDialog(null, Languages._MESSAGE_A, "Message", JOptionPane.INFORMATION_MESSAGE);
			break;
		case _MESSAGE_S:
			JOptionPane.showMessageDialog(null, Languages._MESSAGE_S, "Message", JOptionPane.INFORMATION_MESSAGE);
			break;
		case _MESSAGE_M:
			JOptionPane.showMessageDialog(null, Languages._MESSAGE_M, "Message", JOptionPane.INFORMATION_MESSAGE);
			break;
		case _MESSAGE_C:
			JOptionPane.showMessageDialog(null, Languages._MESSAGE_C, "Message", JOptionPane.INFORMATION_MESSAGE);
			break;

		case _ERREUR_CO:
			JOptionPane.showMessageDialog(null, Languages._ERREUR_CO, "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case _ERREUR_CH:
			JOptionPane.showMessageDialog(null, Languages._ERREUR_CH, "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case _ERREUR_MO:
			JOptionPane.showMessageDialog(null, Languages._ERREUR_MO, "Erreur", JOptionPane.ERROR_MESSAGE);
			break;

		}
		
		return confirm;
	}
}
