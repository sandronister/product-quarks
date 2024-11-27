package org.sandroni.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sandroni.dto.ProductDTO;
import org.sandroni.service.ProductService;

import java.util.List;


@Path("api/v1/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> getProducts() {
        return productService.findAllProducts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getProductById(@PathParam("id") Long id) {
        return productService.findProductById(id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteProductById(@PathParam("id") Long id) {
        try {
            productService.deleteProduct(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateProduct(@PathParam("id") Long id, ProductDTO productDTO) {
        try{
            productService.updateProduct(id, productDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    public Response createProduct(ProductDTO productDTO) {
        try{
            productService.createProduct(productDTO);
            return Response.created(null).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
