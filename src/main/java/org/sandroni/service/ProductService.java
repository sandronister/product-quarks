package org.sandroni.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sandroni.dto.ProductDTO;
import org.sandroni.entity.ProductEntity;
import org.sandroni.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public void createProduct(ProductDTO productDTO) {
        productRepository.persist(mapperDTOToEntity(productDTO));
    }

    public ProductDTO findProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id);
        return mapperEntityToDTO(productEntity);
    }

    public List<ProductDTO> findAllProducts() {
        List<ProductEntity> productEntities = productRepository.listAll();
        return productEntities.stream()
                .map(this::mapperEntityToDTO)
                .toList();
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(id);
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setQuantity(productDTO.getQuantity());
        System.out.println("Product updated");
        System.out.println(productEntity);
        productRepository.persist(productEntity);
    }

    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id);
        productRepository.delete(productEntity);
    }

    private ProductEntity mapperDTOToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setQuantity(productDTO.getQuantity());
        return productEntity;
    }

    private ProductDTO mapperEntityToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setQuantity(productEntity.getQuantity());
        return productDTO;
    }
}
