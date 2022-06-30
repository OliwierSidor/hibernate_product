package pl.sda.arppl4.hibernatestore.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernatestore.model.Product;
import pl.sda.arppl4.hibernatestore.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduktDao implements IProductDao {

    @Override
    public void addProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            session.merge(product);

            transaction.commit();
        } catch (SessionException se) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            session.remove(product);

            transaction.commit();
        } catch (SessionException se) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public Optional<Product> returnProduct(Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        try (Session session = factory.openSession()) {
            Product objectProduct = session.get(Product.class, id);
            return Optional.ofNullable(objectProduct);
        }
    }

    @Override
    public List<Product> returnProductsList() {
        List<Product> productList = new ArrayList<>();

        SessionFactory factory = HibernateUtil.getSessionFactory();

        try (Session session = factory.openSession()) {
            TypedQuery<Product> question = session.createQuery("from Product ", Product.class);
            List<Product> resultOfQuestion = question.getResultList();

            productList.addAll(resultOfQuestion);
        } catch (SessionException se) {
            System.err.println("Błąd wczytywania danych");
        }
        return productList;
    }

    @Override
    public void updateProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(product);

            transaction.commit();
        } catch (SessionException se) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
