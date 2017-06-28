package fr.msaidara.services;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.msaidara.api.InProduct;
import fr.msaidara.api.OutProduct;
import fr.msaidara.model.Product;
import fr.msaidara.dao.ProductDAO;


// End of user code

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);
	@Autowired
	private ProductDAO productDAO;

	private String nomService="ProductService";
	private BusinessException e;


	public boolean addProduct(InProduct product) throws BusinessException {
		String nomMethode ="addProduct";
		if(!IsEmpty(product)){
			if(productDAO.addProduct(getProductFromInOut(product))){
				return true;
			}
			e = creerBusinessException("01", nomService+"."+nomMethode+ " : Product save failled");
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product cannot be empty or price must be higher than 0.0");
		}
		throw e;
	}

	public boolean deleteProduct(InProduct product) throws BusinessException {
		String nomMethode ="deleteProduct";
		BusinessException e;
		if(!IsEmpty(product)){
			if(productDAO.deleteProduct(getProductFromInOut(product))){
				return true;
			}
			e = creerBusinessException("01", nomService+"."+nomMethode+ " : Product delete failled");
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product cannot be empty or price must be higher than 0.0");
		}
		throw e;
	}

	public boolean updateProduct(InProduct product) throws BusinessException {
		String nomMethode ="updateProduct";
		BusinessException e;
		if(!IsEmpty(product)){
			if(productDAO.updateProduct(getProductFromInOut(product))){
				return true;
			}
			e = creerBusinessException("01", nomService+"."+nomMethode+ " : Product update failled");
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product cannot be empty or price must be higher than 0.0");
		}
		throw e;
	}

	public List<OutProduct> searchProduct(String productName) throws BusinessException {
		String nomMethode ="searchProduct";
		BusinessException e;
		if(!"".equals(productName)){
			return getOutlist(productDAO.searchProduct(productName));
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product name cannot be empty");
		}
		throw e;
	}

	public OutProduct getProductById(int id) throws BusinessException {
		String nomMethode ="getProductById";
		BusinessException e;
		if( id > 0){
			return getOutFromProduct(productDAO.getProductById(id));
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product id cannot be empty or id must be higher than 0");
		}
		throw e;
	}

	public List<OutProduct> getAllProductByPrice(double price) throws BusinessException {
		String nomMethode ="getAllProductByPrice";
		BusinessException e;
		if( price > 0.0 ){
			return getOutlist(productDAO.getAllProductByPrice(price));
		} else {
			e = creerBusinessException("02", nomService+"."+nomMethode+ " : Product price cannot be empty or price must be higher than 0.0");
		}
		throw e;
	}
	
	
	private boolean IsEmpty(InProduct p){
		if(p != null) {
			if((!"".equals(p.getName())) && (p.getPrice() > 0.0)) {
				return false;
			}
		}
		return true;
	}
	private BusinessException creerBusinessException(String code, String message) {
		BusinessException e = new BusinessException();
		e.setCode(code);
		e.setMessage(message);
		return e;
	}
	
	private List<OutProduct> getOutlist(List<Product> productList){
		List<OutProduct> outList = new ArrayList<OutProduct>();
		for(Product p: productList){
			outList.add(getOutFromProduct(p));
		}
		return outList;
	}
	
	private Product getProductFromInOut(InProduct inout){
		Product p = new Product();
		p.setId(inout.getId());
		p.setName(inout.getName());
		p.setPrice(inout.getPrice());
		logger.info("Product : getProductFromInOut " + p.toString());
		return p;
		
	}
	
	private OutProduct getOutFromProduct(Product product){
		OutProduct p = new OutProduct();
		p.setId(product.getId());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		logger.info("Product : getOutFromProduct " + p.toString());
		return p;
		
	}
	
}
