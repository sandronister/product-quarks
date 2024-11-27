package org.sandroni.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.sandroni.entity.ProductEntity;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<ProductEntity,Long> {

}
