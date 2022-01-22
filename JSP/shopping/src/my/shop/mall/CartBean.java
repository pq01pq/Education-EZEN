package my.shop.mall;

import java.util.*;

import my.shop.ProductDTO;

public class CartBean {
	private List<ProductDTO> cart;
	
	public CartBean() {
		cart = new ArrayList<>();
	}

	public void add(ProductDTO inProduct) {
		for(ProductDTO product : cart) {
			if(product.getPnum() == inProduct.getPnum()) {
				product.setPqty(product.getPqty() + inProduct.getPqty());
				return;
			}
		}
		cart.add(inProduct);
	}
	
	public void modify(int pnum, int quantity) {
		for(ProductDTO product : cart) {
			if(product.getPnum() == pnum) {
				product.setPqty(quantity);
				return;
			}
		}
	}
	
	public ProductDTO remove(int pnum) {
		ProductDTO delProduct = null;
		for(ProductDTO product : cart) {
			if(product.getPnum() == pnum) {
				delProduct = product;
				cart.remove(delProduct);
				break;
			}
		}
		return delProduct;
	}
	
	public ProductDTO get(int pnum) {
		for(ProductDTO product : cart) {
			if(product.getPnum() == pnum) {
				return product;
			}
		}
		return null;
	}
	
	public List<ProductDTO> getCart() {
		return cart;
	}
}
