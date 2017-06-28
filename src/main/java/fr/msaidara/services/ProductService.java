package fr.msaidara.services;

import fr.msaidara.api.InProduct;
import fr.msaidara.api.OutProduct;
import java.util.List;
import javax.jws.WebParam;



public interface ProductService {
	
	
	public boolean addProduct(@WebParam(name="product") InProduct product) throws BusinessException;
	
	public boolean deleteProduct(@WebParam(name="product") InProduct product) throws BusinessException;
	
	public boolean updateProduct(@WebParam(name="product") InProduct product) throws BusinessException;
	
	public List<OutProduct> searchProduct(@WebParam(name="productName") String productName) throws BusinessException;
	
	public OutProduct getProductById(@WebParam(name="id") int id) throws BusinessException;
	
	public List<OutProduct> getAllProductByPrice(@WebParam(name="price") double price) throws BusinessException;
}
