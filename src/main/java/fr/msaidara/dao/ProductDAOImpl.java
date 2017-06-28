package fr.msaidara.dao;

import java.util.List;
import fr.msaidara.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;


@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	//private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	private static final Log logger = LogFactory.getLog(ProductDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean addProduct(Product product){
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.save(product);
			logger.info("Product saved successfully, Product Details=" + product);
		} catch(Exception e){
			logger.error("addProduct Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Transactional
	public boolean deleteProduct(Product product){
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.load(Product.class, new Integer(product.getId()));
		if( p != null ){
			try{
				session.delete(product);
				logger.info("Product deleted successfully, Product Details=" + product);
			}catch(Exception e){
				logger.error("deleteProduct Exception: " + e.getMessage());
				return false;
			}
		}
		return true;
	}

	@Transactional
	public boolean updateProduct(Product product){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.update(product);
			logger.info("Product updated successfully, Product Details=" + product);
		}catch(Exception e){
			logger.error("updateProduct Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Transactional
	public List<Product> searchProduct(String productName){
		List<Product> productList = null;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			productList = session.createQuery("FROM Product P WHERE P.name = '" + productName + "'").list();
			for (Product p : productList) {
				logger.info("Product List searchProduct ::Product founded successfully, Product Details=" + p);
			}
		} catch (Exception e){
			logger.error("searchProduct Exception: " + e.getMessage());
		}
		return productList;
	}

	@Transactional
	public Product getProductById(int id){
		Product p = null;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			 p = (Product) session.load(Product.class, new Integer(id));
			logger.info("Product loaded successfully, Product Details=" + p);
		} catch(Exception e){
			logger.error("getProductById Exception: " + e.getMessage());
		}
		return p;
	}

	@Transactional
	public List<Product> getAllProductByPrice(double price){
		List<Product> productList = null;
		Session session = this.sessionFactory.getCurrentSession();
		try {
			productList = session.createQuery("FROM Product P WHERE P.price =" + price).list();
			for (Product p : productList) {
				logger.info("Product List getAllProductByPrice :Product founded successfully, Product Details=" + p);
			}
		}catch (Exception e){
			logger.error("getAllProductByPrice Exception: " + e.getMessage());
		}
		return productList;
	}
	

	
}
