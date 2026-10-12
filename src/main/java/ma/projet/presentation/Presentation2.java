package ma.projet.presentation;

import ma.projet.dao.IDao;
import ma.projet.metier.IMetier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class Presentation2 {
    public static void main(String[] args){
        try {
            //Chargements des noms des classes depuis config
                String[] classNames = loadClassNames("config.txt");

            //Instantie le DAO
            IDao dao = (IDao) instantiateClass(classNames[0]);
            System.out.println("DAO instancie: "+ dao.getClass().getName());

            //Instantie le metier
            IMetier metier =  (IMetier) instantiateClass(classNames[1]);
            System.out.println("Metier instancie:" + metier.getClass().getName());

            //Injecter le Dao
            injectDependency(metier,"setDao",dao);

            //execeuter loperation du calcul
            double resultat = metier.calcul();
            System.out.println("Resulats = " + resultat);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Lit les noms de callsess a partie du config.txt      .
     */
    private static String[] loadClassNames(String fileName) throws IOException {
        String[] classNames = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 2; i++) {
                classNames[i] = reader.readLine().trim();
            }
        }
        return classNames;
    }

    /**
     * Instancee une class a partie de son nom complet
     */
    private static Object instantiateClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }

    /**
     * Inejcte une depenadce via setter
     */
    private static void injectDependency(Object target, String setterName, Object dependency) throws Exception {
        Class<?> targetClass = target.getClass();
        Method setterMethod = targetClass.getMethod(setterName, dependency.getClass().getInterfaces()[0]);
        setterMethod.invoke(target, dependency);
    }
}
