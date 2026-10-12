package ma.projet.dao;

//Implimentation dinterface Idao Plus une simulation de recuperation des donnes

public class DaoImpl implements IDao{

    @Override
    public double getValue(){
        //simulation du recuperation des donnes
        System.out.println("La recuperation de la valeur depuis la source de donnes ");
        return 100.0;
    }
}
