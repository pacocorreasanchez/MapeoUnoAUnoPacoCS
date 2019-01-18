package es.albarregas.dao;

import es.albarregas.beans.Profesor;
import es.albarregas.persistencia.HibernateUtil;

import java.util.List;

import org.hibernate.Session;


public class ProfesorDAO implements IProfesorDAO {

    @Override
    public Profesor getOne(int pk) {
        Profesor profesor = new Profesor();
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            profesor = (Profesor)sesion.get(Profesor.class, pk);
            sesion.getTransaction().commit();
        } catch(org.hibernate.JDBCException jdbce){
            if(sesion != null){
                sesion.getTransaction().rollback();
            }
        } finally {
            if(sesion != null){
                sesion.close();
            }
        }
        return profesor;
    }

}
