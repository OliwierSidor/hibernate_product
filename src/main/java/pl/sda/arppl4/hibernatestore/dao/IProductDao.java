package pl.sda.arppl4.hibernatestore.dao;

import pl.sda.arppl4.hibernatestore.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDao {
    public void addProduct(Product product);

    public void removeProduct(Product product);

    public Optional<Product> returnProduct(Long id);

    public List<Product> returnProductsList();

    public void updateProduct(Product product);
}