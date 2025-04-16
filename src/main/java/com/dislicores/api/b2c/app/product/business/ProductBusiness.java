package com.dislicores.api.b2c.app.product.business;

import com.dislicores.api.b2c.app.product.domain.carrousel.CarrouselResponse;
import com.dislicores.api.b2c.app.product.domain.carrousel.OriginSection;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductDetailDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductList;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductsBrandResponseDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductsFilteredResponseDto;
import com.dislicores.api.b2c.app.product.domain.promotions.RequestPromotionDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductInfoDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductParamListDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductParamsDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductWishDto;
import com.dislicores.api.b2c.app.product.domain.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductBusiness {

    ProductsFilteredResponseDto getProducts(ProductParamsDto productParamsDto, String token);

}
