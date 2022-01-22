package shop.mall;

import java.util.*;

import shop.ProductDTO;

public class CartBean {
	private List<ProductDTO> products;
	
	public CartBean() {
		products = new ArrayList<>();
	}

	public void add(ProductDTO inProduct) {
		for(ProductDTO product : products) {
			if(product.getPnum() == inProduct.getPnum()) {
				product.setPqty(product.getPqty() + inProduct.getPqty());
				return;
			}
		}
		products.add(inProduct);
	}
	
	public void modify(int pnum, int quantity) {
		for(ProductDTO product : products) {
			if(product.getPnum() == pnum) {
				product.setPqty(quantity);
				return;
			}
		}
	}
	
	public ProductDTO remove(int pnum) {
		ProductDTO delProduct = null;
		for(ProductDTO product : products) {
			if(product.getPnum() == pnum) {
				delProduct = product;
				products.remove(delProduct);
				break;
			}
		}
		return delProduct;
	}
	
	public ProductDTO get(int pnum) {
		for(ProductDTO product : products) {
			if(product.getPnum() == pnum) {
				return product;
			}
		}
		return null;
	}
	
	public List<ProductDTO> getProducts() {
		return products;
	}
}
