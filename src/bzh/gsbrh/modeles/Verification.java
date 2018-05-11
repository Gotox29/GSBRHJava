package bzh.gsbrh.modeles;

public class Verification {

	public boolean verifId(String id) {
        boolean flag = false;
        String compar = Requetes.trouverEmploye(id).getId();
        if(!id.isEmpty() && id.length() <= 3 && compar == null){
            flag = true;
        }
        return flag;
    }

    public boolean verifText(String text){
        boolean flag = false;
        if(!text.isEmpty() && text.length() <= 30){
            flag = true;
        }
        return flag;
    }

    public boolean verifMdp(String mdp){
        boolean flag = false;
        if(!mdp.isEmpty() && mdp.length() <= 20){
            flag = true;
        }
        return flag;
    }

    public boolean verifLogin(String login){
        boolean flag = false;
        if(!login.isEmpty() && login.length() <= 20){
            flag = true;
        }
        return flag;
    }

    public boolean verifCP(String cp){
        boolean flag = false;
        if(!cp.isEmpty() && cp.length() == 5){
            flag = true;
        }
        return flag;
    }

}
