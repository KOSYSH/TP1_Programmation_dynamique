package ma.projet.metier;

import ma.projet.dao.IDao;

public class MetierImpl implements IMetier{

    private IDao dao;

    public void setDao(IDao dao) {
        this.dao=dao;
    }

    @Override
    public double calcul(){
        if (dao== null){
            throw new IllegalStateException("Loperation dinjection de dao nest pas affectue .");
        }
        double valeurBase = dao.getValue();
        double resultat = valeurBase * 2;
        System.out.println("Loperation a ete effectue."+ resultat);
        return resultat;
    }
}
