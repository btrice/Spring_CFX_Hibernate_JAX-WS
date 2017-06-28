package fr.msaidara.services;

import fr.msaidara.api.InProduct;
import fr.msaidara.api.OutProduct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@WebService(serviceName="ProductService", targetNamespace="http://www.msaidara.product/service")
public class ProductServiceEndpoint implements ProductService {

	@Autowired
	private ProductServiceImpl service;
	
	@WebMethod
	public boolean addProduct(InProduct product) throws BusinessException {
		return service.addProduct(product);
	}
	
	@WebMethod
	public boolean deleteProduct(InProduct product) throws BusinessException {
		return service.deleteProduct(product);
	}
	
	@WebMethod
	public boolean updateProduct(InProduct product) throws BusinessException {
		return service.updateProduct(product);
	}
	
	@WebMethod
	public List<OutProduct> searchProduct(String productName) throws BusinessException {
		return service.searchProduct(productName);
	}
	
	@WebMethod
	public OutProduct getProductById(int id) throws BusinessException {
		return service.getProductById(id);
	}
	
	@WebMethod
	public List<OutProduct> getAllProductByPrice(double price) throws BusinessException {
		return service.getAllProductByPrice(price);
	}
	
}
