package bzh.gsbrh.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bzh.gsbrh.controleurs.Controleur;

public class Login extends JFrame implements ActionListener {
    
    protected Champ saisieLogin;
    protected Champ saisieMdp;
    Controleur cont; 
    
    public Login(){
        
    }
    
    public Login(Controleur cont){
        super();
        this.cont = cont;
        this.initComponents();
        
        setTitle("Application GSB RH");
        setSize(300,160);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void initComponents(){
        JPanel panelLogin = new JPanel();
        JButton boutonValider = new JButton("Valider");
        boutonValider.addActionListener(this);
        
        saisieLogin = new Champ("Login : ");
        saisieMdp = new Champ("mot de passe : ");
        
        panelLogin.add(saisieLogin);
        panelLogin.add(saisieMdp);
        panelLogin.add(boutonValider);
        
        this.add(panelLogin);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if(this.cont.verifierMdp(this.saisieLogin.getDansSaisie(), this.saisieMdp.getDansSaisie())){
            JOptionPane.showMessageDialog(null, "tout est ok", "Valider", JOptionPane.INFORMATION_MESSAGE);
            this.cont.lancerListeEmploye();
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Essaye encore", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}